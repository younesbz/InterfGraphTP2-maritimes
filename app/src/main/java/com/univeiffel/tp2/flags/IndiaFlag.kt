package com.example.tp2.flags

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

object IndiaFlag: ICSFlag("india") {
    override val message = "Je viens sur bâbord."
    @Composable
    override fun Flag(modifier: Modifier) {
        Column(modifier) {
            Box(
                Modifier.Companion.fillMaxWidth().weight(1f).background(Color.Companion.Yellow)
            );//Modifier avec M majuscule crée un modifier vide pour rajouter des éléments
            Box(Modifier.Companion.fillMaxWidth().weight(1f).background(Color.Companion.Yellow)) {
                Canvas(Modifier.Companion.fillMaxSize()) {
                    drawCircle(
                        color = Color.Companion.Black
                    )
                }
            };

            Box(Modifier.Companion.fillMaxWidth().weight(1f).background(Color.Companion.Yellow));
        }
    }
}