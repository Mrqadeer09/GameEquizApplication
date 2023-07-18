package com.teamx.gameequizapplication.games

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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import com.teamx.gameequizapplication.GamesUID
import com.teamx.gameequizapplication.utils.ListItem

fun LazyListState.isScrolledToEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

@Composable
fun RainFallGame(content: @Composable () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        content()

        rainFallDrops()

    }


}

@Composable
fun rainFallDrops() {

    val items1 = (0..(23)).map {
        ListItem(
            height = 150/*Random.nextInt(100, 300)*/.dp,
            name = "Gae$it",
            gamesUID = GamesUID.values()[it],
            color = if (it % 2 == 0) {
                Color(0xFFF44336).copy(alpha = 1f)
            } else {
                Color(0xFF00BCD4).copy(alpha = 1f)
            }
        )
    }
    var score by remember { mutableStateOf(0) }
    val boxes by remember { mutableStateOf(items1) }
    val boxes2 by remember { mutableStateOf(items1) }
    var counter by remember { mutableStateOf(0) }
    var counter2 by remember { mutableStateOf(0) }
    val deletedList = remember { mutableStateListOf<ListItem>() }
    val deletedList2 = remember { mutableStateListOf<ListItem>() }
    val scrollState = rememberLazyListState()
    val scrollState2 = rememberLazyListState()
    val endOfListReached by remember {
        derivedStateOf {
            scrollState.isScrolledToEnd()
        }
    }
    LaunchedEffect(true) {

        scrollState2.scrollToItem(boxes2.size)
        scrollState.scrollToItem(boxes.size)
        scrollState.scroll(MutatePriority.PreventUserInput, {})
        scrollState2.scroll(MutatePriority.PreventUserInput, {})
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
//        Row() {

                LazyColumn(
//            columns = StaggeredGridCells.Adaptive(122.dp),
                    modifier = Modifier
                        .width(150.dp)
                        .height(500.dp)
                        .clickable(enabled = false, null, null, {}),
                    contentPadding = PaddingValues(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(86.dp),
                    state = scrollState
//        verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    itemsIndexed(items = boxes, itemContent = { _, item ->

                        AnimatedVisibility(
                            visible = !deletedList.contains(item),
                            enter = expandVertically(),
                            exit = shrinkVertically(animationSpec = tween(durationMillis = 1000))
                        ) {
                            drop(item = item) {}
                        }
                    })
                }

            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
//        Row() {

                LazyColumn(
//            columns = StaggeredGridCells.Adaptive(122.dp),
                    modifier = Modifier
                        .width(150.dp)
                        .height(500.dp)
                        .clickable(enabled = false, null, null, {}),
                    contentPadding = PaddingValues(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(86.dp),
                    state = scrollState2
//        verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(items = boxes2, itemContent = { _, item ->

                        AnimatedVisibility(
                            visible = !deletedList2.contains(item),
                            enter = expandVertically(),
                            exit = shrinkVertically(animationSpec = tween(durationMillis = 1000))
                        ) {
                            drop(item = item) {}
                        }
                    })
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Button(onClick = {
            /*    if ((boxes.lastIndex - counter++) % 2 == 0) {
                    score++
                } else {
                    score--
                }
//                deletedList.clear()
                deletedList.add(boxes[boxes.lastIndex - counter++])*/
                val iu = boxes.lastIndex - counter++
                if (iu % 2 == 0) {
                    score++
                } else {
                    score--
                }
//                deletedList.clear()
                deletedList.add(boxes2[iu])
//                boxes = boxes.subList(0, boxes.size - 1)
//                boxes = boxes.subList(0, boxes.size - 2)
//                deletedList.add(boxes[boxes.lastIndex])
            }) {
                Text(text = "Umbrella")
            }

            Button(onClick = {
                val iu = boxes2.lastIndex - counter2++
                if (iu % 2 == 0) {
                    score++
                } else {
                    score--
                }
//                deletedList.clear()
                deletedList2.add(boxes2[iu])
//                boxes = boxes.subList(0, boxes.size - 1)
//                boxes = boxes.subList(0, boxes.size - 2)
//                deletedList.add(boxes[boxes.lastIndex])
            }) {
                Text(text = "Umbrella")
            }
        }
        Text(text = "$score")
    }
    LaunchedEffect(endOfListReached) {
        // do your stuff
    }

}


@Composable
fun drop(item: ListItem, onClick: () -> Unit) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .size(item.height)
            .padding(vertical = 16.dp)
            .clip(RoundedCornerShape(95.dp))
            .background(item.color)
            .clickable {
                onClick()
            }, contentAlignment = Alignment.Center


    ) {
        Text(
            text = item.name, style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
fun previewRainGame() {
    RainFallGame {

    }
}