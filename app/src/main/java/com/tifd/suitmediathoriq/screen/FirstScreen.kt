package com.tifd.suitmediathoriq.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.tifd.suitmediathoriq.R

@Composable
fun FirstScreen(navController: NavController) {
    // Font Definitions
    val regularFont = FontFamily(Font(R.font.pr))
    val boldFont = FontFamily(Font(R.font.pb))

    var name by remember { mutableStateOf("") }
    var palindromeInput by remember { mutableStateOf("") }
    val context = androidx.compose.ui.platform.LocalContext.current

    fun isPalindromeWithSpace(text: String): Boolean {
        val cleanedText = text.replace(" ", "").lowercase()
        return cleanedText == cleanedText.reversed()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Set background image
        Image(
            painter = painterResource(id = R.drawable.image), // Replace with your background image
            contentDescription = null,
            contentScale = ContentScale.Crop, // Adjust image scaling to fill the screen
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Circle Background with Icon
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(120.dp) // Ukuran lingkaran
                    .background(
                        color = Color.White.copy(alpha = 0.3f), // Warna putih transparan
                        shape = CircleShape
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user), // Ganti dengan ikon user Anda
                    contentDescription = "User Icon",
                    modifier = Modifier.size(40.dp) // Ukuran ikon
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Name Input Field
            CustomTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = "Name",
                fontFamily = regularFont
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Palindrome Input Field
            CustomTextField(
                value = palindromeInput,
                onValueChange = { palindromeInput = it },
                placeholder = "Palindrome",
                fontFamily = regularFont
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Check Button
            Button(
                onClick = {
                    val message = if (isPalindromeWithSpace(palindromeInput)) {
                        "isPalindrome"
                    } else {
                        "Not Palindrome"
                    }
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2A6F97),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "CHECK",
                    style = TextStyle(fontFamily = boldFont, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Next Button
            Button(
                onClick = {
                    navController.navigate("second_screen/$name")
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2A6F97),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "NEXT",
                    style = TextStyle(fontFamily = boldFont, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    fontFamily: FontFamily
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(Color.White, RoundedCornerShape(12.dp)) // Background putih dengan sudut bulat
            .padding(horizontal = 16.dp, vertical = 12.dp) // Padding internal
    ) {
        androidx.compose.foundation.text.BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontFamily = fontFamily,
                fontSize = 16.sp,
                color = Color.Gray
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = TextStyle(
                            fontFamily = fontFamily,
                            fontSize = 16.sp,
                            color = Color.Gray.copy(alpha = 0.5f)
                        )
                    )
                }
                innerTextField()
            }
        )
    }
}
