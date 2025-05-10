package com.example.tp2.flags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

object QuebecFlag: ICSFlag("quebec") {
    override val message = "Mon navire est indemne, je demande la libre pratique"

    @Composable
    override fun Flag(modifier: Modifier) {
        Box(modifier.background(Color.Companion.Yellow))
    }
}