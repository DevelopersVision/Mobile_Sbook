package br.senai.sp.jandira.s_book.components.announceDetail.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.view_model.AnuncioViewMODEL
import coil.compose.AsyncImage


@Composable
fun CardInformacao(
    viewMODEL: AnuncioViewMODEL
) {

    Log.e("viewZuada", "${viewMODEL}")

    var favorito = Icons.Default.FavoriteBorder

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(330.dp)
    ) {
        Column(
            modifier = Modifier
                .width(330.dp)
                .height(350.dp)
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x40000000),
                    ambientColor = Color(0x40000000)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .width(292.dp)
                    .height(90.dp)
                    .padding(top = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Log.e("nome que a view model passou", "${viewMODEL.nome}")
                Text(
                    text = "${viewMODEL.nome}",
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF404040),
                )
                Icon(
                    imageVector = favorito,
                    contentDescription = "",
                    modifier = Modifier
                        .size(32.dp)
                )
            }
            Spacer(modifier = Modifier.height(22.dp))
            Column(
                modifier = Modifier
                    .padding(0.dp)
                    .width(300.dp)
                    .height(2.dp)
                    .background(color = Color(0xFFCECECE))
            ) {}
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .width(292.dp)
                    .height(200.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
               Column(
                   modifier = Modifier
                       .width(292.dp)
                       .height(35.dp)
               ) {
                   Text(
                       text = "${viewMODEL.generos}",
                       fontSize = 14.sp,
                       fontFamily = FontFamily(Font(R.font.intermedium)),
                       fontWeight = FontWeight(600),
                       color = Color(0xFF808080),
                       modifier = Modifier
                   )
               }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${viewMODEL.tipo_anuncio}",
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF404040),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .width(292.dp)
                        .height(64.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                ) {
                    Card(
                        modifier = Modifier
                            .size(60.dp),
                        shape = CircleShape,
                    ) {

                        AsyncImage(
                            model = "${viewMODEL.anunciante_foto}",
                            contentDescription = "image description",
                            contentScale = ContentScale.Crop
                        )
                    }
                    Column(
                        modifier = Modifier
                            .width(292.dp)
                            .height(70.dp)
                    ) {
                        Text(
                            text = "${viewMODEL.autor}",
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.intermedium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        )
                        Text(
                            text = "${viewMODEL.cidade_anuncio}, ${viewMODEL.estado_anuncio}",
                            fontSize = 12.sp,
                            fontFamily = FontFamily(Font(R.font.intermedium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF9F9898),
                        )
                    }
                }
            }
        }
    }
}