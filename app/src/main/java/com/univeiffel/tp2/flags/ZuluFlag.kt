package com.example.tp2.flags

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

object ZuluFlag : ICSFlag("Zulu") {
  override val message = "Je demande un remorqueur"

  @Composable
  override fun Flag(modifier: Modifier) {
      Canvas(modifier) {
      val w = size.width
      val h = size.height

      drawPath(
        path = Path().apply {
          moveTo(0f, 0f)
          lineTo(w / 2, h / 2)
          lineTo(0f, h)
          close()
        },
        color = Color.Companion.Black
      )

      drawPath(
        path = Path().apply {
          moveTo(0f, 0f)
          lineTo(w / 2, h / 2)
          lineTo(w, 0f)
          close()
        },
        color = Color.Companion.Yellow
      )

      drawPath(
        path = Path().apply {
          moveTo(0f, h)
          lineTo(w / 2, h / 2)
          lineTo(w, h)
          close()
        },
        color = Color.Companion.Red
      )

      drawPath(
        path = Path().apply {
          moveTo(w, 0f)
          lineTo(w / 2, h / 2)
          lineTo(w, h)
          close()
        },
        color = Color.Companion.Blue
      )
    }
  }
}