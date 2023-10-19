package br.senai.sp.jandira.s_book.components.third_create_announce.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.universal.HeaderCreateAnnounce

@Composable
fun ThirdCreateAnnounceScreen(
    navController: NavController
){

    Column() {
        HeaderCreateAnnounce()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Muito bem! Adicione detalhes visuais do livro.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF2A2929)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Insira imagens do livro:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2A2929)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.photo),
                    contentDescription = "",
                    modifier = Modifier.size(128.dp)
                )
                Spacer(modifier = Modifier.height(48.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .height(260.dp)
                            .width(160.dp)
                            .background(Color.Transparent)
                            .border(width = 1.dp, color = Color(0xFFEBEBEB)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "",
                            tint = Color(206, 206, 206, 255),
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .height(260.dp)
                            .width(160.dp)
                            .background(Color.Transparent)
                            .border(width = 1.dp, color = Color(0xFFEBEBEB)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.diario),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(193, 188, 204, 255)
                    ) {}
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(193, 188, 204, 255)
                    ) {}
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(170, 98, 49, 255)
                    ) {}
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(193, 188, 204, 255)
                    ) {}
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(193, 188, 204, 255)
                    ) {}
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(193, 188, 204, 255)
                    ) {}
                }
                Image(
                    painter = painterResource(id = R.drawable.seta_prosseguir),
                    contentDescription = "",
                    modifier = Modifier
                        .size(72.dp)
                        .clickable { navController.navigate("quarto_anunciar") }
                )
            }
        }
    }
}