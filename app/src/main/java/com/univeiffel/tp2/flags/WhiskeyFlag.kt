package com.example.tp2.flags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object WhiskeyFlag: ICSFlag("whiskey") {
    override val message = "J’ai besoin d’assistance médicale."

    @Composable
    override fun Flag(modifier: Modifier) {
        Box(modifier = modifier.background(Color.Blue)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.3f)
                        .background(Color.Red)
                )
            }
        }
    }
}