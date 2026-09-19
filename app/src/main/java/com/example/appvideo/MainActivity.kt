package com.example.appvideo

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.appvideo.ui.theme.AppvideoTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.LaunchedEffect



class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AppvideoTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Tela(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun VideoPlayer(
    url: Uri,
    isPlaying: Boolean,
    videovolume: Float,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val exoPlayer = remember {

        ExoPlayer.Builder(context)
            .build()
            .apply {

                setMediaItem(
                    MediaItem.fromUri(url)
                )

                prepare()
            }
    }

    LaunchedEffect(url) {
        val mediaItem = MediaItem.fromUri(url)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    LaunchedEffect(isPlaying) {
        exoPlayer.playWhenReady = isPlaying
    }

    LaunchedEffect(videovolume) {
        exoPlayer.volume = videovolume
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            exoPlayer.release()
        }
    }


    AndroidView(
        modifier = modifier,

        factory = { context ->

            PlayerView(context).apply {

                player = exoPlayer

                useController = false
            }
        }
    )
}


@Composable
fun Tela(
    modifier: Modifier = Modifier
) {

    var volume by remember {
        mutableFloatStateOf(0f)
    }
    val context = LocalContext.current
    var video by remember { mutableStateOf(Uri.parse("android.resource://${context.packageName}/${R.raw.video1}")) }

    var isPlaying by remember{ mutableStateOf(true)}


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
            .padding(top = 100.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {



        VideoPlayer(
            url = video,
            isPlaying = isPlaying,
            videovolume = volume,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
        )



        Row(
            modifier = Modifier.padding(top = 20.dp),
                //.background(Color.Yellow),
            horizontalArrangement = Arrangement.Center
        ) {

            // Botão play/pause
            Button(
                onClick = {
                    isPlaying = !isPlaying
                          },

                contentPadding = PaddingValues(
                    horizontal = 80.dp,
                    vertical = 12.dp
                ),

                modifier = Modifier.padding(end = 10.dp)
            ) {

                Text(
                    text = if(isPlaying){"❚❚"}else{"▶\uFE0E"},
                    fontSize = 30.sp
                )
            }

            // Botão 2
            Button(
                onClick = {
                    video = Uri.parse("android.resource://${context.packageName}/${R.raw.video2}")
                },

                contentPadding = PaddingValues(
                    horizontal = 80.dp,
                    vertical = 12.dp
                )
            ) {

                Text(
                    text = "⛶",
                    fontSize = 30.sp
                )
            }
        }


    // Controle do volume
        Surface(
            shape = RoundedCornerShape(40.dp),

            modifier = Modifier.padding(top = 20.dp)
        ) {

            Row(
                modifier = Modifier
                    .background(Color.White)
                    .padding(15.dp),

                verticalAlignment = Alignment.CenterVertically,

                horizontalArrangement = Arrangement.Center
            ) {

                Slider(
                    value = volume,

                    onValueChange = {
                        volume = it
                    },

                    valueRange = 0f..1f,

                    modifier = Modifier
                        .width(250.dp)
                )


                Text(
                    text = "🔊 ${String.format("%.0f", volume * 100)}"
                )
            }
        }

        // Lista
        Card(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextButton(
                    onClick = {
                        video = Uri.parse("android.resource://${context.packageName}/${R.raw.video1}")
                        isPlaying = true
                    }
                ) {

                    Text(
                        text = "Vídeo 1",
                        fontSize = 30.sp,
                        modifier = Modifier
                            .padding(bottom = 20.dp)
                            .padding(top = 30.dp)
                    )
                }


                TextButton(
                    onClick = {
                        video = Uri.parse("https://www.w3schools.com/html/mov_bbb.mp4")
                        isPlaying = true
                    }
                ) {

                    Text(
                        text = "Vídeo 2",
                        fontSize = 30.sp,
                        modifier = Modifier.padding(bottom = 20.dp)
                    )
                }
            }
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
fun GreetingPreview() {

    AppvideoTheme {
        Tela()
    }
}
