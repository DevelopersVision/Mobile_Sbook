package br.senai.sp.jandira.s_book.components.update_announce.screens

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.Storage
import br.senai.sp.jandira.s_book.components.feed.screen.getAnunciante
import br.senai.sp.jandira.s_book.components.universal.HeaderCreateAnnounce
import br.senai.sp.jandira.s_book.components.update_announce.components.HeaderUpdateAnnounce
import br.senai.sp.jandira.s_book.functions.createAnnounceApp
import br.senai.sp.jandira.s_book.model.AutoresParaPostAnuncio
import br.senai.sp.jandira.s_book.model.ResponseUsuario
import br.senai.sp.jandira.s_book.model.Usuario
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.AnnouncePhotosViewModel
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import br.senai.sp.jandira.s_book.view_model.SharedViewModel
import br.senai.sp.jandira.s_book.view_model.ViewModelDosAutores
import br.senai.sp.jandira.s_book.view_model.ViewModelDosGenerosSelecionados
import br.senai.sp.jandira.s_book.view_model.ViewModelDosIds
import br.senai.sp.jandira.s_book.view_model.ViewModelDosTipoDeLivros
import br.senai.sp.jandira.s_book.view_model.ViewModelPreco
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun UpdateAnnounceFourthScrenn(
    viewModelV2: AnuncioViewModelV2,
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavController,
){
    val context = LocalContext.current

    val array = UserRepository(context).findUsers()

    val user = array[0]

    Column(modifier = Modifier.verticalScroll(ScrollState(0))) {
        HeaderUpdateAnnounce {
            navController.popBackStack()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pronto! Agora só revisar e confirmar os dados!",
                fontSize = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF2A2929)
            )
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier
                    .height(245.dp)
                    .width(160.dp)
            ) {
                AsyncImage(
                    model = viewModelV2.dadosAnuncio.foto[0].foto,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Card(
                    modifier = Modifier
                        .size(8.dp),
                    shape = CircleShape,
                    backgroundColor = Color(92, 44, 12, 255)
                ) {}
                Card(
                    modifier = Modifier
                        .size(8.dp)
                        .border(width = 1.dp, color = Color(0xFF000000), shape = CircleShape),
                    shape = CircleShape,
                    backgroundColor = Color.Transparent
                ) {}
                Card(
                    modifier = Modifier
                        .size(8.dp)
                        .border(width = 1.dp, color = Color(0xFF000000), shape = CircleShape),
                    shape = CircleShape,
                    backgroundColor = Color.Transparent
                ) {}
            }
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 6.dp,
                        spotColor = Color(0xFF000000),
                        ambientColor = Color(0xFF000000),
                        shape = RoundedCornerShape(8.dp)
                    ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)) {
                    Text(
                        text = "${viewModelV2.dadosAnuncio.anuncio.nome}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF404040)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFF808080)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${viewModelV2.dadosAnuncio.generos}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF808080)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "${viewModelV2.dadosAnuncio.tipo_anuncio[0].tipo}",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF404040),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {



                        AsyncImage(
                            model = user.foto,
                            contentDescription = "",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = user.nome,
                                fontSize = 20.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000)
                            )
                            Text(
                                text = "${user.cidade}, ${user.ufEstado}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF9F9898)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Descrição",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "${viewModelV2.dadosAnuncio.anuncio.descricao}",
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF9F9898),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Especificações",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth() ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Ano da edição",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                        Text(
                            text = "${viewModelV2.dadosAnuncio.anuncio.ano_lancamento}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(126, 125, 122, 255)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFFE0E0E0)
                    )
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth() ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Autor",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                        Text(
                            text = "${viewModelV2.dadosAnuncio.autores[0].nome}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(126, 125, 122, 255)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFFE0E0E0)
                    )
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth() ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Editora",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                        Text(
                            text = "${viewModelV2.dadosAnuncio.editora.nome}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(126, 125, 122, 255)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFFE0E0E0)
                    )
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth() ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Idioma",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                        Text(
                            text = "${viewModelV2.dadosAnuncio.idioma.nome}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(126, 125, 122, 255)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFFE0E0E0)
                    )
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth() ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Edição",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                        Text(
                            text = "${viewModelV2.dadosAnuncio.anuncio.edicao}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(126, 125, 122, 255)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFFE0E0E0)
                    )
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth() ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Número de páginas",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                        Text(
                            text = "${viewModelV2.dadosAnuncio.anuncio.numero_paginas}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(126, 125, 122, 255)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFFE0E0E0)
                    )
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth() ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "ISBN",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                        Text(
                            text = "${viewModelV2.dadosAnuncio.anuncio.isbn}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(126, 125, 122, 255)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFFE0E0E0)
                    )
                }
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth() ,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        val newEstadoLivro = viewModelV2.dadosAnuncio.estado_livro.estado.replace("[", "").replace("]", "");
                        Text(
                            text = "Estado do livro",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                        Text(
                            text = "${newEstadoLivro}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(126, 125, 122, 255)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFFE0E0E0)
                    )
                }
            }
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = {


                },
                colors = ButtonDefaults.buttonColors(Color(218, 108, 39, 255)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = "Anunciar",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}

fun getAnunciante(id: Long, callback: (Usuario?) -> Unit) {
    val call = RetrofitHelper.getUserByIdService().getUsuarioById(id)

    call.enqueue(object : Callback<ResponseUsuario> {
        override fun onResponse(
            call: Call<ResponseUsuario>, response: Response<ResponseUsuario>
        ) {
            val usuario = response.body()?.dados
            callback(usuario)
        }

        override fun onFailure(call: Call<ResponseUsuario>, t: Throwable) {
            callback(null) // Em caso de falha, passa null para o callback
        }
    })
}
