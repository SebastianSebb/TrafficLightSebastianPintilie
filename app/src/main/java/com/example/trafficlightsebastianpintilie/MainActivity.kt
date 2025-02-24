package com.example.trafficlightsebastianpintilie

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trafficlightsebastianpintilie.presentation.inputscreen.InputScreen
import com.example.trafficlightsebastianpintilie.presentation.trafficlightscreen.TrafficLightScreen
import com.example.trafficlightsebastianpintilie.ui.theme.TrafficLightSebastianPintilieTheme
import dagger.hilt.android.AndroidEntryPoint

@Composable
fun TrafficLightNavHost(context: Context, navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = "input_screen") {
        composable("input_screen") { InputScreen(context, navHostController) }
        composable(
            "traffic_light_screen/{receivedText}",
            arguments = listOf(navArgument("receivedText") { type = NavType.StringType })
        ) { backStackEntry ->
            val receivedText = backStackEntry.arguments?.getString("receivedText")
            receivedText?.let { TrafficLightScreen(it) }
        }
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry = navController.currentBackStackEntryAsState().value

            TrafficLightSebastianPintilieTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Traffic Light App") },
                            navigationIcon = {
                                if (navBackStackEntry != null && navBackStackEntry.destination.route != "input_screen") {
                                    IconButton(onClick = { navController.popBackStack() }) {
                                        Icon(
                                            Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Back"
                                        )
                                    }
                                }
                            },
                            colors = TopAppBarColors(
                                containerColor = Color.White,
                                scrolledContainerColor = Color.White,
                                navigationIconContentColor = Color.Black,
                                titleContentColor = Color.Black,
                                actionIconContentColor = Color.White
                            )
                        )
                    },
                    content = { paddingValues: PaddingValues ->
                        Surface(modifier = Modifier.padding(paddingValues)) {
                            TrafficLightNavHost(applicationContext, navController)
                        }
                    }
                )
            }
        }
    }
}