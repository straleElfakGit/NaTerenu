package com.example.naterenu.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.naterenu.R


sealed class Screen(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int) {

    object Map : Screen("map", R.drawable.soccer_field, R.string.nav_title_tereni)
    object Schedule : Screen("schedule", R.drawable.calendar, R.string.nav_title_termini)
    object Friends : Screen("friends", R.drawable.friends, R.string.nav_title_prijatelji)
    object Settings : Screen("settings", R.drawable.trophy, R.string.nav_title_rang_lista)
}