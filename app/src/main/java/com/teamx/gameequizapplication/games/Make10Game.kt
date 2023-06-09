package com.teamx.gameequizapplication.games

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class Make10Game {
}


//make 10


/*@Composable
fun Make10GameScreen() {
    val availableCards = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val selectedCards = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Make 10",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (card in availableCards) {
                Button(
                    onClick = {
                        if (selectedCards.sum() + card <= 10) {
                            selectedCards.add(card)
                        }
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedCards.contains(card)) Color.Gray else Color.White
                    ),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = card.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selected Cards: ${selectedCards.joinToString(", ")}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { selectedCards.clear() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Clear")
        }
    }
}

@Preview
@Composable
fun PreviewMake10GameScreen() {
    Make10GameScreen()
}*/


@Composable
fun Make10GameScreen(content: @Composable () -> Unit) {
    val availableCards = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val selectedCards = remember { mutableStateListOf<Int>() }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Make 10",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            availableCards.forEach { card ->
                Button(
                    onClick = {
                        if (selectedCards.sum() + card <= 10) {
                            selectedCards.add(card)
                        }
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (selectedCards.contains(card)) Color.Gray else Color.White
                    ),
                    border = BorderStroke(1.dp, Color.Black)
                ) {
                    Text(text = card.toString())
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Selected Cards: ${selectedCards.joinToString(", ")}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { selectedCards.clear() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Clear")
        }
    }
}

/*@Preview
@Composable
fun PreviewMake10GameScreen() {
    Make10GameScreen()
}*/
//make 10