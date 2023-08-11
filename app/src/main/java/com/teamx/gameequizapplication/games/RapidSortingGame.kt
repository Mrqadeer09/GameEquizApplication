package com.teamx.gameequizapplication.games

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.teamx.gameequizapplication.GamesUID
import com.teamx.gameequizapplication.ui.theme.Purple200
import com.teamx.gameequizapplication.ui.theme.Purple500
import com.teamx.gameequizapplication.ui.theme.Teal200
import com.teamx.gameequizapplication.utils.RainGameObject

class RapidSortingGame {}

@Composable
fun RapidSortingGame(content: @Composable () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        content()

        SwipeToRemoveApp()

    }


}

@Composable
fun rapidSortingGame() {

    var leftItems = (0..(23)).map {
        RapidListItem(
            height = 150/*Random.nextInt(100, 300)*/.dp,
            name = "$it",
            gamesUID = GamesUID.values()[it],
            color = if (it % 5 == 0) {
                Color(0xFFF44336).copy(alpha = 1f)
            } else if (it % 2 == 0) {
                Color(0xFF4CAF50).copy(alpha = 1f)
            } else {
                Color(0xFF00BCD4).copy(alpha = 1f)
            },
            gameObject = if (it % 5 == 0) {
                RainGameObject.THUNDER
            } else if (it % 2 == 0) {
                RainGameObject.BLANK
            } else {
                RainGameObject.DROP
            }
        )
    }
    var rightItems = (0..(23)).map {
        RapidListItem(
            height = 150/*Random.nextInt(100, 300)*/.dp,
            name = "$it",
            gamesUID = GamesUID.values()[it],
            color = if (it % 5 == 0) {
                Color(0xFFF44336).copy(alpha = 1f)
            } else if (it % 2 == 0) {
                Color(0xFF4CAF50).copy(alpha = 1f)
            } else {
                Color(0xFF00BCD4).copy(alpha = 1f)
            },
            gameObject = if (it % 5 == 0) {
                RainGameObject.THUNDER
            } else if (it % 2 == 0) {
                RainGameObject.BLANK
            } else {
                RainGameObject.DROP
            }
        )
    }
    leftItems = leftItems.shuffled()
    rightItems = rightItems.shuffled()

    /*rightItems =*/
    leftItems.forEachIndexed { i, t ->
        if (leftItems[i].gameObject == RainGameObject.THUNDER && rightItems[i].gameObject == RainGameObject.THUNDER) {
            leftItems.get(i).gameObject = RainGameObject.BLANK
            leftItems.get(i).color = Color(0xFF4CAF50).copy(alpha = 1f)
        }
    }
    leftItems.forEachIndexed { i, t ->
        if (leftItems[i].gameObject == RainGameObject.BLANK && rightItems[i].gameObject == RainGameObject.BLANK) {
            leftItems.get(i).gameObject = RainGameObject.DROP
            leftItems.get(i).color = Color(0xFF00BCD4).copy(alpha = 1f)
        }
    }
    leftItems.forEachIndexed { i, t ->
        if (leftItems[i].gameObject == RainGameObject.DROP && rightItems[i].gameObject == RainGameObject.DROP) {
            leftItems.get(i).gameObject = RainGameObject.THUNDER
            leftItems.get(i).color = Color(0xFFF44336).copy(alpha = 1f)
        }
    }
    var score by remember { mutableIntStateOf(0) }
    val leftBoxes by remember { mutableStateOf(leftItems) }
    val rightBoxes by remember { mutableStateOf(rightItems) }
    var leftIndexCounter by remember { mutableIntStateOf(0) }
    var rightIndexCounter by remember { mutableIntStateOf(0) }
    val deletedLeftList = remember { mutableStateListOf<RapidListItem>() }
    val deletedRightList = remember { mutableStateListOf<RapidListItem>() }
    val leftScrollState = rememberLazyListState()
    val rightScrollState2 = rememberLazyListState()
    val endOfListReached by remember {
        derivedStateOf {
            leftScrollState.isScrolledToEnd()
        }
    }
    LaunchedEffect(true) {
        rightScrollState2.scrollToItem(rightBoxes.size)
        leftScrollState.scrollToItem(leftBoxes.size)
        leftScrollState.scroll(MutatePriority.PreventUserInput, {})
        rightScrollState2.scroll(MutatePriority.PreventUserInput, {})
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom

    ) {
        Row(
            modifier = Modifier.fillMaxSize()/*.fillMaxSize(*//*1f*//*)*/,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier/*.fillMaxHeight(0.9f)*/,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
//        Row() {

                LazyColumn(
//            columns = StaggeredGridCells.Adaptive(122.dp),
                    modifier = Modifier
                        .fillMaxSize()
//                        .width(150.dp)
//                        .heightIn(500.dp, max = 680.dp)
                        .clickable(enabled = false, null, null, {}),
                    contentPadding = PaddingValues(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(86.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    state = leftScrollState
//        verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    itemsIndexed(items = leftBoxes, itemContent = { _, item ->

                        AnimatedVisibility(
                            visible = !deletedLeftList.contains(item),
                            enter = expandVertically(),
                            exit = shrinkVertically(animationSpec = tween(durationMillis = 1000))
                        ) {
                            dropRapid(item = item) {}
                        }
                    })
                }
            }
        }

        Text(text = "$score")
    }
    LaunchedEffect(endOfListReached) {

    }

}

