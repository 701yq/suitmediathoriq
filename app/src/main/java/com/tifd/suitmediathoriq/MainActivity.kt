package com.tifd.suitmediathoriq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tifd.suitmediathoriq.screen.FirstScreen
import com.tifd.suitmediathoriq.screen.SecondScreen
import com.tifd.suitmediathoriq.screen.ThirdScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "first_screen"
            ) {
                composable("first_screen") { FirstScreen(navController) }
                composable("second_screen/{name}") { backStackEntry ->
                    val name = backStackEntry.arguments?.getString("name") ?: ""
                    SecondScreen(navController, name)
                }
                composable("third_screen") { ThirdScreen(navController) }
            }
        }
    }
}