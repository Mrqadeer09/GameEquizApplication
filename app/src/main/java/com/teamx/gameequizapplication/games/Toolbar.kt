package com.teamx.gameequizapplication.games


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar(
    title: String, onMenuClicked: () -> Unit, onProfileClicked: () -> Unit
) {
    TopAppBar(modifier = Modifier
        .height(56.dp)
        .background(Color.White, shape = RoundedCornerShape(1.dp)),

        title = {
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 6.dp),
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,

                )
        }, navigationIcon = {
            IconButton(onClick = onMenuClicked) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        }, actions = {
            ProfileButton(onClick = onProfileClicked)
        })
}

@Composable
private fun RowScope.ProfileButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(Icons.Filled.AccountCircle, contentDescription = "Profile")
    }
}

 
@Composable
fun ToolbarPreview(navController: NavController) {
    Toolbar(title = "Equiz",
        onMenuClicked = { navController.navigate("MenuScreen") },
        onProfileClicked = { "ProfileScreen" })
}