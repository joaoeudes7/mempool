package com.jedev.mempool.utils

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.roundedCorners(
    radius: Dp = 8.dp,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(radius),
    color: Color = Color.Transparent,
    borderSize: Dp = 1.dp
) = then(
    Modifier
        .clip(roundedCornerShape)
        .border(borderSize, color, roundedCornerShape)
)
