package com.teamx.gameequizapplication.games

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.teamx.gameequizapplication.GamesUID
import com.teamx.gameequizapplication.utils.RainGameObject
import kotlin.math.absoluteValue

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

            }/*   Column(
                   modifier = Modifier*//*.fillMaxHeight(0.9f)*//*,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
//        Row() {

                LazyColumn(
//            columns = StaggeredGridCells.Adaptive(122.dp),
                    modifier = Modifier
                        .width(150.dp)
                        .heightIn(500.dp, max = 680.dp)
                        .clickable(enabled = false, null, null, {}),
                    contentPadding = PaddingValues(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(86.dp),
                    state = rightScrollState2
//        verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(items = rightBoxes, itemContent = { _, item ->

                        AnimatedVisibility(
                            visible = !deletedRightList.contains(item),
                            enter = expandVertically(),
                            exit = shrinkVertically(animationSpec = tween(durationMillis = 1000))
                        ) {
                            drop(item = item) {}
                        }
                    })
                }
            }*/
        }/*Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Button(
                modifier = Modifier

                    .height(110.dp),
                onClick = {
                    val iu = leftBoxes.lastIndex - leftIndexCounter++
                    val iu2 = rightBoxes.lastIndex - rightIndexCounter++

                    if (leftBoxes[iu].gameObject == RainGameObject.DROP && (rightBoxes[iu2].gameObject == RainGameObject.THUNDER || rightBoxes[iu2].gameObject == RainGameObject.BLANK)) {
                        score++
                        deletedLeftList.add(leftBoxes[iu])
                        deletedRightList.add(rightBoxes[iu2])
                    } else if (leftBoxes[iu].gameObject == RainGameObject.BLANK && rightBoxes[iu2].gameObject == RainGameObject.THUNDER) {
//                        return@Button
                        deletedLeftList.add(leftBoxes[iu])
                        deletedRightList.add(rightBoxes[iu2])
                    } else {
                        return@Button
//                    score--
                    }

                },
            ) {

                Text(text = "Left")
            }

            Button(modifier = Modifier

                .height(110.dp), onClick = {
                val iu = leftBoxes.lastIndex - leftIndexCounter++
                val iu2 = rightBoxes.lastIndex - rightIndexCounter++

                if (rightBoxes[iu2].gameObject == RainGameObject.DROP && leftBoxes[iu].gameObject == RainGameObject.DROP || (rightBoxes[iu2].gameObject == RainGameObject.BLANK && leftBoxes[iu].gameObject == RainGameObject.BLANK)) {
                    score++
                    deletedLeftList.add(leftBoxes[iu])
                    deletedRightList.add(rightBoxes[iu2])
                } else if (rightBoxes[iu2].gameObject == RainGameObject.BLANK && leftBoxes[iu].gameObject == RainGameObject.THUNDER) {
//                    return@Button
                } else {
                    return@Button
//                    score--
                }


            }) {
                Text(text = "Mid")
            }
            Button(modifier = Modifier

                .height(100.dp), onClick = {
                val iu = leftBoxes.lastIndex - leftIndexCounter++
                val iu2 = rightBoxes.lastIndex - rightIndexCounter++

                if (rightBoxes[iu2].gameObject == RainGameObject.DROP && (leftBoxes[iu].gameObject == RainGameObject.THUNDER || leftBoxes[iu].gameObject == RainGameObject.BLANK)) {
                    score++
                    deletedLeftList.add(leftBoxes[iu])
                    deletedRightList.add(rightBoxes[iu2])
                } else if (rightBoxes[iu2].gameObject == RainGameObject.BLANK && leftBoxes[iu].gameObject == RainGameObject.THUNDER) {
//                    return@Button
                    deletedLeftList.add(leftBoxes[iu])
                    deletedRightList.add(rightBoxes[iu2])
                } else {
                    return@Button
//                    score--
                }

            }) {
                Text(text = "Right")
            }
        }*/

        Text(text = "$score")
    }
    LaunchedEffect(endOfListReached) {
        // do your stuff
    }

}

