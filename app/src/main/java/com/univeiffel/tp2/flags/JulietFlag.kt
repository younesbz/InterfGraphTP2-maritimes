package com.example.tp2.flags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

object JulietFlag: ICSFlag("juliet") {
    override val message = "En feu et je transporte des marchandises dangereuses"

    @Composable
    override fun Flag(modifier: Modifier) {
        Column(modifier) {
            Box(Modifier.Companion.weight(1f).fillMaxWidth().background(Color.Companion.Blue))
            Box(Modifier.Companion.weight(1f).fillMaxWidth().background(Color.Companion.White))
            Box(Modifier.Companion.weight(1f).fillMaxWidth().background(Color.Companion.Blue))
        }
    }
}