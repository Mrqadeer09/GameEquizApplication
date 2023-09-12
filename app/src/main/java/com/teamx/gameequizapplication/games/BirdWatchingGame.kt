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
import com.teamx.gameequizapplication.ui.theme.Pinky
import com.teamx.gameequizapplication.ui.theme.Purple200
import com.teamx.gameequizapplication.ui.theme.Teal200
import kotlinx.coroutines.delay
import java.util.LinkedList
import kotlin.random.Random

@Composable
fun BirdWatchingGame(content: @Composable () -> Unit) {
    content()
    birdAscendingObjects()
}

//


@Preview
@Composable
fun birdAscendingObjects() {
    val maxCount = 8
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
        /* if (link.size >= 1) {
             Log.d("123123", "AscendingObjects:1212 $birdLinkListAdded")
             for (i in link) {
                 birdLinkListAdded.push(i)
             }
         } else {*/
        Log.d("123123", "AscendingObjects:12 $birdLinkListAdded")
        for (i in 0..maxCount) {
            birdLinkListAdded.push(
                BirdListItem(
                    i,
                    12.dp,
                    checkNumberReturnColor2(BirdEnum.values()[Random.nextInt(0, 8)])
                )
            )
        }
//        }

        Log.d("123123", "AscendingObjects:birdLinkListAddedabbbbbb $birdLinkListAdded")

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

            Log.d("123123", "AscendingObjects:temp $i")

            birdAnimatedObject(i) {
                restart = false
                restart = true
                Log.d("123123", "AscendingObjects:birdLinkListAdded $it")

            }
        }

    }
}

@Composable
fun birdAnimatedObject(

    itemCompared: BirdListItem,
    onClick: (Item: BirdListItem) -> Unit
) {


    Surface(color = /*if (birdLinkListAdded.contains(number)) {*/
    itemCompared.color
        /* } else {
             Color.Transparent
         }*/, shape = RectangleShape, modifier = Modifier
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
            text = itemCompared.name.toString(),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive
        )
    }
}

data class BirdListItem(
    var name: Int,
    var height: Dp,
    var color: Color
)


fun checkNumberReturnColor2(index: BirdEnum): Color {
    return when (index) {
        BirdEnum.CYAN -> {
            Color.Cyan
        }

        BirdEnum.YELLOW -> {
            Color.Yellow
        }

        BirdEnum.GRAY -> {
            Color.LightGray
        }

        BirdEnum.RED -> {
            Color.Red
        }

        BirdEnum.GREEN -> {
            Color.Magenta
        }

        BirdEnum.BLUE -> {
            Color.Blue
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
        }

        else -> {
            Color.Cyan
        }
    }
}

enum class BirdEnum {
    RED, GREEN, YELLOW, CYAN, BLUE, MAGENTA, ORANGE, PINK, VIOLET, WHITE, GRAY
}
//
