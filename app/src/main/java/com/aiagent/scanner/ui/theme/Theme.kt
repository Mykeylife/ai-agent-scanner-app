package com.aiagent.scanner.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF6D28D9),
    secondary = Color(0xFF06B6D4),
    tertiary = Color(0xFF7C3AED),
    background = Color(0xFF1F2937),
    surface = Color(0xFF111827)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF7C3AED),
    secondary = Color(0xFF06B6D4),
    tertiary = Color(0xFFA855F7),
    background = Color(0xFFFAFAFA),
    surface = Color(0xFFFFFFFF)
)

@Composable
fun AIAgentScannerTheme(content: @Composable () -> Unit) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
