package com.example.tp2.flags

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

object OscarFlag: ICSFlag("oscar") {
    override val message = "Homme à la mer"

    @Composable
    override fun Flag(modifier: Modifier) {
        Canvas(modifier) {
            val width = size.width
            val height = size.height

            val topTriangle = Path().apply {
                moveTo(0f, 0f)
                lineTo(width, 0f)
                lineTo(0f, height)
                close()
            }

            val bottomTriangle = Path().apply {
                moveTo(width, 0f)
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }

            drawPath(topTriangle, color = Color.Companion.Yellow)
            drawPath(bottomTriangle, color = Color.Companion.Red)
        }
    }
}