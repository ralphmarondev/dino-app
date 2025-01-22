package com.ralphmarondev.dinoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.ralphmarondev.dinoapp.ui.theme.DinoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MainViewModel = viewModel()
            val randomDino by viewModel.randomDino.collectAsState()
            val isDarkTheme by viewModel.darkTheme.collectAsState()

            DinoAppTheme(darkTheme = isDarkTheme) {
                Scaffold(
                    topBar = {
                        HomeTopBar(
                            isDarkTheme = isDarkTheme,
                            toggleDarkTheme = viewModel::toggleDarkTheme
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = Color.Transparent
                        ) {
                            ElevatedCard(
                                onClick = viewModel::getRandomDino,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 16.dp, horizontal = 8.dp),
                                colors = CardDefaults.elevatedCardColors(
                                    containerColor = MaterialTheme.colorScheme.secondary,
                                    contentColor = MaterialTheme.colorScheme.onSecondary
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Get Random Dinosaur",
                                        fontFamily = FontFamily.Monospace,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        item {
                            AnimatedVisibility(visible = randomDino.id != -1) {
                                Column {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(8.dp)
                                    ) {
                                        Image(
                                            painter = rememberAsyncImagePainter(randomDino.imageUrl),
                                            contentDescription = "Dinosaur Image",
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(240.dp)
                                                .clip(RoundedCornerShape(8.dp)),
                                            contentScale = ContentScale.Crop
                                        )
                                    }

                                    Text(
                                        text = randomDino.name,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.W500,
                                        fontSize = 20.sp,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(
                                            horizontal = 8.dp,
                                            vertical = 4.dp
                                        )
                                    )
                                    Text(
                                        text = randomDino.description,
                                        fontFamily = FontFamily.Monospace,
                                        fontWeight = FontWeight.W400,
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.secondary,
                                        modifier = Modifier.padding(
                                            horizontal = 8.dp,
                                            vertical = 4.dp
                                        )
                                    )
                                }
                            }
                        }

                        item {
                            AnimatedVisibility(visible = randomDino.id == -1) {
                                ElevatedCard(
                                    modifier = Modifier
                                        .padding(8.dp)
                                ) {
                                    Column {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(8.dp)
                                        ) {
                                            Image(
                                                painter = rememberAsyncImagePainter(randomDino.imageUrl),
                                                contentDescription = "Dinosaur Image",
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(320.dp)
                                                    .clip(RoundedCornerShape(8.dp)),
                                                contentScale = ContentScale.Crop
                                            )
                                        }

                                        Text(
                                            text = randomDino.name,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.W500,
                                            fontSize = 20.sp,
                                            color = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.padding(
                                                horizontal = 8.dp,
                                                vertical = 4.dp
                                            )
                                        )
                                        Text(
                                            text = randomDino.description,
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.W400,
                                            fontSize = 16.sp,
                                            color = MaterialTheme.colorScheme.secondary,
                                            modifier = Modifier.padding(
                                                horizontal = 8.dp,
                                                vertical = 4.dp
                                            )
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopBar(
    isDarkTheme: Boolean,
    toggleDarkTheme: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Dino App",
                fontFamily = FontFamily.Monospace
            )
        },
        actions = {
            IconButton(
                onClick = toggleDarkTheme
            ) {
                val imageVector = when (isDarkTheme) {
                    true -> Icons.Outlined.LightMode
                    false -> Icons.Outlined.DarkMode
                }

                Icon(
                    imageVector = imageVector,
                    contentDescription = "Theme Icon"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}