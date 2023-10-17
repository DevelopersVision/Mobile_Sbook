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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import coil.compose.AsyncImage


@Composable
fun CardInformacao(
    viewModel: AnuncioViewModel
) {

    Log.e("viewDoCardAgora", "${viewModel}")

    var favorito = Icons.Default.FavoriteBorder

    Surface(
        modifier = Modifier
            .width(370.dp)
            .height(330.dp)
            .background(
                color = Color(0xFFFFFFFF),
                shape = RoundedCornerShape(size = 10.dp)
            ),
        shape = RoundedCornerShape(size = 10.dp),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier
                .width(300.dp)
                .height(350.dp)
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 10.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            Column(
                modifier = Modifier
                    .width(300.dp)
                    .height(350.dp)
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 10.dp)
                    ),
            ) {
                Row(
                    modifier = Modifier
                        .width(292.dp)
                        .height(90.dp)
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${viewModel.nome}",
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
                Spacer(modifier = Modifier.height(20.dp))
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
                            .height(45.dp)
                    ) {
                        LazyColumn(){
                            items(viewModel.generos){
                                Text(
                                    text = "${it.nome}",
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.intermedium)),
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFF808080),
                                    modifier = Modifier
                                )
                            }

                        }

                    }

                    if(viewModel.preco != null) {
                        Text(
                            text = "R$ ${viewModel.preco}",
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.intermedium)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF404040),
                            modifier = Modifier
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyColumn() {
                            items(viewModel.tipo_anuncio) {
                                Log.e("AAA123452342342342323233", "${it.tipo}")
                                if(it.tipo == "Venda"){

                                } else{
                                    DefaultButtonScreen(
                                        text = "${it.tipo}",
                                    ) {}
                                }

                            }

                        }
                    } else{
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyColumn() {
                            items(viewModel.tipo_anuncio) {
                                Log.e("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "${it.tipo}")
                                Text(
                                    text = "Disponivel para ${it.tipo}",
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily(Font(R.font.intermedium)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF404040),
                                    modifier = Modifier
                                )

                            }

                        }
                    }


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
                            Log.e("Foto do anunciante ########", "${viewModel.anunciante_foto}")
                            AsyncImage(
                                model = "${viewModel.anunciante_foto}",
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
                                text = "${viewModel.anunciante_nome}",
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.intermedium)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000),
                            )
                            Text(
                                text = "${viewModel.cidade_anuncio}, ${viewModel.estado_anuncio}",
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
}


//else{
//                    Log.e("testando", "${viewModel.tipo_anuncio}")
//                    LazyColumn() {
//                        items(viewModel.tipo_anuncio) {
//                            Log.e("testando depois do items", "${viewModel.tipo_anuncio}")
//                            Text(
//                                text = "Disponivel para ${it.tipo}",
//                                fontSize = 24.sp,
//                                fontFamily = FontFamily(Font(R.font.intermedium)),
//                                fontWeight = FontWeight(700),
//                                color = Color(0xFF404040),
//                                modifier = Modifier
//                            )
//                        }
//
//                    }
//                }

//if(viewModel.tipo_anuncio[0].tipo == "Venda" && viewModel.tipo_anuncio[1].tipo == "Venda"  ){
//                                Log.e("testando depois do items1", "${viewModel.tipo_anuncio}")
//                            }else{

