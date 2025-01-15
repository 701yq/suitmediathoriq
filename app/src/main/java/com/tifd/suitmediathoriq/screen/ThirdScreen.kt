package com.tifd.suitmediathoriq.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tifd.suitmediathoriq.R
import com.tifd.suitmediathoriq.network.ApiService
import kotlinx.coroutines.launch

data class User(val id: Int, val email: String, val firstName: String, val lastName: String, val avatar: String)

@Composable
fun ThirdScreen(navController: NavController) {
    val boldFont = FontFamily(Font(R.font.pb))
    val regularFont = FontFamily(Font(R.font.pr))
    val coroutineScope = rememberCoroutineScope()
    var users by remember { mutableStateOf(listOf<User>()) }
    var errorMessage by remember { mutableStateOf("") }

    // Load data from API
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                users = ApiService.getUsers()
            } catch (e: Exception) {
                errorMessage = "Failed to load users: ${e.message}"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .size(40.dp) // Sama dengan pedoman
                    .align(Alignment.CenterStart)
                    .clickable { navController.popBackStack() }
                    .padding(start = 16.dp)
            )
            Text(
                text = "Third Screen",
                style = TextStyle(
                    fontFamily = boldFont,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.align(Alignment.Center),
                maxLines = 1
            )
        }

        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.isNotEmpty()) {
            // Tampilan jika API gagal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = errorMessage, color = Color.Red)
            }
        } else {
            // Tampilan daftar pengguna
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                items(users) { user ->
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 20.dp)
                                .clickable {
                                    navController.previousBackStackEntry
                                        ?.savedStateHandle
                                        ?.set("selectedUser", "${user.firstName} ${user.lastName}")
                                    navController.popBackStack() // balik ke sec screen
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(user.avatar),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(20.dp))
                            Column {
                                Text(
                                    text = "${user.firstName} ${user.lastName}",
                                    style = TextStyle(
                                        fontFamily = boldFont,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Black
                                    )
                                )
                                Text(
                                    text = user.email,
                                    style = TextStyle(
                                        fontFamily = regularFont,
                                        fontSize = 14.sp,
                                        color = Color.Gray
                                    )
                                )
                            }
                        }
                        Divider(
                            color = Color.Gray.copy(alpha = 0.2f),
                            thickness = 1.dp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}