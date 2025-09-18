package com.example.naterenu

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.example.naterenu.ui.components.BottomNavigationBar
import com.example.naterenu.ui.components.Screen
import com.example.naterenu.ui.screens.SettingsScreen
import com.example.naterenu.ui.screens.MapScreen
import com.example.naterenu.ui.screens.FriendsScreen
import com.example.naterenu.ui.screens.ScheduleScreen
import com.example.naterenu.ui.theme.NaTerenuTheme

import androidx.navigation.compose.*
import com.example.naterenu.ui.components.BottomNavigationBarWithBorder
import com.example.naterenu.ui.components.BottomNavigationBarWithBorderAndBlackOutline

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            NaTerenuTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBarWithBorder(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Map.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Map.route) { MapScreen() }
            composable(Screen.Schedule.route) { ScheduleScreen() }
            composable(Screen.Friends.route) { FriendsScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }
}

@Composable
fun ScreenWithFullSideFill(
    content: @Composable () -> Unit
) {
    val insets = WindowInsets.systemBars.asPaddingValues()

    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        val left = insets.calculateLeftPadding(LayoutDirection.Ltr)
        val right = insets.calculateRightPadding(LayoutDirection.Ltr)
        val top = insets.calculateTopPadding()
        val bottom = insets.calculateBottomPadding()

        if (left > 0.dp) {
            Box(
                modifier = Modifier
                    .width(left)
                    .fillMaxHeight()
                    .background(Color.Black)
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(top = top, bottom = bottom)
        ) {
            content()
        }

        if (right > 0.dp) {
            Box(
                modifier = Modifier
                    .width(right)
                    .fillMaxHeight()
                    .background(Color.Black)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreviewLight() {
    NaTerenuTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreviewDark() {
    NaTerenuTheme(
        darkTheme = true
    ) {
        MainScreen()
    }
}

