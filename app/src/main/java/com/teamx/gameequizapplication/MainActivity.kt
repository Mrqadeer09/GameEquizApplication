package com.teamx.gameequizapplication

import android.os.Build
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teamx.gameequizapplication.games.*
import com.teamx.gameequizapplication.ui.theme.GameEquizApplicationTheme
import com.teamx.gameequizapplication.utils.grids
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameEquizApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {

                    Navigation()

                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun startUp(modifier: Modifier) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(

        ) {
            Navigation()
            /* Text(text = "Loading...", modifier = Modifier.padding(vertical = 48.dp))
             LoadingAnimation()
             grids()*/
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GameEquizApplicationTheme {
        startUp(Modifier)

    }
}


class Hexagon(private val size: Size) {
    private val radius: Float = size.width / 2f

    private val angles = listOf(
        0f, 60f, 120f, 180f, 240f, 300f
    )

    fun toPath(): Path {
        val path = Path()

        for (i in 0 until 6) {
            val angleRad = Math.toRadians(angles[i].toDouble())
            val x = size.width / 2f + radius * Math.cos(angleRad).toFloat()
            val y = size.height / 2f + radius * Math.sin(angleRad).toFloat()

            val point = if (i == 0) {
                Offset(x, y)
            } else {
                path.lineTo(x, y)
                null
            }

            if (i == 5) {
                point?.let { path.lineTo(it.x, it.y) }
            }
        }

        return path
    }
}


//lazycolum//
@Composable
fun LazyListExample() {
    val itemsList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

    LazyColumn {
        items(itemsList) { item ->
            ListItem(item = item)
        }
    }
}

@Composable
fun ListItem(item: String) {
    Surface(
        color = Color.White,
        shadowElevation = 2.dp,
        modifier = androidx.compose.ui.Modifier.padding(8.dp)
    ) {
        Text(
            text = item, style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold, color = Color.Black
            ), modifier = androidx.compose.ui.Modifier.padding(16.dp)
        )
    }
}

@Preview
@Composable
fun PreviewLazyListExample() {
    LazyListExample()
}

/*@ExperimentalFoundationApi
@Composable
fun LazyGridExample() {
    val itemsList = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9")

    LazyVerticalGrid(
        cells = GridCells.Fixed(3)
    ) {
        items(itemsList) { item ->
            GridItem(item = item)
        }
    }
}

@Composable
fun GridItem(item: String) {
    Card(
        modifier = Modifier.padding(8.dp),
        backgroundColor = Color.White,
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Surface(
            modifier = Modifier.padding(16.dp),
            color = Color.White
        ) {
            Text(
                text = item,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewLazyGridExample() {
    LazyGridExample()
}*/
//

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splashScreen") {

        composable("splashScreen") {
            SplashScreen(navController = navController)
        }

        composable("mainScreen") {
            MainScreen(navController = navController)
        }
        composable(GamesUID.DashboardScreen.name) {
//            MainScreen()
        }

        composable(GamesUID.AdditionScreen.name) {
            AdditionAddictionGame() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.BirdWatchingScreen.name) {
            BirdWatchingGame() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.BreakTheBlockScreen.name) {
            BreakTheBlockGame() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.ColorDeceptionScreen.name) {
            TouchTheColorsGameScreen() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.ColorSwitchScreen.name) {
            ColorSwitchGameScreen() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.ConcentrationScreen.name) {
            ConcentrationGame() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.CardCalculationGameScreen.name) {
            CardCalculationGameScreen() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.FlickScreen.name) {
            SwipeableComponent() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.FollowTheLeaderScreen.name) {
            FollowTheLeaderGame() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.HexaChainScreen.name) {
            HexaChainGameScreen() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.HighLowScreen.name) {
            HighOrLowGameScreen(content = { ToolbarPreview(navController = navController) }) {}
        }
        composable(GamesUID.LearningThingScreen.name) {
            Main() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.MakeTenScreen.name) {
            Make10GameScreen() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.MatchingScreen.name) {
            MatchingStepGame(Modifier.padding(2.dp)) { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.MissingPieceScreen.name) {
            MissingPieceGameScreen() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.OperationsScreen.name) {
            GameScreen() { ToolbarPreview(navController = navController) }
        }

        composable(GamesUID.QuickEyeScreen.name) {
            QuickEyeGame() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.RainFallScreen.name) {
            RainFallGame() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.ResultScreen.name) {
//            MainScreen()
        }
        composable(GamesUID.ReverseRpsScreen.name) {
            ReverseRockPaperScissorsGameScreen() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.SimplicityScreen.name) {
            ImplicityGameScreen() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.SpinningBlockScreen.name) {
            SpinningBlockGame() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.TapTheColorScreen.name) {
            TapTheColorGame() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.TenSecondScreen.name) {
//            MainScreen()
        }
        composable(GamesUID.TouchTheNumScreen.name) {
            TouchTheNumbersGameScreen() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.TouchTheNumPlusScreen.name) {
            TouchTheNumPlusGame() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.UnfollowTheLeaderScreen.name) {
            UnfollowTheLeaderGame() { ToolbarPreview(navController = navController) }
        }
        composable(GamesUID.WeatherTheLeaderScreen.name) {
            WeatherCastGame() { ToolbarPreview(navController = navController) }
        }

    }


}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.9f,
            animationSpec = tween(durationMillis = 500, /*delayMillis = 50, */easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            })
        )

        delay(3000L)
        navController.navigate("mainScreen")
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .clip(RoundedCornerShape(13.dp)),
        contentAlignment = Alignment.Center
    ) {


        Icon(
            modifier = Modifier
                .size(145.dp)
                .scale(scale.value),
            imageVector = Icons.Default.Spa,
            contentDescription = "SplashScreen"
        )

    }


}

@Composable
fun MainScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .background(Color.White)
            .clip(RoundedCornerShape(13.dp)),
        contentAlignment = Alignment.Center,

        ) {

        Text(
            style = MaterialTheme.typography.bodySmall, text = "MainScreen"
        )
        grids(navController)
    }

}

@Composable
fun GeneralScreen(text: String) {

    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .clip(RoundedCornerShape(13.dp)),
        contentAlignment = Alignment.Center,

        ) {

        Text(
            style = MaterialTheme.typography.bodySmall, text = text
        )

    }

}


enum class GamesUID {
       DashboardScreen, AdditionScreen, BirdWatchingScreen, BreakTheBlockScreen, ColorDeceptionScreen, ColorSwitchScreen, ConcentrationScreen,CardCalculationGameScreen, FlickScreen, FollowTheLeaderScreen, HexaChainScreen, HighLowScreen, LearningThingScreen, MakeTenScreen, MatchingScreen, MissingPieceScreen, OperationsScreen, QuickEyeScreen, RainFallScreen, ResultScreen, ReverseRpsScreen, SimplicityScreen, SpinningBlockScreen, TapTheColorScreen, TenSecondScreen, TouchTheNumScreen, TouchTheNumPlusScreen, UnfollowTheLeaderScreen, WeatherTheLeaderScreen
}

