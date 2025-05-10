package com.example.tp2.flags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


object XrayFlag : ICSFlag("whiskey") {
    override val message = "Arrêtez vos manœuvres et veillez mes signaux."

    @Composable
    override fun Flag(modifier: Modifier) {
        Box(modifier = modifier.background(Color.White)) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.25f)
                    .background(Color.Blue)
                    .align(Alignment.Center)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .background(Color.Blue)
                    .align(Alignment.Center)
            )
        }
    }
}

