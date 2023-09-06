package com.example.playground.ui.screens

import android.content.res.Resources
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.RepeatableSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomIndicator(
    modifier: Modifier,
    percentage: Float,
    indicatorColor: Color,
    backgroundColor: Color,
    icon: ImageVector
) {
    var iconSize by remember {
        mutableStateOf(0f)
    }
    val targetValue = percentage * 360f / 100f
    val animatedValue = remember { Animatable(initialValue = 0f) }
    LaunchedEffect(key1 = targetValue) {
        animatedValue.animateTo(
            targetValue = targetValue,
            animationSpec = RepeatableSpec(
                iterations = 1,
                animation = tween(durationMillis = 2200, easing = LinearOutSlowInEasing),
                RepeatMode.Restart
            )
        )
    }
    Box(modifier = modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
        ) {
            drawArc(
                color = backgroundColor,
                useCenter = false,
                startAngle = 150f,
                sweepAngle = 360f,
                topLeft = Offset(0f, 0f),
                style = Stroke(50f, cap = StrokeCap.Round)
            )

            drawArc(
                color = indicatorColor,
                useCenter = false,
                startAngle = 150f,
                sweepAngle = animatedValue.value,
                topLeft = Offset(0f, 0f),
                style = Stroke(50f, cap = StrokeCap.Round)

            )
            iconSize = size.width

        }
        Icon(
            imageVector = icon,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .size((iconSize / 2.5f).toDp())
        )
    }
}

private fun Float.toDp(): Dp {
    return Dp(this / Resources.getSystem().displayMetrics.density)
}

@Preview
@Composable
fun customIndicatorPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        CustomIndicator(
            modifier = Modifier
                .size(300.dp)
                .padding(10.dp)
                .align(Alignment.Center),
            percentage = 68f,
            indicatorColor = Color.Magenta,
            backgroundColor = Color.LightGray,
            icon = Icons.Rounded.ShoppingCart
        )
    }
}





