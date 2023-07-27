package com.teamx.gameequizapplication.games

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamx.gameequizapplication.R
import kotlinx.coroutines.delay

@Composable
fun ConcentrationGame(content: @Composable () -> Unit) {
    content()
    ConcentrationObjects()
}

var linkListAdded2 = ArrayList<ConcentrationModel>()

@Preview
@Composable
fun ConcentrationObjects() {
    val maxCount = 5
    val concentrationModels by remember { mutableStateOf<ArrayList<ConcentrationModel>>(arrayListOf()) }


    LaunchedEffect(Unit) {
        repeat(maxCount) { count ->

            concentrationModels.add(
                ConcentrationModel(
                    count.toString(), count.toString(), count.toString()
                )
            )

        }
    }

//    val link = ArrayList<ConcentrationModel>()

//    linkListAdded2.forEach {
//        link.add(it)
//    }
    /*  Log.d("123123", "ConcentrationObjects:linkListAdded2abb $linkListAdded2")
      linkListAdded2.clear()
      Log.d("123123", "ConcentrationObjects:linkListAdded2abb $link")
      if (link.size >= 1) {
          Log.d("123123", "ConcentrationObjects:1212 $linkListAdded2")
          for (i in link) {
              linkListAdded2.add(i)
          }
      } else {
          Log.d("123123", "ConcentrationObjects:12 $linkListAdded2")
          for (i in linkListAdded2) {
  //            if (Random.nextBoolean()) {
              linkListAdded2.add(i)
  //            }
          }
      }*/


    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        for (i in 0..maxCount) {/* if (i == (currentCount - 1)) {
                 bool = false
             }*/

//            var temp = linkListAdded2
            ConcentrationObject(i /*temp.get(0)*//*, colorStateList[i]*/) {

                Log.d("123123", "ConcentrationObjects:linkListAdded2 $linkListAdded2")/*  if (listAdded[it] == i) {

                      } else {

                      }*/
//                colorStateList[it] = Color.Transparent
            }
        }
//        }
    }
}

@Composable
fun ConcentrationObject(
    number: Int, /*itemCompared: ConcentrationModel,*/ onClick: (Item: ConcentrationModel) -> Unit
) {
    var colorState by remember { mutableStateOf<Color>(Color.Black) }

    Surface(color = if (linkListAdded2.contains(
            ConcentrationModel(
                number.toString(), number.toString(), number.toString()
            )
        )/* % 2 == 0*/) {
        colorState
    } else {
        colorState
    }, shape = RectangleShape, modifier = Modifier
        .size(85.dp)
        .offset(
            y = /*(-number * 60).dp*//* if (number in 2..3) {
                 (-(number % 3) * 90).dp
             } else*/ if (number > 1) {
                (-(number % 2) * 90).dp
            } else {
                (-number * 90).dp
            }, x = if (number in 2..3) {
                (/*-number*/0 * 90).dp
            } else if (number > 3) {
                (/*-number*/1 * 90).dp
            } else {
                (/*-number*/-1 * 90).dp
            }/*(-number * 60).dp*/
        )
        .clip(RoundedCornerShape(6.dp))

        .clickable(
            enabled = linkListAdded2.contains(
                ConcentrationModel(
                    number.toString(), number.toString(), number.toString()
                )
            )
        ) {
            if (colorState == Color.Transparent && false) {
                Log.d("123123", "ConcentrationObjectWrong2:$number ::$/itemCompared ")
                return@clickable
            } else if (ConcentrationModel(
                    number.toString(), number.toString(), number.toString()
                ) == linkListAdded2.get(0)
            ) {
                colorState
//                onClick(itemCompared)
//                Log.d("123123", "ConcentrationObjectWrong1:$number ::$itemCompared ")
            } else {
//                Log.d("123123", "ConcentrationObjectWrong2:$number ::$itemCompared ")
            }
        }
//            .graphicsLayer(alpha = 1f - number * 0.2f)

    ) {
        Text(
            text = number.toString(),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive
        )


    }
}

