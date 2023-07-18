package com.teamx.gameequizapplication.games

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class ColorOfDeceptionGame {}


data class ColorBox(val colorName: ColorBundle, val color: Color)

enum class ColorBundle {
    ORANGE, WHITE, BLACK, GREEN, RED
}


@Composable
fun TouchTheColorsGameScreen(content: @Composable () -> Unit) {
    var score by remember { mutableStateOf(0) }
    var spanCount by remember { mutableStateOf(2) }
    var asGridCells by remember { mutableStateOf(GridCells.Fixed(2)) }
    var boxes by remember { mutableStateOf(generateBoxes()) }
    var restart by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Colors Deception",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyVerticalGrid(
            modifier = Modifier.width(200.dp),
            verticalArrangement = Arrangement.Center,
            columns = asGridCells
        ) {
            /*item(
                span = { GridItemSpan(2) }
            ) {
                Text("Title")
            }*/

            /*  boxes.forEach {
                  item(
                      span = { GridItemSpan(3) }
                  ) {
                      Text("Title")
                  }
              }*/

            itemsIndexed(boxes/*, span = { _, _ -> GridItemSpan(spanCount) }*/) { index, box ->

                /*   if (boxes.size % 2 == 0) {
                   } else if (boxes.size - 1 == index) {

   //                    asGridCells = GridCells.Fixed(3)
                   }*/
                Box(modifier = Modifier
                    .padding(1.dp)
                    .height(70.dp)
                    .width(70.dp)
                    .background(color = box.color)
                    .border(BorderStroke(1.dp, Color.LightGray))
                    .clip(
                        RoundedCornerShape(22.dp)
                    )
                    .clickable {
                        updateScore(boxes, box, index) { i, bool ->
                            score++
                            restart = true
                            val arr = ArrayList<ColorBox>()
                            boxes.forEach {
                                if (i != it.colorName) {
                                    arr.add(it)
                                }
                            }
                            boxes = arr
                            if (bool) {
                                restart = false
                            }

                        }
                        if (!restart) {
                            boxes = generateBoxes()
                            restart = true
                        }
                    }) {
                    if (box.colorName.toString()
                            .equals("BLACK", true) && box.color != Color.White
                    ) {
                        Text(
                            text = box.colorName.toString(),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        Text(

                            modifier = Modifier.align(Alignment.Center), color = Color.LightGray,
                            text = box.colorName.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }

        }

        //purani logic
        /*LazyRow(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            itemsIndexed(boxes) { index, box ->
                Box(modifier = Modifier
                    .padding(1.dp)
                    .size(70.dp)
                    .background(color = box.color)
                    .border(BorderStroke(1.dp, Color.LightGray))

                    .clickable {
                        updateScore(boxes, box, index) { i, bool ->
                            score++
                            restart = true
                            val arr = ArrayList<ColorBox>()
                            boxes.forEach {
                                if (i != it.colorName) {
                                    arr.add(it)
                                }
                            }
                            boxes = arr
                            if (bool) {
                                restart = false
                            }

                        }
                        if (!restart) {
                            boxes = generateBoxes()
                            restart = true
                        }
                    }

                    .clip(
                        RoundedCornerShape(12.dp)
                    )

                ) {
                    if (box.colorName.toString()
                            .equals("BLACK", true) && box.color != Color.White
                    ) {
                        Text(
                            text = box.colorName.toString(),
                            color = Color.White,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    } else {
                        Text(

                            modifier = Modifier.align(Alignment.Center), color = Color.LightGray,
                            text = box.colorName.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }*/

        Text(
            text = "Score: $score",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

var deceptionNum: ColorBundle? = null
private fun generateBoxes(): List<ColorBox> {
    val numbers = mutableListOf<ColorBundle>()
    for (i in ColorBundle.values()) {
        numbers.add(i)
    }
    numbers.shuffle()

    val deceptionNumber = Random.nextInt(1, 4)

    var colorBox = Color.White
    val colors = mutableListOf<Color>()
    for (i in numbers.indices) {
        if (deceptionNumber == i) {
            deceptionNum = numbers[deceptionNumber]
            colorBox = when (numbers[deceptionNumber]) {

                ColorBundle.ORANGE -> {
                    Color.Black
                }

                ColorBundle.WHITE -> {
                    Color(0xFFFF5722)
                }

                ColorBundle.BLACK -> {
                    Color.White

                }

                ColorBundle.GREEN -> {
                    Color.Red
                }

                ColorBundle.RED -> {
                    Color.Green
                }

                else -> {
                    Color.Green
                }
            }
        } else {
            colorBox = when (numbers[i]) {

                ColorBundle.ORANGE -> {
                    Color(0xFFFF5722)
                }

                ColorBundle.WHITE -> {
                    Color.White
                }

                ColorBundle.BLACK -> {
                    Color.Black

                }

                ColorBundle.GREEN -> {
                    Color.Green
                }

                ColorBundle.RED -> {
                    Color.Red
                }

                else -> {
                    Color.Green
                }
            }
        }

        /* val colorBox = when (i) {
            ColorBundle.ORANGE -> {
                Color(0xFFFF5722)
            }
            ColorBundle.WHITE -> {
                Color.White
            }
            ColorBundle.BLACK -> {
                Color.Black

            }
            ColorBundle.GREEN -> {
                Color.Green
            }
            ColorBundle.RED -> {
                Color.Red
            }
            else -> {
                Color.Green
            }
        }*/


        colors.add(colorBox)
    }

    return numbers.mapIndexed { index, number ->
        ColorBox(colorName = number, color = colors[index])
    }
}

var colorScore = 0
private fun updateScore(
    boxes: List<ColorBox>,
    box: ColorBox,
    index: Int,
    onClickAdd: (number: ColorBundle, bool: Boolean) -> Unit
) {

    /*    val Checker = if (box.color == Color.Green) {
            boxes.minWith(Comparator.comparing { it.colorName })

        } else {
            boxes.maxWith(Comparator.comparing { it.colorName })

        }*/


    val selectedColors = mutableListOf<ColorBundle>()

    for (i in ColorBundle.values()) {
        if (i != box.colorName && box.color == Color.Green) {
            selectedColors.add(i)
        } else if (i > box.colorName && box.color == Color.Red) {
            selectedColors.add(i)
        }
    }

//
//    val sortedColors = selectedColors.sorted()
    if (deceptionNum == box.colorName) {
        // Correct order
        score++
        onClickAdd(box.colorName, true)
    } else {
        // Incorrect order
//        score = 0
        score--
    }
}


@Preview
@Composable
fun PreviewTouchTheColorsGameScreen() {
    TouchTheColorsGameScreen() {}
}
