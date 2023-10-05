package br.senai.sp.jandira.s_book.components.feed.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import br.senai.sp.jandira.s_book.navigation_home_bar.BottomBarScreen


@Preview(showSystemUi = true)
@Composable
fun Rowa() {


    Surface(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(255.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(255.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.Top,
            ) {
                Column(
                    modifier = Modifier
                        .width(132.dp)
                        .height(255.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.diario),
                        contentDescription = "image description",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .width(96.dp)
                            .height(147.dp)
                    )
                    Text(
                        text = "Diário de um \nBanana",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                    Text(
                        text = "J.K Rowling | Abril 2009",
                        fontSize = 10.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF9F9898),
                    )

                    Row(
                        modifier = Modifier
                            .padding(top = 40.dp),
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
                                .width(100.dp)
                                .height(42.dp),
                            onClick = { }
                        ) {
                            BottomBarScreen.Anuncio.icon = Icons.Default.Favorite
                        }
                    }
                }
            }
        }
    }
}