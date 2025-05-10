package com.univeiffel.tp2

import android.R.attr.maxWidth
import android.os.Bundle
import android.os.SystemClock
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp2.flags.ICSFlag
import com.univeiffel.tp2.ui.theme.TP2Theme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP2Theme {
                FlagGameScreen()
            }
        }
    }
}

@Composable
fun FlagGridTester() {
    var letterClicked by remember { mutableStateOf<Char?>(null) }

    FlagGrid(
        selectedFlag = letterClicked,
        onClick = { clicked ->
            letterClicked = if (letterClicked == clicked) null else clicked
        },
        modifier = Modifier.fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun FlagManagerPreview() {
    TP2Theme {
        FlagManager()
    }
}
//
@Composable
fun FlagGrid(
    flags: List<ICSFlag> = ICSFlag.allFlags.values.sortedBy { it.letter },
    selectedFlag: Char?,
    onClick: (Char) -> Unit,
    modifier: Modifier = Modifier,
    shuffle: Boolean = false
) {
    val flagSize = 64.dp
    val flagPadding = 8.dp
    val displayFlags = if (shuffle) flags.shuffled() else flags

    BoxWithConstraints(modifier = modifier) {
        val flagsPerRow = (this@BoxWithConstraints.maxWidth / (flagSize + flagPadding)).toInt().coerceAtLeast(1)
        val rows = displayFlags.chunked(flagsPerRow)

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(rows) { _, row ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (flag in row) {
                        Box(
                            modifier = Modifier
                                .size(flagSize)
                                .border(
                                    width = 2.dp,
                                    color = if (flag.letter == selectedFlag) Color.Red else Color.Transparent
                                )
                                .clickable {
                                    onClick(flag.letter)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            flag.Flag(Modifier.fillMaxSize())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun FlagInfoBox(flag: Char, modifier: Modifier = Modifier) {
    val icsFlag = ICSFlag.findFlag(flag)

    if (icsFlag != null) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp)
        ) {
            icsFlag.Flag(
                Modifier
                    .weight(20f)
                    .fillMaxHeight()
            )

            Column(
                modifier = Modifier
                    .weight(80f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = icsFlag.codeWord,
                    fontSize = 40.sp
                )
                Text(
                    text = icsFlag.message
                )
            }
        }
    }
}

@Composable
fun FlagManager(modifier: Modifier = Modifier) {
    var selectedFlag by remember { mutableStateOf<Char?>(null) }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        FlagGrid(
            selectedFlag = selectedFlag,
            onClick = { clicked ->
                selectedFlag = if (selectedFlag == clicked) null else clicked
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        )

        if (selectedFlag != null) {
            FlagInfoBox(
                flag = selectedFlag!!,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LetterBarPreview() {
    var selected by remember { mutableStateOf<Char?>(null) }

    LetterBar(
        lines = 2,
        selectedLetter = selected,
        onClick = { clicked ->
            selected = if (clicked == selected) null else clicked
        }
    )
}

@Composable
fun LetterBar(
    lines: Int,
    selectedLetter: Char?,
    onClick: (Char) -> Unit,
    modifier: Modifier = Modifier
) {
    val allLetters = ('A'..'Z').toList()
    val lettersPerLine = (allLetters.size + lines - 1) / lines

    val rows = allLetters.chunked(lettersPerLine)

    Column(
        modifier = modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        rows.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { letter ->
                    Text(
                        text = letter.toString(),
                        fontSize = 20.sp,
                        modifier = Modifier
                            .border(
                                width = 2.dp,
                                color = if (letter == selectedLetter) Color.Blue else Color.Transparent
                            )
                            .padding(8.dp)
                            .clickable { onClick(letter) }
                    )
                }
            }
        }
    }
}

@Composable
fun FlagLetterPairer(
    onPairFound: (Char) -> Unit,
    modifier: Modifier = Modifier,
    flagOrder: List<ICSFlag>
) {
    var selectedFlag by remember { mutableStateOf<Char?>(null) }
    var selectedLetter by remember { mutableStateOf<Char?>(null) }

    var remainingFlags by remember {
        mutableStateOf(flagOrder.toMutableList())
    }

    val remainingLetters = remember(remainingFlags) {
        remainingFlags.map { it.letter }
    }

    LaunchedEffect(selectedFlag, selectedLetter) {
        if (selectedFlag != null && selectedLetter != null) {
            if (selectedFlag == selectedLetter) {
                remainingFlags.removeAll { it.letter == selectedFlag }
                onPairFound(selectedFlag!!)
            }
            selectedFlag = null
            selectedLetter = null
        }
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isPortrait = this@BoxWithConstraints.maxHeight > this@BoxWithConstraints.maxWidth
        val letterLines = if (isPortrait) 2 else 1

        Column(modifier = Modifier.fillMaxSize()) {
            // Barre de lettres
            LetterBar(
                lines = letterLines,
                selectedLetter = selectedLetter,
                onClick = { clicked ->
                    selectedLetter = if (selectedLetter == clicked) null else clicked
                },
                modifier = Modifier.fillMaxWidth()
            )

            FlagGrid(
                flags = remainingFlags,
                selectedFlag = selectedFlag,
                onClick = { clicked ->
                    selectedFlag = if (selectedFlag == clicked) null else clicked
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                ,shuffle = false
            )
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlagGameScreen() {
    val allFlags = ICSFlag.allFlags.values.sortedBy { it.letter }

    var startTime by rememberSaveable { mutableStateOf(SystemClock.elapsedRealtime()) }
    var elapsedTime by rememberSaveable { mutableStateOf(0L) }

    var foundPairs by rememberSaveable { mutableStateOf(0) }
    val totalPairs = allFlags.size

    var attempts by rememberSaveable { mutableStateOf(0) }

    var flagOrder by rememberSaveable { mutableStateOf(allFlags.shuffled()) }
    var cheatMode by rememberSaveable { mutableStateOf(false) }

    // Timer
    LaunchedEffect(startTime) {
        while (true) {
            elapsedTime = (SystemClock.elapsedRealtime() - startTime) / 1000
            delay(1000)
        }
    }

    // Cheat mode
    LaunchedEffect(cheatMode) {
        if (cheatMode) {
            flagOrder = allFlags.sortedBy { it.letter }
            delay(2000)
            flagOrder = allFlags.shuffled()
            cheatMode = false
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Jeu d’appariement") },
                actions = {
                    TextButton(onClick = {
                        startTime = SystemClock.elapsedRealtime()
                        foundPairs = 0
                        attempts = 0
                        flagOrder = allFlags.shuffled()
                    }) {
                        Text("Redémarrer")
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Text(
                    "Paires : $foundPairs / $totalPairs",
                    modifier = Modifier.weight(1f).padding(start = 8.dp)
                )
                Text(
                    "Tentatives : $attempts",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    "Temps : ${elapsedTime}s",
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { cheatMode = true }) {
                Text("👁️")
            }
        }
    ) { innerPadding ->
        FlagLetterPairer(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            flagOrder = flagOrder,
            onPairFound = {
                foundPairs++
                attempts++
            }
        )
    }
}
