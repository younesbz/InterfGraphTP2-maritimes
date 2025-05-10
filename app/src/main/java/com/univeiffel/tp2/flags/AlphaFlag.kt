package com.example.tp2.flags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.tp2.flags.ICSFlag

object AlphaFlag: ICSFlag("alpha") {
    override val message = "J'ai un scaphandrier en plongée; tenez-vous à distance et avancez lentement."
    private val triangle = GenericShape { size, _ ->
        moveTo(0f, 0f)
        lineTo(size.width, 0f)
        lineTo(size.width / 2, size.height / 2)
        lineTo(size.width, size.height)
        lineTo(0f, size.height)
        lineTo(0f, 0f)
        close()
    }

    @Composable
    override fun Flag(modifier: Modifier) {
        Box(modifier = Modifier.Companion.fillMaxSize()) {
            Row(Modifier.Companion.fillMaxSize()) {
                Box(
                    Modifier.Companion.fillMaxHeight().weight(1f / 2f, fill = true)
                        .background(color = Color.Companion.White)
                )
                Box(
                    Modifier.Companion.fillMaxHeight().weight(1f / 2f, fill = true)
                        .background(color = Color.Companion.Blue, shape = triangle)
                )
            }
        }
    }
}