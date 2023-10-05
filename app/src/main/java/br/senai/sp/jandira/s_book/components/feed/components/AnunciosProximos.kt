package br.senai.sp.jandira.s_book.components.feed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnunciosProximos(
    id: Int,
    nome_livro: String,
    ano_lancamento: Int,
    autor: String,
    tipo_anuncio: String,
    preco: Double?,
    foto: String,
) {

    val coracao = Icons.Default.FavoriteBorder

    var isChecked by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .width(156.dp)
                .height(260.dp),
        ) {
            Column(
                modifier = Modifier
                    .width(156.dp)
                    .height(260.dp)
                    .background(
                        color =
                        Color(0xFFFFFFFF)
                    ),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .width(156.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = "${foto}",
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(96.dp)
                            .height(147.dp)
                            .padding(top = 12.dp)
                    )

                }
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp),
                    text = "${nome_livro}",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
                Text(
                    modifier = Modifier
                        .padding(start = 12.dp),
                    text = "${autor}",
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF9F9898),
                )
                Row(
                    modifier = Modifier
                        .padding(start = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    if (tipo_anuncio == "Doação") {
                        androidx.compose.material3.Text(
                            text = "Doa-se",
                            fontSize = 24.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.poppinsmedium
                                )
                            ),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    } else if (tipo_anuncio == "Troca") {
                        androidx.compose.material3.Text(
                            text = "Troca-se",
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.poppinsmedium
                                )
                            ),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    } else {
                        androidx.compose.material3.Text(
                            text = "R$" + preco,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.poppinsmedium
                                )
                            ),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .width(50.dp)
                            .height(42.dp),
                        onClick = {
                            if (isChecked == false) {
                                isChecked = true
                                var cor = 0xFFFFFF
                            } else {
                                isChecked = false
                                var cor = 0xF60E1C
                            }
                        }
                    ) {
                        androidx.compose.material3.Icon(
                            imageVector = coracao,
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }