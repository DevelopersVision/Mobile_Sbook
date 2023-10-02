package br.senai.sp.jandira.s_book.components.favorite.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.Foto
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.navigation_home_bar.BottomBarScreen.Anuncio.icon
import coil.compose.AsyncImage

@Composable
fun Card(
    nome_livro: String,
    ano_lancamento: Int,
    autor: String,
    tipo_anuncio: String,
    preco: Double?,
    foto: String,
    onClick: () -> Unit
) {

    val coracao = Icons.Default.Favorite

    var isChecked by remember { mutableStateOf(false) }

    androidx.compose.material.Card(
        modifier = Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0xFF000000),
                ambientColor = Color(0xFF000000)
            )
            .width(300.dp)
            .height(200.dp)
            .clickable {
                onClick()
            },
    ) {
        Row() {

            AsyncImage(
                model = "${foto}",
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(130.dp)
                    .height(200.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 12.dp, top = 10.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "${nome_livro}",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(
                            Font(R.font.poppinsmedium)
                        ),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                    Text(
                        text = " ${autor} | ${ano_lancamento}",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.intermedium
                            )
                        ),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF9F9898),
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    if(tipo_anuncio == "Doação"){
                        Text(
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
                    } else if (tipo_anuncio == "Troca"){
                        Text(
                            text = "Troca-se",
                            fontSize = 24.sp,
                            fontFamily = FontFamily(
                                Font(
                                    R.font.poppinsmedium
                                )
                            ),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                    } else{
                        Text(
                            text = "R$" + preco,
                            fontSize = 24.sp,
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
                            .width(100.dp)
                            .height(42.dp),
                        onClick = {
                            onClick
                        }
                    ) {
                        Icon(
                            imageVector = coracao ,
                            contentDescription = "",
                            tint = Color(207, 22, 22, 255)
                        )
                    }
                }
            }
        }
    }

}