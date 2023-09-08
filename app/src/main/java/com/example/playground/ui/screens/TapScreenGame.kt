package com.example.playground.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TapGameArena() {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    var gameIsRunning by remember { mutableStateOf(true) }
    var heightForPlayer1 by remember { mutableStateOf(screenHeight / 2) }
    var heightForPlayer2 by remember { mutableStateOf(screenHeight / 2) }
    var winner by remember { mutableStateOf("") }
    if (!gameIsRunning) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$winner is winner!",
                textAlign = TextAlign.Center,
                color = Color.Red,
                fontSize = 20.sp
            )
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .clickable {
                        heightForPlayer2 -= 20.dp
                        heightForPlayer1 += 20.dp
                        if (heightForPlayer1 >= screenHeight) {
                            gameIsRunning = false
                            winner = "Blue"
                        }
                    }
                    .fillMaxWidth()
                    .height(heightForPlayer1)
                    .background(
                        color = Color.Blue
                    )
            ) {
            }
            Box(
                modifier = Modifier
                    .clickable {
                        heightForPlayer1 -= 20.dp
                        heightForPlayer2 += 20.dp
                        if (heightForPlayer2 >= screenHeight) {
                            gameIsRunning = false
                            winner = "Red"
                        }
                    }
                    .fillMaxWidth()
                    .height(heightForPlayer2)
                    .background(color = Color.Red)
            )
            {
            }
        }
    }
}

@Preview
@Composable
fun GamePreview() {
    TapGameArena()
}