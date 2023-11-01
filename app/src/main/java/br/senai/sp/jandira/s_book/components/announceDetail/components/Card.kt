package br.senai.sp.jandira.s_book.components.announceDetail.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.my_announces.components.favoritarAnuncio
import br.senai.sp.jandira.s_book.components.my_announces.components.removerDosFavoritos
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.model.DesfavoritarBaseResponse
import br.senai.sp.jandira.s_book.model.VerificarFavoritoBaseResponse
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.repository.AnunciosFavoritadosRepository
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun CardInformacao(
    viewModel: AnuncioViewModel,
    lifecycleScope: LifecycleCoroutineScope,
    onClick: () -> Unit
) {

    var isChecked by remember { mutableStateOf(false) }



    var visible by remember { mutableStateOf(true) }

    val context = LocalContext.current
    val array = UserRepository(context).findUsers()
    var user = User()

    if (array.isNotEmpty()) {
        user = array[0]
    }

    // Cria uma chamada para o EndPoint
    val callPraCorDosCard = viewModel.id?.let {
        RetrofitHelper.getAnunciosFavoritadosService().verificarFavorito(user.id,
            it
        )
    }



    // Executar a chamada
    if (callPraCorDosCard != null) {
        callPraCorDosCard.enqueue(object : Callback<VerificarFavoritoBaseResponse> {
            override fun onResponse(
                call: Call<VerificarFavoritoBaseResponse>,
                response: Response<VerificarFavoritoBaseResponse>
            ) {
                if (response.code() == 200) {


                    isChecked = true


                } else {

                    isChecked = false


                }
            }


            override fun onFailure(call: Call<VerificarFavoritoBaseResponse>, t: Throwable) {

            }
        })
    }



    Log.e("viewDoCardAgora", "${viewModel}")

    var favorito = Icons.Default.FavoriteBorder

    Surface(
        modifier = Modifier
            .width(370.dp)
            .height(390.dp)
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
                .height(390.dp)
                .padding(start = 24.dp)
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 10.dp)
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .height(390.dp)
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(size = 10.dp)
                    ),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    modifier = Modifier
                        .width(292.dp)
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .width(250.dp)
                    ) {
                        Text(
                            text = "${viewModel.nome}",
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.intermedium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF404040),
                        )
                    }
                    IconButton(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(end = 8.dp,),
                        onClick = {
                            // Cria uma chamada para o EndPoint
                            val call = viewModel.id?.let {
                                RetrofitHelper.getAnunciosFavoritadosService()
                                    .verificarFavorito(user.id, it)
                            }


                            // Executar a chamada
                            if (call != null) {
                                call.enqueue(object : Callback<VerificarFavoritoBaseResponse> {
                                    override fun onResponse(
                                        call: Call<VerificarFavoritoBaseResponse>,
                                        response: Response<VerificarFavoritoBaseResponse>
                                    ) {

                                        Log.e("BODY", "onResponse: ${response.body()}")


                                        if (response.isSuccessful) {

                                            Log.e("Ja ta favoritado bixo burro", "Plim")
                                            isChecked = false

                                            viewModel.id?.let {
                                                removerDosFavoritos(
                                                    id_anuncio = it,
                                                    id_usuario = user.id
                                                )
                                            }

                                            visible = false


                                        } else {
                                            Log.e("MORREU", "morreu")
                                            Log.e(
                                                "ErrorBody",
                                                "burrei: ${response.errorBody()?.string()!!}",
                                            )
                                            isChecked = true
                                            Log.e("Log de Hoje felipe", "${viewModel.id}")
                                            Log.e("Log de Hoje felipe", "${user.id}")
                                            viewModel.id?.let {
                                                favoritarAnuncio(
                                                    id_anuncio = it,
                                                    id_usuario = user.id,
                                                    lifecycleScope = lifecycleScope
                                                )
                                            }
                                        }
                                    }


                                    override fun onFailure(
                                        call: Call<VerificarFavoritoBaseResponse>,
                                        t: Throwable
                                    ) {
                                        Log.d("mudou o nome", "Depois da chamada da API:")
                                    }
                                })
                            }
                            Log.i("testando123", "${call}")
                        }
                    ) {
                        if (isChecked !== true) {
                            Image(
                                painter = painterResource(
                                    id = R.drawable.coracao_certo
                                ),
                                contentDescription = ""
                            )


                        } else {
                            Image(
                                painter = painterResource(
                                    id = R.drawable.desfavoritar
                                ),
                                contentDescription = ""
                            )


                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(0.dp)
                        .width(300.dp)
                        .height(2.dp)
                        .background(color = Color(0xFFCECECE))
                ) {}
                Column(
                    modifier = Modifier
                        .width(292.dp)
                        .height(270.dp)
                ) {
                    LazyRow(
                        modifier = Modifier
                            .width(292.dp)
                            .height(45.dp)
                    ) {
                        items(viewModel.generos) {
                            Text(
                                text = "${it.nome}, ",
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.intermedium)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFF808080),
                                modifier = Modifier
                            )
                        }

                    }
                    Column(
                        modifier = Modifier
                            .width(292.dp)
                            .height(120.dp),
                    ) {
                        if (viewModel.preco != null) {
                            Text(
                                text = "R$ ${viewModel.preco}",
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.intermedium)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF404040),
                                modifier = Modifier
                            )
                            Spacer(modifier = Modifier.height(4.dp))

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Ou",
                                    fontSize = 20.sp,
                                    fontFamily = FontFamily(Font(R.font.intermedium)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF404040),
                                    modifier = Modifier
                                )
                            }
                            LazyColumn() {
                                items(viewModel.tipo_anuncio) {
                                    Log.e("AAA123452342342342323233", "${it.tipo}")
                                    if (it.tipo == "Venda") {

                                    } else {
                                        DefaultButtonScreen(
                                            text = "${it.tipo}",
                                        ) {}
                                    }
                                }
                            }
                        } else {
                            LazyColumn() {
                                items(viewModel.tipo_anuncio) {
                                    Log.e("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", "${it.tipo}")
                                    DefaultButtonScreen(
                                        text = "${it.tipo}",
                                    ) {}
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(27.dp))
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
                                .height(64.dp)
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

fun favoritarAnuncio(
    id_usuario: Long,
    id_anuncio: Int,
    lifecycleScope: LifecycleCoroutineScope
) {
    val favoriteRepositoy = AnunciosFavoritadosRepository()

    lifecycleScope.launch {
        val response =
            favoriteRepositoy.inserirAnuncioAosFavoritos(id_usuario, id_anuncio)

        if (response.isSuccessful) {
            Log.e("registrar nos favoritados", "bodyy: ${response.body()}")
        } else {
            val erroBody = response.errorBody()?.string()

            Log.e("registrar os erros", "bodyerrado: $erroBody")
        }
    }

}

fun removerDosFavoritos(id_usuario: Long, id_anuncio: Int) {
    val call = RetrofitHelper.getAnunciosFavoritadosService()
        .destavoritarAnuncio(id_usuario, id_anuncio)

    call.enqueue(object : Callback<DesfavoritarBaseResponse> {
        override fun onResponse(
            call: Call<DesfavoritarBaseResponse>,
            response: Response<DesfavoritarBaseResponse>
        ) {
        }

        override fun onFailure(
            call: Call<DesfavoritarBaseResponse>,
            t: Throwable
        ) {
            Log.d("mudou o nome", "Depois da chamada da API:")
        }
    })
}


//Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .shadow(
//                        elevation = 6.dp,
//                        spotColor = Color(0xFF000000),
//                        ambientColor = Color(0xFF000000),
//                        shape = RoundedCornerShape(8.dp)
//                    ),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Column(modifier = Modifier
//                    .fillMaxSize()
//                    .padding(24.dp)) {
//                    Text(
//                        text = "$nomeLivro",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight(600),
//                        color = Color(0xFF404040)
//                    )
//                    Spacer(modifier = Modifier.height(20.dp))
//                    Divider(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(0.8.dp),
//                        color = Color(0xFF808080)
//                    )
//                    Spacer(modifier = Modifier.height(16.dp))
//                    Text(
//                        text = "$generoLivro",
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight(600),
//                        color = Color(0xFF808080)
//                    )
//                    Spacer(modifier = Modifier.height(24.dp))
//                    Text(
//                        text = "$mensagem",
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight(700),
//                        color = Color(0xFF404040),
//                        modifier = Modifier.fillMaxWidth(),
//                        textAlign = TextAlign.Center
//                    )
//                    Spacer(modifier = Modifier.height(24.dp))
//                    Row(verticalAlignment = Alignment.CenterVertically) {
//                        Image(
//                            painter = painterResource(id = R.drawable.susanna_profile),
//                            contentDescription = "",
//                            modifier = Modifier
//                                .size(64.dp)
//                                .clip(CircleShape)
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Column {
//                            Text(
//                                text = "Max Kellerman",
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight(600),
//                                color = Color(0xFF000000)
//                            )
//                            Text(
//                                text = "Carapícuiba, São Paulo",
//                                fontSize = 12.sp,
//                                fontWeight = FontWeight(600),
//                                color = Color(0xFF9F9898)
//                            )
//                        }
//                    }
//                }
//            }