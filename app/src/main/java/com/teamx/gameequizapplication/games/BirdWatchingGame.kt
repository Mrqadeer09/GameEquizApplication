package com.teamx.gameequizapplication.games

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamx.gameequizapplication.ui.theme.BirdColor1
import com.teamx.gameequizapplication.ui.theme.BirdColor2
import com.teamx.gameequizapplication.ui.theme.BirdColor3
import com.teamx.gameequizapplication.ui.theme.BirdColor4
import kotlinx.coroutines.delay
import java.util.LinkedList
import kotlin.random.Random

@Preview
@Composable
fun BirdWatchingGame(content: @Composable () -> Unit = {}) {
    content()
    BirdAscendingObjects()
}

//

var iteration = 0

@Preview
@Composable
fun BirdAscendingObjects() {
//    val maxCount = 8
    val maxCount by remember {
        mutableIntStateOf(
            if (iteration % 1 == 0) {
                8
            } else {
                8

            }
        )
    }
//    iteration = 0

//    val maxCount = 4
    val birdLinkListAdded by remember { mutableStateOf(LinkedList<BirdListItem>()) }
    var restart by remember { mutableStateOf(true) }
    val link = LinkedList<BirdListItem>()
    if (restart) {
//        restart = true

        birdLinkListAdded.forEach {
            link.push(it)
        }
        Log.d("123123", "AscendingObjects:birdLinkListAdded $birdLinkListAdded")
        birdLinkListAdded.clear()
        Log.d("123123", "AscendingObjects:birdLinkListAdded $link")

        Log.d("123123", "AscendingObjects:12 $birdLinkListAdded")
        for (i in 0..maxCount) {
            val randomNum = Random.nextInt(0, 4)
            birdLinkListAdded.push(
                BirdListItem(
                    i,
                    valueColor = randomNum,
                    12.dp,
                    checkNumberReturnColor2(BirdEnum.values()[randomNum]),
                )
            )
        }
//        }

        Log.d("123123", "AscendingObjects:birdLinkListAddedab $birdLinkListAdded")

        Log.d("123123", "AscendingObjects:birdlinkListAdded ${birdLinkListAdded.size}")
        ///
        var rotationState by remember { mutableFloatStateOf(0f) }


        LaunchedEffect(Unit) {
            repeat(180) { count ->
                delay(10L)
//            colorStateList.add(colorState)
                rotationState = 180f

            }
        }
        // Animate the rotationState from 0f to 360f repeatedly
        val rotation by animateFloatAsState(
            targetValue = rotationState, animationSpec = repeatable(
                iterations = 1, animation = tween(5000)
            )
        )
        ///
    }
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
//            .graphicsLayer(rotationY = rotation), contentAlignment = Alignment.Center
    ) {

        for (i in birdLinkListAdded) {

//            val temp = birdlinkListAdded

//            Log.d("123123", "AscendingObjects: $i")

            BirdAnimatedObject(i) { it ->

                val clickedCount = birdLinkListAdded.count { a -> a.valueColor == it.valueColor }
                var maxCount2 = birdLinkListAdded.groupingBy { it.valueColor }.eachCount()
                    .filter { it.value >= 0 }.values.max()
                if (clickedCount == maxCount2) {
                    restart = false
                    restart = true
                    iteration++


                }
//                Log.d("123123", "AscendingObjects:birdLinkListAdded $it")
                Log.d(
                    "123123",
                    "@@@@@$iteration@@@@" + birdLinkListAdded.groupingBy { it.valueColor }.eachCount()
                        .filter { it.value >= 0 }.values.max()
                )

                val aia = birdLinkListAdded.groupingBy { it.valueColor }
                    .eachCount()
                    .filter { it.value >= 0 }.values.indexOf(birdLinkListAdded.groupingBy { it.valueColor }
                        .eachCount().filter { it.value >= 1 }.values.max())
                Log.d(
                    "123123",
                    "@@@@@" + BirdEnum.values()[birdLinkListAdded.groupingBy { it.valueColor }
                        .eachCount()
                        .filter { it.value >= 0 }.values.indexOf(birdLinkListAdded.groupingBy { it.valueColor }
                            .eachCount().filter { it.value >= 1 }.values.max())]
                ).toString()

                Log.d(
                    "123123",
                    "@@@@@" + birdLinkListAdded.groupingBy { it.valueColor }
                        .eachCount()
                        .filter { it.value >= 0 }.values
                )
                Log.d(
                    "123123",
                    "@@@@@" + BirdEnum.values()[it.valueColor].toString()
                )
                Log.d(
                    "123123",
                    "@@@@@" + birdLinkListAdded.count { a -> a.valueColor == it.valueColor }
                )
            }
        }

    }
}


