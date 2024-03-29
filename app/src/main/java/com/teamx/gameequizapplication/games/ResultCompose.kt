package com.teamx.gameequizapplication.games

import androidx.annotation.StringRes
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.AvTimer
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teamx.gameequizapplication.R
import com.teamx.gameequizapplication.ui.theme.CyanGreen
import com.teamx.gameequizapplication.ui.theme.GameEquizApplicationTheme
import com.teamx.gameequizapplication.ui.theme.Pinky
import com.teamx.gameequizapplication.ui.theme.Purple80
import kotlinx.coroutines.launch
import java.util.Locale


@Composable
fun ChartScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Chart Image",
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your Chart Title",
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Preview
@Composable
fun DefaultPreview() {
    GameEquizApplicationTheme {
        ResultScreenToolbar() {}
    }
}

@Composable
fun ShowScoring(title: String, color: Color, score: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Text(text = "$title", color = color)
        Text(text = score)
    }
}


@Composable
fun ShowMeantime(title: String, score: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Default.AvTimer, contentDescription = null)
        Text(modifier = Modifier.padding(6.dp), text = "$title")
        Text(
            modifier = Modifier.padding(6.dp), text = score, fontSize = 14.sp
        )
    }
}

@Preview
@Composable
fun BottomResult() {
    Column(
        modifier = Modifier
            .height(100.dp)
            .width(230.dp)
            .clip(RoundedCornerShape(6))
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Battle Record", fontSize = 24.sp)
        Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceAround) {
            ShowScoring("Highest", Color.LightGray, "0")
            ShowScoring("Lowest", Color.LightGray, "0")
        }
    }

}

@Composable
fun BottomResult2() {
    Column(
        modifier = Modifier
            .height(100.dp)
            .width(130.dp)
            .clip(RoundedCornerShape(6))
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Score", fontSize = 24.sp)
        Icon(imageVector = Icons.Default.Lock, contentDescription = null)
    }

}

@Composable
fun bottomButtons(onContinueClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(6))
            .background(Color.White)
            .fillMaxWidth(),
        Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.width(130.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text(text = "Retry")

        }
        Button(
            onClick = onContinueClicked,
            modifier = Modifier.width(130.dp),
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(CyanGreen)
        ) {
            Text(text = "Back")

        }
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(6.dp),
            colors = ButtonDefaults.buttonColors(CyanGreen)

        ) {
            Icon(imageVector = Icons.Default.Share, contentDescription = null)

        }
    }

}

@Composable
fun BackButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier.clickable { onClick() }, contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = Icons.Default.ArrowBackIos,
            contentDescription = null,
            modifier = Modifier
                .height(56.dp)
                .padding(start = 15.dp)
        )
    }
}


@Composable
fun ResultScreenToolbar(onContinueClicked: () -> Unit) {

    val context = LocalContext.current
    var shouldShowOnboarding2 by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding2) {
        Column(modifier = Modifier.fillMaxHeight(), Arrangement.SpaceAround) {


            Row(modifier = Modifier.background(color = Purple80)) {

                BackButton(onClick = onContinueClicked)
                Text(
                    text = "Result Preview",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterVertically),
                    textAlign = TextAlign.Center,
                    fontSize = 13.sp
                )

            }
            TitleHeader()
//        CircularChart1()
            Row(
                modifier = Modifier.fillMaxWidth(),
                Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ShowScoring("Incorrect", Pinky, "67")
                PieChart(
                    listOf(
                        ChartSlice(4f, CyanGreen), ChartSlice(1f, Pinky)
                    )
                )
                ShowScoring("Correct", CyanGreen, "33")
            }
            ShowMeantime("Mean Time", "1.765s")
            Row(
                modifier = Modifier.fillMaxWidth(), Arrangement.SpaceAround
            ) {
                BottomResult()
                BottomResult2()
            }
            bottomButtons({ shouldShowOnboarding2 = false })
        }
    } else {
//        HighOrLowGameScreen({ shouldShowOnboarding2 = true })
    }
}

@Composable
fun TitleHeader() {
    Row(
        modifier = Modifier.padding(22.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.size(56.dp)

        )
        Text(
            text = "Title",
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 13.dp),

            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}


@Composable
fun CircularChart1(
    values: List<Float> = listOf(65f, 60f, 55f),
    colors: List<Color> = listOf(
        Color(0xFFbe1558), Color(0xFFe75874), Color(0xFFfbcbc9)
    ),
    backgroundCircleColor: Color = Color.LightGray.copy(alpha = 0.3f),
    legend: List<String> = listOf("Mango", "Apple", "Melon"),
    size: Dp = 280.dp,
    thickness: Dp = 16.dp,
    gapBetweenCircles: Dp = 42.dp
) {

    // Convert each value to angle
    val sweepAngles = values.map {
        360 * it / 100
    }

    Canvas(
        modifier = Modifier.size(size)
    ) {

        var arcRadius = size.toPx()

        for (i in values.indices) {

            arcRadius -= gapBetweenCircles.toPx()

            drawCircle(
                color = backgroundCircleColor,
                radius = arcRadius / 2,
                style = Stroke(width = thickness.toPx(), cap = StrokeCap.Butt)
            )

            drawArc(
                color = colors[i],
                startAngle = -90f,
                sweepAngle = sweepAngles[i],
                useCenter = false,
                style = Stroke(width = thickness.toPx(), cap = StrokeCap.Round),
                size = Size(arcRadius, arcRadius),
                topLeft = Offset(
                    x = (size.toPx() - arcRadius) / 2, y = (size.toPx() - arcRadius) / 2
                )
            )

        }

    }

    Spacer(modifier = Modifier.height(32.dp))

    Column {
        for (i in values.indices) {
            DisplayLegend(color = colors[i], legend = legend[i])
        }
    }

}

@Composable
fun DisplayLegend(color: Color, legend: String) {

    Row(
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .background(color = color, shape = CircleShape)
        )

        Spacer(modifier = Modifier.width(4.dp))

        Text(
            text = legend, color = Color.Black
        )
    }
}

