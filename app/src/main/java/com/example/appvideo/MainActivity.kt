package com.example.appvideo

import android.R
import android.os.Bundle
import android.transition.Slide
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appvideo.ui.theme.AppvideoTheme

// Adicionais
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Alignment

import android.net.Uri
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.unit.dp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppvideoTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Tela(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun Tela(modifier: Modifier = Modifier) {

    var volume = remember { mutableStateOf(0f) }

    // Configurações

// Fim das configurações
    // Tela
    Box(
        modifier = Modifier
            .padding(all = 10.dp)
            .fillMaxSize(),
            //.background(Color.White)
        contentAlignment = Alignment.Center
    ) {
        // Controle de mídia
        Column() {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                   // .background(Color.Yellow),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(modifier = modifier.padding(top = 100.dp)) {
                    Button(
                        onClick = {},
                        contentPadding = PaddingValues(
                            horizontal = 50.dp, // Mais espaço nas laterais
                            vertical = 12.dp    // Mais espaço em cima e embaixo
                        ),
                        modifier = Modifier.padding(end = 10.dp)
                    ) {
                        Text(
                            text = "A",
                            fontSize = 40.sp
                        )
                    }
                    Button(
                        onClick = {},
                        contentPadding = PaddingValues(
                            horizontal = 50.dp, // Mais espaço nas laterais
                            vertical = 12.dp    // Mais espaço em cima e embaixo
                        )
                    ) {
                        Text(
                            text = "B",
                            fontSize = 40.sp
                        )
                    }
                }
                // Menu volume
                Surface(
                    shape = RoundedCornerShape(40.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .background(Color.White)
                            .padding(all = 15.dp),
                    ) {
                        Slider(
                            value = volume.value,
                            onValueChange = { volume.value = it },
                            valueRange = 0f..100f,
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .width(250.dp)
                        )
                        Text(
                            text = "\uD83D\uDD0A: ${String.format("%.0f", volume.value)}"
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .widthIn(max = 350.dp)
                            .heightIn(max = 300.dp)
                            .fillMaxSize()
                    ) {

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextButton(onClick = {}) {
                                Text(
                                    text = "Vídeo 1",
                                    fontSize = 30.sp,
                                    modifier = Modifier.padding(top = 20.dp)
                                )
                            }
                            TextButton(onClick = {}) {
                                Text(
                                    text = "Vídeo 2",
                                    fontSize = 30.sp,
                                    modifier = Modifier.padding(top = 20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AppvideoTheme {
            Tela()
        }
    }
