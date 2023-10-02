package com.teamx.gameequizapplication.games

import android.util.Log
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamx.gameequizapplication.R
import com.teamx.gameequizapplication.ui.theme.BirdColor1
import com.teamx.gameequizapplication.ui.theme.BirdColor3
import com.teamx.gameequizapplication.ui.theme.Pink80
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt
import kotlin.random.Random

@Preview
@Composable
fun FlickGameScreen(content: @Composable () -> Unit={}) {
    Box(
        modifier = Modifier.fillMaxSize(), Alignment.Center
    ) {
        FlickComponent()
    }
}

//flick
@Composable
fun FlickComponent() {
    var swipeStateX by remember { mutableStateOf(false) }
    var restart by remember { mutableStateOf(true) }
    val checkOppo by remember { mutableStateOf(false) }
    var randomInt by remember { mutableIntStateOf(0) }
    var wapsiState by remember { mutableStateOf(false) }
    var intOffset by remember { mutableStateOf(IntOffset(y = 0, x = 0)) }
    var valuesTranslation by remember { mutableFloatStateOf(150f) }

    var bimap by remember { mutableIntStateOf(R.drawable.left) }

    val transitionState = remember {
        MutableTransitionState(swipeStateX).apply { targetState = !swipeStateX }
    }
    val transition = updateTransition(transitionState, "cardTransition")

    val offsetTransitionY by transition.animateFloat(label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = 300) },
        targetValueByState = { if (swipeStateX) valuesTranslation else 0f })
    val offsetTransitionX by transition.animateFloat(label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = 300) },
        targetValueByState = { if (swipeStateX) valuesTranslation else 0f })

    wapsiState = transition.isRunning

    LaunchedEffect(key1 = !wapsiState) {
        randomInt = Random.nextInt(0, 4)
    }
    if (!wapsiState) {
        valuesTranslation = 0f
        swipeStateX = false
        swipeStateX = false
        valuesTranslation = 100f
    }
    when (randomInt) {

        0 -> {
            intOffset = IntOffset(x = -offsetTransitionX.roundToInt(), y = 0)
            bimap = R.drawable.right
        }

        1 -> {
            intOffset = IntOffset(x = offsetTransitionX.roundToInt(), y = 0)
            bimap = R.drawable.left
        }

        2 -> {
            intOffset = IntOffset(y = -offsetTransitionY.roundToInt(), x = 0)
            bimap = R.drawable.up
        }

        3 -> {
            intOffset = IntOffset(y = offsetTransitionY.roundToInt(), x = 0)
            bimap = R.drawable.down
        }

        else -> {
            intOffset = IntOffset(y = -offsetTransitionY.roundToInt(), x = 0)
            bimap = R.drawable.left
        }


    }


    if (restart) {
        Card(
            modifier = Modifier
                .testTag("DraggableCard")
                .width(165.dp)
                .alpha(valuesTranslation)
                .height(165.dp)
                .padding(horizontal = 4.dp, vertical = 1.dp)
                .offset {
                    intOffset

                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->

                        when {
                            dragAmount.x >= 26 && randomInt == 0 -> {

                                Log.d("123123", "MyCardRight: ")
                                swipeStateX = true
                                restart = true
                            }

                            dragAmount.x < -26 && randomInt == 1 -> {

                                Log.d("123123", "MyCardLeft: ")
                                swipeStateX = true
                                restart = true
                            }

                            dragAmount.y >= 26 && randomInt == 3 -> {
//                            randomInt = 3
                                Log.d("123123", "MyCardDOWN: ")
                                swipeStateX = true
                                restart = true
                            }

                            dragAmount.y < -26 && randomInt == 2 -> {

                                Log.d("123123", "MyCardUP: ")
                                swipeStateX = true
                                restart = true
                            }
                        }
                    }


                },
        ) {
            Image(
                painter = painterResource(id = bimap),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = if (checkOppo) {
                            BirdColor1
                        } else {
                            BirdColor3
                        }
                    )
//                .clickable { isFlipped = !isFlipped }
//                .graphicsLayer(rotationZ = rotationZ)
            )


        }
    }

}


//new implementation
var i2322 = 1
var dragged22 = true


