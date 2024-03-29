package com.teamx.gameequizapplication.games

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.teamx.gameequizapplication.R
import com.teamx.gameequizapplication.ui.theme.BirdColor3
import com.teamx.gameequizapplication.ui.theme.BirdColor4
import com.teamx.gameequizapplication.ui.theme.DeceptionBlack
import kotlinx.coroutines.delay

@Preview
@Composable
fun ConcentrationGame(content: @Composable () -> Unit = {}) {
    content()
    ConcentrationObjects()
}

var linkListAdded2 = ArrayList<ConcentrationModel>()
var checkListAns = ArrayList<EnumConcentration>()

@Preview
@Composable
fun ConcentrationObjects() {
    val maxCount = 5
    val concentrationModels by remember { mutableStateOf<ArrayList<ConcentrationModel>>(arrayListOf()) }/*
        LaunchedEffect(Unit) {
            repeat(maxCount) { count ->*/
    for (count in 0..maxCount) {


        concentrationModels.add(
            ConcentrationModel(
                count.toString(),
                count.toString(),
                count.toString(),
                EnumConcentration.values()[count % 3]
            )
        )
    }

    /*}
}*/

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
            ConcentrationObject(
                i, concentrationModels[i]/*temp.get(0)*//*, colorStateList[i]*/
            ) {

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
    number: Int, concentrationModels: ConcentrationModel,/*itemCompared: ConcentrationModel,*/
    onClick: (Item: ConcentrationModel) -> Unit
) {
    var colorState by remember { mutableStateOf<Color>(DeceptionBlack) }

    Surface(color = if (linkListAdded2.contains(
            ConcentrationModel(
                number.toString(), number.toString(), number.toString(),

                EnumConcentration.values()[number % 3]
            )
        )/* % 2 == 0*/) {
        colorState
    } else {
        colorState
    }, shape = RectangleShape, modifier = Modifier
        .height(85.dp)
        .width(70.dp)
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
                    number.toString(),
                    number.toString(),
                    number.toString(),
                    EnumConcentration.values()[number % 3]
                )
            )
        ) {
            if (colorState == Color.Transparent && false) {
                Log.d("123123", "ConcentrationObjectWrong2:$number ::$/itemCompared ")
                return@clickable
            } else if (ConcentrationModel(
                    number.toString(),
                    number.toString(),
                    number.toString(),
                    EnumConcentration.values()[number % 3]
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
        /*  Text(
              text = ""*//*number.toString()*//*,
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive
        )*/
        SpinningBox(concentrationModels.asdEnum)

    }
}

data class ConcentrationModel(
    var name: String, var key: String, var value: String, var asdEnum: EnumConcentration
)


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
fun FlippableImage(asdEnum: EnumConcentration) {
    var isFlipped by remember { mutableStateOf(false) }

//    val imageRes = if (isFlipped) R.drawable.your_flipped_image else R.drawable.your_unflipped_image
//    val imageBitmap = painterResource(id = imageRes).value.asImageBitmap()

    val rotationY: Float by animateFloatAsState(targetValue = if (isFlipped) 180f else 0f)

    Image(painter = painterResource(id = concentrationCheckStringReturnDrawable(asdEnum)),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxSize()
            .background(BirdColor3)

            .clickable { isFlipped = !isFlipped }
            .graphicsLayer(rotationY = rotationY))
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
            .fillMaxSize()
            .clickable { isFlipped = !isFlipped }
            .graphicsLayer(rotationZ = rotationZ)
    )
}

var isFlippy = false

@Composable
fun SpinningBox(asdEnum: EnumConcentration = EnumConcentration.DIAMOND) {
    var rotationState by remember { mutableStateOf(0f) }

    // Animate the rotationState from 0f to 360f repeatedly
    val rotation by animateFloatAsState(
        targetValue = rotationState, animationSpec = repeatable(
            iterations = 1, animation = tween(10)
        )
    )
    var isFlipped by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        repeat(180) { count ->
            delay(5000L)
//            colorStateList.add(colorState)
            rotationState = rotationState + 1

            isFlipped = false
        }
    }


//    val imageRes = if (isFlipped) R.drawable.your_flipped_image else R.drawable.your_unflipped_image
//    val imageBitmap = painterResource(id = imageRes).value.asImageBitmap()

    val rotationY: Float by animateFloatAsState(targetValue = if (isFlipped) 180f else 0f)
    Surface(
        color = BirdColor4,
        modifier = Modifier
            .padding(2.dp)
            .fillMaxSize()
            .graphicsLayer(rotationY = rotationY)
    ) {
        // Content of the spinning box (optional)
//        RotatableImage(rotation)
//        FlippableImage(asdEnum)
        Image(painter = painterResource(
            id = if (isFlipped) concentrationCheckStringReturnDrawable(
                asdEnum
            ) else concentrationCheckStringReturnDrawable(EnumConcentration.BACK)
        ),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxSize()
                .paint(
                    painterResource(id = concentrationCheckStringReturnDrawable(EnumConcentration.FRONT)),
                    contentScale = ContentScale.FillBounds
                )
                .clickable(enabled = !isFlipped) {
                    isFlipped = !isFlipped
                    if (checkListAns.isEmpty()) {
                        checkListAns.add(asdEnum)
                    } else if (checkListAns.get(checkListAns.size - 1) == asdEnum && checkListAns.size % 2 != 0) {
                        checkListAns.add(asdEnum)

                    } else {
                        checkListAns.clear()
                        isFlipped = false
                    }


                })
//                .graphicsLayer(rotationY = rotationY))
    }

}

fun concentrationCheckStringReturnDrawable(str: EnumConcentration): Int {
    return when (str) {
        EnumConcentration.STAR -> {

            R.drawable.starfilledminor_svgrepo_com
        }

        EnumConcentration.DIAMOND -> {

            R.drawable.diamond_fill_svgrepo_com
        }

        EnumConcentration.PENTAGON -> {

            R.drawable.hexagon_svgrepo_com
        }

        EnumConcentration.BACK -> {

            R.drawable.cards_back_flip
        }

        EnumConcentration.FRONT -> {

            R.drawable.cards_front_flip
        }


        else -> {
            R.drawable.hexagon_svgrepo_com
        }
    }
}

enum class EnumConcentration {
    STAR, DIAMOND, PENTAGON, BACK, FRONT
}

@Preview
@Composable
fun PreviewFlippedImage() {
//    FlippableImage()
//    SpinningBox()
}

