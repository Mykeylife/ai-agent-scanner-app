package com.aiagent.scanner.utils

import android.graphics.Bitmap
import android.graphics.Color
import kotlin.math.pow
import kotlin.math.sqrt

class InkRemovalProcessor {
    
    fun removeInk(bitmap: Bitmap): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        for (y in 0 until height) {
            for (x in 0 until width) {
                val pixel = bitmap.getPixel(x, y)
                val r = Color.red(pixel)
                val g = Color.green(pixel)
                val b = Color.blue(pixel)
                val a = Color.alpha(pixel)

                // AI-based ink detection: Dark colors (ink) and high saturation
                val brightness = (0.299 * r + 0.587 * g + 0.114 * b).toInt()
                val saturation = calculateSaturation(r, g, b)

                // If it's ink-like (dark + saturated), lighten it
                val isInk = brightness < 100 && saturation > 0.3
                
                val newPixel = if (isInk) {
                    // Apply correction fluid effect: lighten significantly
                    val newR = (r + (255 - r) * 0.7).toInt().coerceIn(0, 255)
                    val newG = (g + (255 - g) * 0.7).toInt().coerceIn(0, 255)
                    val newB = (b + (255 - b) * 0.7).toInt().coerceIn(0, 255)
                    Color.argb(a, newR, newG, newB)
                } else {
                    // Keep light areas bright (paper white)
                    Color.argb(a, r, g, b)
                }

                result.setPixel(x, y, newPixel)
            }
        }

        // Apply smoothing filter
        return applyBilateralFilter(result, 5, 50)
    }

    private fun calculateSaturation(r: Int, g: Int, b: Int): Double {
        val max = maxOf(r, g, b).toDouble()
        val min = minOf(r, g, b).toDouble()
        return if (max == 0.0) 0.0 else (max - min) / max
    }

    private fun applyBilateralFilter(bitmap: Bitmap, radius: Int, colorSigma: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

        for (y in 0 until height) {
            for (x in 0 until width) {
                var totalR = 0.0
                var totalG = 0.0
                var totalB = 0.0
                var totalWeight = 0.0

                val centerPixel = bitmap.getPixel(x, y)
                val centerR = Color.red(centerPixel)
                val centerG = Color.green(centerPixel)
                val centerB = Color.blue(centerPixel)

                for (dy in -radius..radius) {
                    for (dx in -radius..radius) {
                        val nx = (x + dx).coerceIn(0, width - 1)
                        val ny = (y + dy).coerceIn(0, height - 1)

                        val pixel = bitmap.getPixel(nx, ny)
                        val r = Color.red(pixel)
                        val g = Color.green(pixel)
                        val b = Color.blue(pixel)

                        val spatialDistance = sqrt((dx * dx + dy * dy).toDouble())
                        val colorDistance = sqrt(
                            (r - centerR).toDouble().pow(2) +
                            (g - centerG).toDouble().pow(2) +
                            (b - centerB).toDouble().pow(2)
                        )

                        val spatialWeight = Math.exp(-(spatialDistance * spatialDistance) / (2 * radius * radius))
                        val colorWeight = Math.exp(-(colorDistance * colorDistance) / (2 * colorSigma * colorSigma))
                        val weight = spatialWeight * colorWeight

                        totalR += r * weight
                        totalG += g * weight
                        totalB += b * weight
                        totalWeight += weight
                    }
                }

                val newR = (totalR / totalWeight).toInt().coerceIn(0, 255)
                val newG = (totalG / totalWeight).toInt().coerceIn(0, 255)
                val newB = (totalB / totalWeight).toInt().coerceIn(0, 255)
                val newA = Color.alpha(centerPixel)

                result.setPixel(x, y, Color.argb(newA, newR, newG, newB))
            }
        }

        return result
    }
}
