package com.teamx.gameequizapplication.games

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.teamx.gameequizapplication.GamesUID
import com.teamx.gameequizapplication.R
import kotlin.random.Random

@Composable
fun WeatherCastGame(content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        content()
        weatherCastGamePlot()
    }
}

@Composable
fun weatherCastGamePlot() {
    val leftItems = (0..(2)).map {
        WeatherListItem(
            height = 70.dp, id = it, gamesUID = GamesUID.values()[it], color = if (it % 5 == 0) {
                Color(0xFFF44336).copy(alpha = 1f)
            } else if (it % 2 == 0) {
                Color(0xFF4CAF50).copy(alpha = 1f)
            } else {
                Color(0xFF00BCD4).copy(alpha = 1f)
            }, gameObject = EnumWeather.values().get(it)/*if (it % 5 == 0) {
                EnumWeather.SUN
            } else if (it % 2 == 0) {
                EnumWeather.CLOUD
            } else {
                EnumWeather.DROP
            }*/
        )
    }
    val leftBoxes by remember { mutableStateOf(leftItems) }
    var imageCheckObj by remember { mutableStateOf<EnumWeather>(EnumWeather.CLOUD) }
    var imageRandom by remember { mutableStateOf<ImageVector?>(null) }
    var gameRand by remember { mutableStateOf<Int>(0) }
    var counter by remember { mutableStateOf<Int>(0) }

    when (imageCheckObj) {
        EnumWeather.SUN -> {
            imageRandom = ImageVector.vectorResource(id = androidx.core.R.drawable.ic_call_answer)

        }

        EnumWeather.CLOUD -> {

            imageRandom = ImageVector.vectorResource(id = R.drawable.ic_launcher_background)
        }

        EnumWeather.DROP -> {
            imageRandom =
                ImageVector.vectorResource(id = androidx.core.R.drawable.ic_call_answer_video)
        }

        EnumWeather.CLOUD_SUN -> {
            imageRandom = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground)
        }

        EnumWeather.CLOUD_DROP -> {
            imageRandom = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground)
        }

        EnumWeather.DROP_SUN -> {
            imageRandom = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground)
        }

        EnumWeather.DROP_CLOUD -> {
            imageRandom = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground)
        }

        EnumWeather.SUN_DROP -> {
            imageRandom = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground)
        }

        EnumWeather.SUN_CLOUD -> {
            imageRandom = ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground)
        }
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        /*Image(
            modifier = Modifier.size(10.dp),
            painterResource(id = checkStringReturnDrawable(imageCheckObj.name.toString())),
            contentDescription = "image"
        )*/
        Image(
            modifier = Modifier.size(130.dp),
            painter = painterResource(id = checkStringReturnDrawable(imageCheckObj.name.toString())),
            contentDescription = ""
        )
        Text(
            modifier = Modifier.size(1.dp),
            text = imageCheckObj.name,
            textAlign = TextAlign.Center
        )
        Row() {
            leftBoxes.forEach {
                weatherDrop(item = it) {
                    if (imageCheckObj.name == it.gameObject.name) {
                        gameRand = Random.nextInt(0, 9)
                        imageCheckObj = EnumWeather.values()[gameRand]
                        counter++
                    } else if (!imageCheckObj.name.contains(it.gameObject.name) && imageCheckObj.name.contains(
                            "_"
                        )
                    ) {
                        gameRand = Random.nextInt(0, 9)
                        imageCheckObj = EnumWeather.values()[gameRand]
                        counter++
                    }   /*else if (imageCheckObj.name == it.gameObject.name) {
//                        imageCheckObj = it.gameObject
                        gameRand = Random.nextInt(0, 9)
                        imageCheckObj = EnumWeather.values()[gameRand]
                        counter++
                    }*/
                }

            }
        }

    }
}


@Preview
@Composable
fun previewWeatherCastGame() {
    MaterialTheme {
        WeatherCastGame() {}
    }
}

@Composable
fun weatherDrop(item: WeatherListItem, onClick: () -> Unit) {
    val context = LocalContext.current


    Box(
        modifier = Modifier
            .padding(vertical = 120.dp, horizontal = 9.dp)
            .width(90.dp)
            .height(item.height)
            .clip(RoundedCornerShape(10.dp))
//            .background(item.color)
            .clickable {
                onClick()
            }, contentAlignment = Alignment.Center


    ) {
        Column(

        ) {
            Image(
                painter = painterResource(id = checkStringReturnDrawable(item.gameObject.name.toString())),
                contentDescription = ""
            )
            Text(
                text = item.gameObject.name.toString(), style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

fun checkStringReturnDrawable(str: String): Int {
    return when (str) {
        "SUN" -> {

            R.drawable.sun_red
        }

        "SUN_CLOUD" -> {

            R.drawable.sun_bule
        }

        "SUN_DROP" -> {

            R.drawable.sun_gray
        }

        "CLOUD" -> {

            R.drawable.cloud_blue
        }

        "CLOUD_SUN" -> {

            R.drawable.cloud_red
        }

        "CLOUD_DROP" -> {

            R.drawable.cloud_gray
        }

        "DROP" -> {
            R.drawable.drop_gray
        }

        "DROP_CLOUD" -> {
            R.drawable.drop_blue
        }

        "DROP_SUN" -> {
            R.drawable.drop_red
        }

        else -> {
            R.drawable.drop_red
        }
    }
}

data class WeatherListItem(
    var id: Int,
    var height: Dp,
    var gameObject: EnumWeather,
    var color: Color,
    var gamesUID: GamesUID,
)

enum class EnumWeather {
    DROP, CLOUD, SUN, DROP_CLOUD, DROP_SUN, SUN_CLOUD, SUN_DROP, CLOUD_DROP, CLOUD_SUN
}