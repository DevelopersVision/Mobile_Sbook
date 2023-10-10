package br.senai.sp.jandira.s_book.components.feed.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.view_model.AnuncioViewMODEL
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnunciosProximos(
    id: Int,
    nome_livro: String,
    autor: String,
    tipo_anuncio: String,
    preco: Double?,
    foto: String,
    navController: NavController,
    onClick: () -> Unit
) {



    val coracao = Icons.Default.FavoriteBorder

    var isChecked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .width(156.dp)
            .height(260.dp)
            .clickable {
                navController.navigate("annouceDetail")
            }
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0xFF000000),
                ambientColor = Color(0xFF000000)
            ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Box(
            modifier = Modifier
                .width(156.dp)
                .height(250.dp)
                .clickable {
                    onClick()
                    navController.navigate("annouceDetail")
                },
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = "${foto}",
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(96.dp)
                        .height(148.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${nome_livro}",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
            )
            Text(
                text = "${autor}",
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF9F9898),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (tipo_anuncio == "Doação") {
                    Text(
                        text = "Doa-se",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.poppinsmedium
                            )
                        ),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                } else if (tipo_anuncio == "Troca") {
                    Text(
                        text = "Troca-se",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.poppinsmedium
                            )
                        ),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                } else {
                    Text(
                        text = "R$" + preco,
                        fontSize = 12.sp,
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