data class ConcentrationModel(var name: String, var key: String, var value: String)


/*@Composable
fun Modifier.flip(horizontal: Boolean = false, vertical: Boolean = false): Modifier {
    return this.then(
        Modifier.graphicsLayer(
            scaleX = if (horizontal) -1f else 1f, scaleY = if (vertical) -1f else 1f
        )
    )
}*/


/*@Composable
fun FlippedImage() {
//    val imageBitmap = remember { loadImageResource(R.drawable.your_image_resource).value }
//    val painter = remember { BitmapPainter(imageBitmap.asImageBitmap()) }

    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .flip(horizontal = true) // Use .flip(horizontal = true) for horizontal flip
        // .flip(vertical = true) for vertical flip
    )
}
@Composable
fun FlippableImage2() {
//    val imageBitmap = remember { loadImageResource(R.drawable.your_image_resource).value }
//    val painter = remember { BitmapPainter(imageBitmap.asImageBitmap()) }

    // State to track the flip animation progress (0f: unflipped, 1f: fully flipped)
    val isFlippedState = remember { mutableStateOf(false) }
    val isFlipped by animateFloatAsState(targetValue = if (isFlippedState.value) 1f else 0f)

    // Track the update of the isFlippedState to prevent animation restarts during recomposition
    val updatedIsFlippedState = rememberUpdatedState(isFlipped)

    Image(
        painter =  painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .alpha(if (isFlipped) 0f else 1f) // Hide the unflipped image during animation
            .flip(horizontal = isFlipped) // Use .flip(horizontal = true) for horizontal flip
        // .flip(vertical = true) for vertical flip
    ) {
        // Listen for click events to trigger the flip animation
        it.onClick {
            // Toggle the isFlippedState to trigger the animation
            isFlippedState.value = !updatedIsFlippedState.value
        }
    }
}*/
@Composable
fun FlippableImage() {
    var isFlipped by remember { mutableStateOf(false) }

//    val imageRes = if (isFlipped) R.drawable.your_flipped_image else R.drawable.your_unflipped_image
//    val imageBitmap = painterResource(id = imageRes).value.asImageBitmap()

    val rotationY: Float by animateFloatAsState(targetValue = if (isFlipped) 180f else 0f)

    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null,
        modifier = Modifier
            .size(150.dp)
            .clickable { isFlipped = !isFlipped }
            .graphicsLayer(rotationY = rotationY)
    )
}
@Composable
fun RotatableImage(rotationZ: Float) {
    var isFlipped by remember { mutableStateOf(false) }

//    val imageRes = if (isFlipped) R.drawable.your_flipped_image else R.drawable.your_unflipped_image
//    val imageBitmap = painterResource(id = imageRes).value.asImageBitmap()

    val rotationY: Float by animateFloatAsState(targetValue = if (isFlipped) 180f else 0f)

    Image(
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null,
        modifier = Modifier
            .size(150.dp)
            .clickable { isFlipped = !isFlipped }
            .graphicsLayer(rotationZ = rotationZ)
    )
}

@Composable
fun SpinningBox() {
    var rotationState by remember { mutableStateOf(0f) }
    LaunchedEffect(Unit) {
        repeat(180) { count ->
            delay(10L)
//            colorStateList.add(colorState)
            rotationState = rotationState + 1

        }
    }
    // Animate the rotationState from 0f to 360f repeatedly
    val rotation by animateFloatAsState(
        targetValue = rotationState, animationSpec = repeatable(
            iterations = 1,
            animation = tween(10)
        )
    )

    Surface(
        color = Color.Blue,
        modifier = Modifier
            .size(100.dp)
//            .graphicsLayer(rotationZ = rotation)
    ) {
        // Content of the spinning box (optional)
        RotatableImage(rotation)
        FlippableImage()
    }
}

@Preview
@Composable
fun PreviewFlippedImage() {
//    FlippableImage()
    SpinningBox()
}

