package br.senai.sp.jandira.s_book.components.feed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun AnunciosProximos() {

    val coracao = Icons.Default.FavoriteBorder

    var isChecked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Top),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {

            Card(
                modifier = Modifier
                    .width(156.dp)
                    .height(260.dp),
                onClick = { /*TODO*/ }
            ) {
                Column(
                    modifier = Modifier
                        .width(156.dp)
                        .height(260.dp)
                        .background(
                            color =
                            Color(0xFFFFFFFF)
                        ),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
                ) {
                    Column(
                        modifier = Modifier
                            .width(156.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.diario
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .width(96.dp)
                                .height(147.dp)
                        )

                    }
                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        text = "Diário de um \nBanana",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        text = "J.K Rowling | Abril 2009",
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF9F9898),
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(13.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        androidx.compose.material3.Text(
                            text = "R$ 34,00",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.poppinsmedium
                                )
                            ),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                        IconButton(
                            modifier = Modifier
                                .width(50.dp)
                                .height(42.dp),
                            onClick = {
                                if(isChecked == false){
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

            // Segundo Card

            Card(
                modifier = Modifier
                    .width(156.dp)
                    .height(260.dp),
                onClick = { /*TODO*/ }
            ) {
                Column(
                    modifier = Modifier
                        .width(156.dp)
                        .height(260.dp)
                        .background(
                            color =
                            Color(0xFFFFFFFF)
                        ),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top)
                ) {
                    Column(
                        modifier = Modifier
                            .width(156.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.diario
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .width(96.dp)
                                .height(147.dp)
                        )

                    }
                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        text = "Diário de um \nBanana",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        text = "J.K Rowling | Abril 2009",
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF9F9898),
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 12.dp),
                        horizontalArrangement = Arrangement.spacedBy(13.dp, Alignment.Start),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        androidx.compose.material3.Text(
                            text = "R$ 34,00",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.poppinsmedium
                                )
                            ),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                        IconButton(
                            modifier = Modifier
                                .width(50.dp)
                                .height(42.dp),
                            onClick = {
                                if(isChecked == false){
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
    }
}