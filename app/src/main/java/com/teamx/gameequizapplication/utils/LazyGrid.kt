package com.teamx.gameequizapplication.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teamx.gameequizapplication.GamesUID
import kotlin.random.Random


data class ListItem(
    val name: String,
    val height: Dp,
    val color: Color,
    val gamesUID: GamesUID,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun grids(navController: NavController) {
    val items1 = (0..(GamesUID.values().size - 1)).map {
        ListItem(
            height = 150/*Random.nextInt(100, 300)*/.dp,
            name = GamesUID.values()[it].name,
            gamesUID = GamesUID.values()[it],
            color = Color(
                Random.nextLong(0xFFFFFFFF)
            ).copy(alpha = 1f)
        )
    }

    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(122.dp),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items1) { item ->
            RandomColorBox(item = item) {
                navController.navigate(it.toString())
            }
        }
    }
}


@Composable
fun RandomColorBox(item: ListItem, onClick: (gamesUID: GamesUID) -> Unit) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(item.height)
            .clip(RoundedCornerShape(25.dp))
            .background(item.color)
            .clickable {
                onClick(item.gamesUID)
            },
        contentAlignment = Alignment.Center


    ) {
        Text(
            text = item.name, style = MaterialTheme.typography.bodySmall
        )
    }
}