@Composable
fun BirdAnimatedObject(
    itemCompared: BirdListItem,
    onClick: (Item: BirdListItem) -> Unit
) {


    Surface(
        color = itemCompared.color,
        shape = RectangleShape,
        modifier = Modifier
            .size(85.dp)
            .offset(
                y = if (itemCompared.name in 3..5) {
                    (((itemCompared.name % 3) + 2) * 90).dp
                } else if (itemCompared.name > 5) {
                    (((itemCompared.name % 3) + 2) * 90).dp
                } else {
                    ((itemCompared.name + 2) * 90).dp
                }, x = if (itemCompared.name in 3..5) {
                    (2 * 70).dp
                } else if (itemCompared.name > 5) {
                    (3 * 80).dp
                } else {
                    (1 * 40).dp
                }
            )
            .clip(RoundedCornerShape(6.dp))

            .clickable(
//                enabled = birdlinkListAdded.contains(number)
            ) {
                onClick(itemCompared)
                /*   if (itemCompared.color == Color.Transparent) {
                       Log.d("123123", "AnimatedObjectWrong2:${itemCompared.name} ::$itemCompared ")
                       return@clickable
                   } else if (itemCompared.name == birdlinkListAdded.first.name) {

                       onClick(itemCompared)
                       Log.d("123123", "AnimatedObjectWrong1:$itemCompared.name ::$itemCompared ")
                   } else {
                       Log.d("123123", "AnimatedObjectWrong2:$itemCompared.name ::$itemCompared ")
                   }*/
            }) {

        Text(
            text = /*itemCompared.name.toString() +*/"11",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Transparent,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive
        )
    }
}
@Composable
fun BirdAnimatedObject2(
    itemCompared: BirdListItem,
    onClick: (Item: BirdListItem) -> Unit
) {


    Surface(
        color = itemCompared.color,
        shape = RectangleShape,
        modifier = Modifier
            .size(35.dp)
            .offset(

                y = if (itemCompared.name in 3..5) {
                    (((itemCompared.name % 3) + 2) * 45).dp
                } else if (itemCompared.name > 5) {
                    (((itemCompared.name % 3) + 2) * 45).dp
                } else {
                    ((itemCompared.name + 2) * 45).dp
                }, x = if (itemCompared.name in 3..5) {
                    (2 * 40).dp
                } else if (itemCompared.name > 5) {
                    (3 * 40).dp
                } else {
                    (1 * 40).dp
                }
            )
            .clip(RoundedCornerShape(6.dp))

            .clickable(
//                enabled = birdlinkListAdded.contains(number)
            ) {
                onClick(itemCompared)
                /*   if (itemCompared.color == Color.Transparent) {
                       Log.d("123123", "AnimatedObjectWrong2:${itemCompared.name} ::$itemCompared ")
                       return@clickable
                   } else if (itemCompared.name == birdlinkListAdded.first.name) {

                       onClick(itemCompared)
                       Log.d("123123", "AnimatedObjectWrong1:$itemCompared.name ::$itemCompared ")
                   } else {
                       Log.d("123123", "AnimatedObjectWrong2:$itemCompared.name ::$itemCompared ")
                   }*/
            }) {

        Text(
            text = /*itemCompared.name.toString() +*/"11",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Transparent,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive
        )
    }
}

data class BirdListItem(
    var name: Int,
    var valueColor: Int,
    var height: Dp,
    var color: Color
)


fun checkNumberReturnColor2(index: BirdEnum): Color {
    return when (index) {
        BirdEnum.CYAN -> {
            BirdColor3
        }

        BirdEnum.VOILET -> {
            BirdColor4
        }


        BirdEnum.RED -> {
            BirdColor1

        }

        BirdEnum.GREEN -> {
            BirdColor2

        }

        /* BirdEnum.BLUE -> {
             Color.Blue
         }
           BirdEnum.GRAY -> {
             Color.LightGray
         }
         BirdEnum.MAGENTA -> {
             Color.Magenta
         }

         BirdEnum.ORANGE -> {
             Purple200
         }

         BirdEnum.PINK -> {
             Pinky
         }

         BirdEnum.VIOLET -> {
             Teal200
         }

         BirdEnum.WHITE -> {
             Color.White
         }*/

        else -> {
            BirdColor4
        }
    }
}

enum class BirdEnum {
    RED, GREEN, CYAN, VOILET, /*, BLUE, MAGENTA, ORANGE, PINK, VIOLET, WHITE, GRAY*/
}
//
