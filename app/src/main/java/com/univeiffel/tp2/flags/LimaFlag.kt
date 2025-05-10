package com.example.tp2.flags

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

object LimaFlag: ICSFlag("lima") {
    override val message = "Stoppez votre navire immédiatement. "
    @Composable
    override fun Flag(modifier: Modifier) {
        Row(modifier) {
            Column(Modifier.Companion.fillMaxWidth().weight(1f)) {
                Box(
                    Modifier.Companion.fillMaxWidth().weight(1f).background(Color.Companion.Yellow)
                )
                Box(
                    Modifier.Companion.fillMaxWidth().weight(1f).background(Color.Companion.Black)
                )
            };
            Column(Modifier.Companion.fillMaxWidth().weight(1f)) {
                Box(
                    Modifier.Companion.fillMaxWidth().weight(1f).background(Color.Companion.Black)
                )
                Box(
                    Modifier.Companion.fillMaxWidth().weight(1f).background(Color.Companion.Yellow)
                )
            };
        }


        Box(modifier) {

            Column(Modifier.Companion.fillMaxWidth().background(Color.Companion.Yellow)) { };
        }

    }
}