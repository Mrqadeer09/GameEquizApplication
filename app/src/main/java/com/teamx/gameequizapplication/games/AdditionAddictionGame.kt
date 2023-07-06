package com.teamx.gameequizapplication.games


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun AdditionAddictionGame(content: @Composable () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        content()
    }
}


@Composable
fun DragListColorChanger(colors: List<Color>) {
    val draggedIndex = remember { mutableStateOf(-1) }

    LazyColumn {
        itemsIndexed(colors) { index, color ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(color, shape = RectangleShape)
                    .pointerInput(Unit) {
                        /*  detectTapGestures(
                              onLongPress = {
                                  isPressed = true
                              },
                              onPress = {
                                  isPressed = false
                              }
                          )*/
                        detectDragGestures { change, dragAmount ->
                            if (change.pressed) {
                                draggedIndex.value = index
                            }
                        }
                    }
            )
        }
    }

    // Display the dragged index
    Text(
        text = "Dragged index: ${draggedIndex.value}",
        modifier = Modifier.padding(16.dp)
    )
}

@Preview
@Composable
fun previewHexa() {
    MaterialTheme() {

        DragList2(generateColors())
    }
}

private fun generateColors(): List<Color> {
    val numbers = mutableListOf<Color>()
    for (i in 1..10) {


        numbers.add(Color(Random.nextLong(0xFFFFFFFF)))

    }
    numbers.shuffle()

    return numbers
}

@Composable
fun DragList2(items: List<Color>) {
    LazyColumn {
        itemsIndexed(items) { index, item ->
            var currentIndex by remember { mutableStateOf(0) }

            Box(
                modifier = Modifier
                    .background(item, RectangleShape)
                    .size(60.dp)
                    .draggable(
                        orientation = Orientation.Vertical,
                        onDragStarted = { dragAmount ->
                            val draggedIndex = (index + (dragAmount.y / 60f)).coerceIn(
                                0f,
                                items.lastIndex.toFloat()
                            )
                            currentIndex = draggedIndex.toInt()
                        },
                        onDragStopped = { p -> /* Handle drag end */ }, state = DraggableState { })
            ) {
                Text(text = currentIndex.toString())
            }
        }
    }
}

data class ListItem(
    val text: String,
    var isSelected: Boolean = false
)
/*
@Composable
fun DragList(items: List<ListItem>) {
    LazyColumn {
        itemsIndexed(items) { index, item ->
            var currentIndex by remember { mutableStateOf(index) }

            Box(
                modifier = Modifier
                    .clickable {
                        item.isSelected = !item.isSelected
                    }
                    .draggable(
                        orientation = Orientation.Vertical,
                        onDrag = { _, dragAmount ->
                            val draggedIndex = (index + (dragAmount / ITEM_HEIGHT)).coerceIn(0, items.lastIndex)
                            currentIndex = draggedIndex
                        },
                        onDragEnd = { */
/* Handle drag end *//*
 }
                    )
                    .background(if (item.isSelected) Color.Red else Color.Transparent)
            ) {
                // Compose implementation for each item in the list
            }
        }
    }
}*/