@Composable
fun dropRapid(item: RapidListItem, onClick: () -> Unit) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(15.dp))
            .background(item.color)
            .clickable {
                onClick()
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

/*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToRemoveApp() {
    val items = remember { mutableStateListOf("Item 1", "Item 2", "Item 3", "Item 4") }
    val itemToRemove = remember { mutableStateOf<String?>(null) }

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Swipe To Remove") }) }) {
        Column(modifier = Modifier.padding(16.dp)) {
            for (item in items) {
                if (item == itemToRemove.value) {
                    // Skip rendering the item that will be removed
                    continue
                }

                SwipeToRemoveItem(item = item, onSwiped = { removedItem ->
                    // Set the item to be removed after the swipe animation
                    itemToRemove.value = removedItem
                })
            }
        }
    }
    )
}

@Composable
fun SwipeToRemoveItem(item: String, onSwiped: (String) -> Unit) {
    var offsetX by remember { mutableStateOf(0f) }
    val screenWidth = LocalDensity.current.run { 720.dp.toPx() }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
        .pointerInput(Unit) {
            detectHorizontalDragGestures { _, dragAmount ->
                offsetX += dragAmount
            }
        }) {
        Text(text = item,
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .background(
                    color = if (offsetX > screenWidth) {
                        onSwiped(item) // The swipe threshold is reached, remove the item
                        Color.Transparent
                    } else {
                        Color.White
                    }, shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp))
    }
}*//*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToRemoveApp() {
    val items = remember { mutableStateListOf("Item 1", "Item 2", "Item 3", "Item 4") }
    val itemToRemove = remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Swipe To Remove") }) },
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            content = {
                items(items.size) { index ->
                    val item = items[index]
                    if (item == itemToRemove.value) {
                        // Skip rendering the item that will be removed
                        return@items
                    }

                    SwipeToRemoveItem(
                        item = item,
                        onSwiped = { removedItem ->
                            // Set the item to be removed after the swipe animation
                            itemToRemove.value = removedItem
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun SwipeToRemoveItem(item: String, onSwiped: (String) -> Unit) {
    var offsetX by remember { mutableStateOf(0f) }
    val screenWidth = LocalDensity.current.run { 720.dp.toPx() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { _, dragAmount ->
                    offsetX += dragAmount
                }
            }
    ) {
        Text(
            text = item,
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), 0) }
                .background(
                    color = if (offsetX > screenWidth) {
                        onSwiped(item) // The swipe threshold is reached, remove the item
                        Color.Transparent
                    } else {
                        Color.White
                    },
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}*//*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeLikeTinderApp() {
    val items = remember { mutableStateListOf("Item 1", "Item 2", "Item 3", "Item 4") }
    var currentItem by remember { mutableStateOf(0) }
    var offsetX by remember { mutableStateOf(0f) }
    var scale by remember { mutableStateOf(1f) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Swipe Like Tinder") }) },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale = zoom
                        offsetX += pan.x
                    }
                }
        ) {
            items.forEachIndexed { index, item ->
                if (index == currentItem) {
                    val animatedBackgroundColor by animateColorAsState(
                        if (offsetX > 0) Color.Green else Color.Red
                    )

                    val alpha by animateFloatAsState(if (offsetX != 0f) 0.7f else 1f)

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .offset {
                                val horizontalOffset = offsetX.coerceIn(-180f, 180f)
                                IntOffset(horizontalOffset.toInt(), 0)
                            }
                            .scale(scale)
                            .background(animatedBackgroundColor, shape = RoundedCornerShape(16.dp))
                            .alpha(alpha)
                            .pointerInput(Unit) {
                                detectTransformGestures { _, pan, _, _ ->
                                    offsetX += pan.x
                                }
                            }
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToRemoveApp() {
    val items = remember { mutableStateListOf("Item 1", "Item 2", "Item 3", "Item 4") }
    var currentItem by remember { mutableIntStateOf(0) }
    var offsetX by remember { mutableFloatStateOf(0f) }
    var scale by remember { mutableFloatStateOf(1f) }
    var removeItem by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Swipe To Remove") }) },
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    scale = zoom
                    offsetX += pan.x
                }
            }) {
            items.forEachIndexed { index, item ->
                if (index == currentItem) {
                    val animatedBackgroundColor by animateColorAsState(
                        if (offsetX > 0) Color.Green else Color.Red
                    )

                    val alpha by animateFloatAsState(if (offsetX != 0f) 0.7f else 1f)

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .offset {
                            val horizontalOffset = offsetX.coerceIn(-180f, 180f)
                            IntOffset(horizontalOffset.toInt(), 0)
                        }
                        .scale(scale)
                        .background(animatedBackgroundColor, shape = RoundedCornerShape(16.dp))
                        .alpha(alpha)
                        .pointerInput(Unit) {
                            detectTransformGestures { _, pan, _, _ ->
                                offsetX += pan.x
                            }

                            detectTransformGestures { _, pan, _, endPanVelocity ->
                                if (pan.x.absoluteValue > 200 || endPanVelocity.absoluteValue > 800) {
                                    removeItem = true
                                }
                            }
                        }) {
                        Text(
                            text = item, modifier = Modifier.padding(16.dp)
                        )
                    }

                    if (removeItem) {
                        LaunchedEffect(Unit) {
                            items.removeAt(currentItem)
                            removeItem = false
                        }
                    }
                }
            }
        }
    }
}