@Composable
fun HighLowComponent2() {
    var swipeStateX by remember { mutableStateOf(false) }

    var previousNumber by remember { mutableIntStateOf(Random.nextInt(0, 100)) }
    var showNumber by remember { mutableIntStateOf(Random.nextInt(0, 100)) }
    var valuesTranslation by remember {
        mutableFloatStateOf(
            590f
        )
    }
    var randomInt by remember { mutableIntStateOf(1) }
    randomInt = if (previousNumber > showNumber) {
        1
    } else {
        2
    }
    var fadeValue by remember { mutableStateOf(0f) }
    var bimap by remember { mutableIntStateOf(R.drawable.down) }

    val transitionState = remember { MutableTransitionState(false) }
    val transition = updateTransition(transitionState, "cardTransition")


    val offsetTransitionY by transition.animateFloat(label = "cardOffsetTransition",
        transitionSpec = { tween(durationMillis = 500) },

        targetValueByState = {
            if (it) {
                valuesTranslation
            } else {
                0f
            }
        })



    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {


        Card(

            modifier = Modifier
                .testTag("DraggableCard")
//            .width(165.dp)
                .wrapContentSize()
//            .height(165.dp)

                .padding(horizontal = 4.dp, vertical = 1.dp)
                .offset(
                    x = if (!transitionState.targetState) {
                        if (randomInt == 1) {
                            offsetTransitionY.dp
                        } else {
                            -offsetTransitionY.dp
                        }
                    } else {
                        0.dp
                    }, y = if (transitionState.targetState) {
                        if (randomInt == 1) {
                            offsetTransitionY.dp
                        } else {
                            -offsetTransitionY.dp
                        }
                    } else {
                        0.dp
                    }


                )
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->

                        when {

                            previousNumber <= showNumber && (dragAmount.y <= -2.0 && dragAmount.y < 0) && randomInt == 2 -> {

                                if (dragged22) {
                                    dragged22 = false
                                    Log.d("123123", "MyCardUP: ${dragAmount.y} $swipeStateX")
                                    transitionState.targetState = true
                                    i2322 = 1

                                    GlobalScope.launch {
                                        delay(800)
                                        dragged22 = true
                                    }
                                }
                            }

                            previousNumber > showNumber && (dragAmount.y >= 2.0 && dragAmount.y > 0) && randomInt == 1 -> {

                                if (dragged22) {
                                    dragged22 = false
                                    Log.d("123123", "MyCardDOWN:${dragAmount.y} $swipeStateX")
                                    transitionState.targetState = true
                                    i2322 = 1
                                    GlobalScope.launch {
                                        delay(800)
                                        dragged22 = true
                                    }
                                }
                            }


                        }
                    }


                }
                .graphicsLayer {

//                if (transition.currentState) {
                    if (i2322 < 2) {
                        if (transition.isRunning) {
                            Log.d("123123", "MyCard2222: ${i2322++}")
                            GlobalScope.launch {

                                for (i in 0..10) {
                                    delay(25)
                                    fadeValue = 1 - i * 0.1f
                                }


                                delay(700)
                                transitionState.targetState = false


                                previousNumber = showNumber
                                showNumber = Random.nextInt(0, 100)
                                randomInt = if (previousNumber > showNumber) {
                                    1
                                } else {
                                    2
                                }

                                when (randomInt) {
                                    2 -> {
                                        bimap = R.drawable.up
                                    }

                                    1 -> {
                                        bimap = R.drawable.down
                                    }

                                }


                                for (i in 0..10) {
                                    delay(25)
                                    fadeValue = 1 - i * 0.1f
                                }
                            }
                        }

                    }
                },

            ) {
            Box(
                modifier = Modifier
                    .size(165.dp)
                    .background(
                        color = Pink80.copy(
                            alpha = if (transitionState.targetState) {
                                fadeValue
                            } else {
                                1 - fadeValue
                            }
                        )
                    )
                    .clip(RoundedCornerShape(12.dp)), contentAlignment = Alignment.Center
            ) {

//                Image(
//                    painter = painterResource(id = bimap),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(165.dp)
//                        .background(
//                            color = BirdColor1.copy(
//                                alpha = if (transitionState.targetState) {
//                                    fadeValue
//                                } else {
//                                    1 - fadeValue
//                                }
//                            )
//                        )
//                )

                Text(
                    modifier = Modifier.wrapContentSize()

//                        .background(
//                            color = Pink80.copy(
//                                alpha = if (transitionState.targetState) {
//                                    fadeValue
//                                } else {
//                                    1 - fadeValue
//                                }
//                            )
//                        )
//                        .clip(RoundedCornerShape(12.dp))
                    ,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 40.sp,
//                    gravity = Alignment.Center,
                    text = "$showNumber"
                )
            }
        }

    }
}


@Preview
@Composable
fun FlickGameS() {
    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize(), Alignment.Center
        ) {
            HighLowComponent2()
        }
    }
}




//
