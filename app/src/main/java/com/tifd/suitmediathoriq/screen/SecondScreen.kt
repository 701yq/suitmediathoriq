package com.tifd.suitmediathoriq.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tifd.suitmediathoriq.R

@Composable
fun SecondScreen(navController: NavController, name: String) {
    val regularFont = FontFamily(Font(R.font.pr))
    val boldFont = FontFamily(Font(R.font.pb))

    var selectedUser by remember { mutableStateOf("Selected User Name") }

    // Mendengarkan data dari Third Screen melalui SavedStateHandle
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    savedStateHandle?.getLiveData<String>("selectedUser")?.observe(LocalLifecycleOwner.current) { selectedName ->
        selectedUser = selectedName
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Second Screen",
                style = TextStyle(
                    fontFamily = boldFont,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.weight(2f),
                maxLines = 1
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        // Divider dari ujung ke ujung
        Divider(
            color = Color.Gray.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth() // Memastikan garis penuh
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Welcome Section
        Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(horizontal = 16.dp)) {
            Text(
                text = "Welcome",
                style = TextStyle(
                    fontFamily = regularFont,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            )

            Text(
                text = name,
                style = TextStyle(
                    fontFamily = boldFont,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(100.dp))

        // Selected User
        Text(
            text = selectedUser,
            style = TextStyle(
                fontFamily = boldFont,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f))

        // Choose a User Button
        Button(
            onClick = {
                navController.navigate("third_screen")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2A6F97),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Choose a User",
                style = TextStyle(
                    fontFamily = boldFont,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}



