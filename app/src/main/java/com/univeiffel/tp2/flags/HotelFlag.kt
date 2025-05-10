package com.example.tp2.flags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

object HotelFlag : ICSFlag("hotel") {
    override val message = "J'ai un pilote à bord."

    @Composable
    override fun Flag(modifier: Modifier) {
        Row(modifier) {
            Box(
                modifier = Modifier.Companion
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Companion.White)
            )
            Box(
                modifier = Modifier.Companion
                    .weight(1f)
                    .fillMaxHeight()
                    .background(Color.Companion.Red)
            )
        }
    }
}