package com.jedev.mempool.utils

import androidx.compose.runtime.Composable
import com.jedev.mempool.ui.theme.MemPoolTheme

@Composable
fun PreviewMD(
    content: @Composable () -> Unit
) = MemPoolTheme {
    content()
}