@Composable
fun dropRapid(item: RapidListItem, onClick: () -> Unit) {
    val context = LocalContext.current
    var colorState by remember { mutableStateOf(Color(0xFFF44336)) }

    Box(
        modifier = Modifier
            .size(item.height)
            .clip(RoundedCornerShape(15.dp))
            .background(colorState)
            .clickable {
                onClick()
                Toast
                    .makeText(context, "clicked", Toast.LENGTH_SHORT)
                    .show()
                colorState = Color.Transparent
            }, contentAlignment = Alignment.Center


    ) {
        Text(
            modifier = Modifier/*.height(500.dp)*/,
            text = item.name,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
fun previewRapidGame() {
    RapidSortingGame {

    }
}

data class RapidListItem(
    var name: String,
    var height: Dp,
    var gameObject: RainGameObject,
    var color: Color,
    var gamesUID: GamesUID,
)


@Composable
fun SwipeToRemoveApp() {
    var colorState by remember { mutableStateOf(Color(0xFFF44336)) }
    val leftItems = (0..(23)).map {
        RapidListItem(
            height = 50.dp,
            name = "$it",
            gamesUID = GamesUID.values()[it],
            color = colorState/*.copy(alpha = 0f)*/,
            gameObject = if (it % 5 == 0) {
                RainGameObject.THUNDER
            } else if (it % 2 == 0) {
                RainGameObject.BLANK
            } else {
                RainGameObject.DROP
            }
        )
    }
    val list by remember { mutableStateOf(leftItems) }

    Box(modifier = Modifier.fillMaxSize() /*contentAlignment = Alignment.Center*/) {
        for (i in list) {
            dropRapid(i) {
                colorState = Color.Transparent
            }
        }
    }
}

///new work found removing items

val items = mutableStateListOf<String>(
    "Item Number 1",
    "Item Number 2",
    "Item Number 3",
    "Item Number 4",
    "Item Number 5",
    "Item Number 6",
    "Item Number 7",
    "Item Number 8",
)
private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = Teal200
)

private val LightColorPalette = lightColorScheme(
    primary = Purple500,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

/*
@Composable
fun SwipeToDeleteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun theme() {
    SwipeToDeleteTheme {
        window.statusBarColor = MaterialTheme.colorScheme.surface.toArgb()
        Scaffold(topBar = { MainTopBar() }) {


            LazyColumn(
                Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            ) {

                itemsIndexed(items = items, key = { index, item ->
                    item.hashCode()
                }) { index, item ->

                    val state = rememberDismissState(confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            items.remove(item)
                        }
                        true
                    })


                    SwipeToDismiss(state = state, background = {
                        val color = when (state.dismissDirection) {
                            DismissDirection.StartToEnd -> Color.Transparent
                            DismissDirection.EndToStart -> Color.Red
                            null -> Color.Transparent
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }

                    }, dismissContent = {
                        MyCustomItem(text = item)
                    }, directions = setOf(DismissDirection.EndToStart)
                    )
                    Divider()

                }

            }


        }
    }

}

@Composable
fun MyCustomItem(text: String) {
    ListItem(
        text = { Text(text = text) },
        overlineText = { Text(text = "OverLine") },
        icon = { Icon(imageVector = Icons.Outlined.Share, contentDescription = null) },
        trailing = {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowRight, contentDescription = null
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    )
}


@Composable
private fun MainTopBar() {
    TopAppBar(title = { Text(text = "Swipe To Delete") })
}*/
