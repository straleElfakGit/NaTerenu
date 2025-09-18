package com.example.naterenu.ui.components

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.naterenu.ui.theme.NaTerenuTheme
import com.example.naterenu.ui.theme.md_theme_dark_outline2
import com.example.naterenu.ui.theme.md_theme_light_outline2

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Map,
        Screen.Schedule,
        Screen.Friends,
        Screen.Settings
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEachIndexed { index, screen ->

            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(screen.icon),
                        contentDescription = stringResource( screen.title),
                        modifier = Modifier.size(40.dp)) },
                label = {
                    Text(
                        stringResource(screen.title),
                        style = MaterialTheme.typography.labelSmall) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    indicatorColor = Color.Transparent
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun BottomNavigationBarWithBorder(navController: NavHostController) {
    val items = listOf(
        Screen.Map,
        Screen.Schedule,
        Screen.Friends,
        Screen.Settings
    )
    val isDarkTheme = isSystemInDarkTheme()

    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .drawBehind {
                val strokeWidth = 1.dp.toPx()
                drawLine(
                    color = if(!isDarkTheme) md_theme_light_outline2 else md_theme_dark_outline2,
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = strokeWidth
                )
            }
    ) {
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background,
            modifier = Modifier
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEachIndexed { index, screen ->

                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(screen.icon),
                            contentDescription = stringResource( screen.title),
                            modifier = Modifier.size(40.dp)) },
                    label = {
                        Text(
                            stringResource(screen.title),
                            style = MaterialTheme.typography.labelSmall) },
                    selected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                        indicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun EqualWidthBottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Map,
        Screen.Schedule,
        Screen.Friends,
        Screen.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.background)
            .navigationBarsPadding()
    ) {
        items.forEachIndexed { index, screen ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable {
                        if (currentRoute != screen.route) {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(screen.icon),
                    contentDescription = stringResource(screen.title),
                    modifier = Modifier.size(40.dp),
                    tint = if (currentRoute == screen.route)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
                Text(
                    stringResource(screen.title),
                    style = MaterialTheme.typography.labelSmall,
                    color = if (currentRoute == screen.route)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }
            if(index != items.size - 1) {
                VerticalDivider(
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}

@Composable
fun NavigationBarWithSideFill(content: @Composable () -> Unit) {
    val insets = WindowInsets.navigationBars.asPaddingValues()

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom
    ) {
        val left = insets.calculateLeftPadding(LayoutDirection.Ltr)
        val right = insets.calculateRightPadding(LayoutDirection.Ltr)

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

@Composable
fun BottomNavigationBarWithBorderAndBlackOutline(navController: NavHostController) {
    val items = listOf(
        Screen.Map,
        Screen.Schedule,
        Screen.Friends,
        Screen.Settings
    )
    val isDarkTheme = isSystemInDarkTheme()
    NavigationBarWithSideFill {
        Box(
            modifier = Modifier
                //.navigationBarsPadding()
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    drawLine(
                        color = if(!isDarkTheme) md_theme_light_outline2 else md_theme_dark_outline2,
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = strokeWidth
                    )
                }
        ) {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEachIndexed { index, screen ->

                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(screen.icon),
                                contentDescription = stringResource( screen.title),
                                modifier = Modifier.size(40.dp)) },
                        label = {
                            Text(
                                stringResource(screen.title),
                                style = MaterialTheme.typography.labelSmall) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            if (currentRoute != screen.route) {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            indicatorColor = Color.Transparent
                        ),
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NavBarPreview() {
    NaTerenuTheme {
        val navController = rememberNavController()
        BottomNavigationBar(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun EqualNavBarPreview() {
    NaTerenuTheme {
        val navController = rememberNavController()
        EqualWidthBottomNavigationBar(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarWithBorderPreview() {
    NaTerenuTheme {
        val navController = rememberNavController()
        BottomNavigationBarWithBorder(navController)
    }
}