/*
@Preview
@Composable
fun PieChart() {
    var animateChart by remember { mutableStateOf(false) }
    val chartValues = listOf(30f, 50f, 20f) // Example chart values

    Column {
        Box(
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val centerX = size.width / 2
                val centerY = size.height / 2
                val radius = size.width / 2
                var startAngle = 0f

                for (value in chartValues) {
                    val sweepAngle =animateFloatAsState(
                        targetValue = if (animateChart) value else 0f,
                        animationSpec = if (animateChart) {
                            tween(durationMillis = 1000, easing = LinearEasing)
                        } else {
                            snap()
                        }
                    ).value

                    drawArc(
                        color = Color.Blue,
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = true,
                        topLeft = Offset(centerX - radius, centerY - radius),
                        size = Size(radius * 2, radius * 2),
                        style = Stroke(width = 100f)
                    )

                    startAngle += sweepAngle
                }
            }
        }

        Button(
            onClick = { animateChart = !animateChart },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = if (animateChart) "Stop Animation" else "Start Animation")
        }
    }
}*/
data class ChartSlice(val value: Float, val color: Color)

@Composable
fun PieChart(chartData: List<ChartSlice>) {
    var animatedFraction by remember { mutableStateOf(0f) }

    val animatedValues = chartData.map {
        Animatable(0f)
    }

    LaunchedEffect(Unit) {
        animatedValues.forEachIndexed { index, animatable ->
            launch {
                animatable.animateTo(
                    targetValue = chartData[index].value,
                    animationSpec = tween(durationMillis = 1500, easing = LinearEasing)
                )
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier

                .size(200.dp)
                .padding(16.dp)

        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val centerX = size.width / 2
                val centerY = size.height / 2
                val radius = size.width / 2

                var startAngle = 0f
                val totalValue = chartData.sumByDouble { it.value.toDouble() }.toFloat()

                chartData.forEachIndexed { index, slice ->
                    val sweepAngle = animatedValues[index].value / totalValue * 360

                    drawArc(
                        color = slice.color,
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = true,
                        topLeft = Offset(centerX - radius, centerY - radius),
                        size = Size(radius * 2, radius * 2),
                        style = Stroke(width = 80f)
                    )

                    startAngle += sweepAngle
                }
            }
        }

        /* Button(
             onClick = { *//* Do something *//* },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Button")
        }*/
    }
}

@Composable
fun HomeSection(
    @StringRes title: Int, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(modifier.fillMaxSize(), Arrangement.SpaceEvenly) {
        Text(
            text = stringResource(title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .fillMaxWidth()
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp),
            textAlign = TextAlign.Center

        )
        content()
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }



    Surface(modifier) {
        if (shouldShowOnboarding) {

//                HomeScreen(Modifier.padding(padding))
            Column(
                modifier
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 16.dp)
            ) {
                Spacer(Modifier.height(16.dp))

                HomeSection(title = R.string.app_name) {

                    OnBoardingScreen(onContinueClicked = { shouldShowOnboarding = false })
                }

                Spacer(Modifier.height(16.dp))

            }

        } else {
            ResultScreenToolbar(onContinueClicked = { shouldShowOnboarding = true })
        }
    }
}

@Composable
fun OnBoardingScreen(
    onContinueClicked: () -> Unit, modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Equiz Game!")
        ElevatedButton(
            modifier = Modifier.padding(vertical = 24.dp), onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }

}


@Composable
fun dpToPx(dp: Dp): Float {
    val density = LocalDensity.current.density
    return dp.value * density
}


@Composable
fun SwipeAbleButton(
    onSwipe: () -> Unit, content: String, backgroundColor: Color, buttonHeight: Dp = 56.dp
) {
    var offsetX by remember { mutableStateOf(0f) }
    var isSwiping by remember { mutableStateOf(false) }

    val fadeAlpha by animateFloatAsState(
        targetValue = if (isSwiping) 0f else 1f, animationSpec = tween(durationMillis = 300)
    )

    Box(modifier = Modifier
        .width(120.dp)
        .pointerInput(Unit) {
            detectDragGestures(
                onDragStart = {
                    isSwiping = true
                },
                onDragEnd = {
                    isSwiping = false
                    if (offsetX < -buttonHeight.toPx() /*|| velocity > 800*/) {
                        onSwipe()
                    } else {
                        offsetX = 0f
                    }
                },
                onDragCancel = {
                    isSwiping = false
                    offsetX = 0f
                }) { change, dragAmount ->
                change.consume()
                offsetX += dragAmount.y
            }
        }) {
        Box(
            modifier = Modifier
                .height(buttonHeight)
                .background(Color.Cyan)
                .offset(y = offsetX.dp)
        ) {
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .fillMaxWidth()

                    .background(Color.Blue)
                    .height(buttonHeight),

                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Text(text = content)
            }
        }

        if (isSwiping) {
            Button(
                onClick = { /* Handle swipe action click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight)
                    .background(Color.Black)
                    .alpha(fadeAlpha),
                colors = ButtonDefaults.buttonColors(
                    contentColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(text = "Remove")
            }
        }
    }
}


@Preview
@Composable
fun foody() {
    var scoring by remember { mutableStateOf(0f) }
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White), Arrangement.Center, Alignment.CenterHorizontally
    ) {

        SwipeAbleButton(
            onSwipe = { scoring++ },
            content = "Swipe to Add Count",
            backgroundColor = Color.Yellow
        )

        Text(text = "Score:$scoring")
    }
}