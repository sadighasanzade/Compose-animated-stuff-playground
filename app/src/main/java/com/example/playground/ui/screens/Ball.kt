package com.example.playground.ui.screens

import android.content.res.Resources
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun MobileBall() {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val screenWidth = LocalConfiguration.current.screenWidthDp.toPx()
    val screenHeight = LocalConfiguration.current.screenHeightDp.toPx()

    val ballY by infiniteTransition.animateFloat(
        initialValue = 100f,
        targetValue = screenHeight,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    val color by infiniteTransition.animateColor(
        initialValue = Color.Blue,
        targetValue = Color.Red,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = color,
                radius = 100f,
                center = Offset(screenWidth / 2, ballY),
            )
        }
    }
}

private fun Int.toPx(): Float {
    return this * Resources.getSystem().displayMetrics.density
}