package br.senai.sp.jandira.s_book.components.favorite.components


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.my_announces.components.favoritarAnuncio
import br.senai.sp.jandira.s_book.components.my_announces.components.removerDosFavoritos
import br.senai.sp.jandira.s_book.model.DesfavoritarBaseResponse
import br.senai.sp.jandira.s_book.model.VerificarFavoritoBaseResponse
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.repository.AnunciosFavoritadosRepository
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun Card(
    id: Int,
    nome_livro: String,
    ano_lancamento: Int,
    autor: String,
    tipo_anuncio: String,
    preco: Double?,
    foto: String,
    lifecycleScope: LifecycleCoroutineScope,
    onClick: () -> Unit,
    coracaoClik: ()-> Unit,
) {
    val context = LocalContext.current
    val array = UserRepository(context).findUsers()
    var user = User()

    if (array.isNotEmpty()) {
        user = array[0]
    }

//    val coracao = Icons.Default.Favo

    val cor = 0xFFFFFF

    var isChecked by remember { mutableStateOf(false) }

    var visible by remember { mutableStateOf(true) }



    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        androidx.compose.material.Card(
            modifier = Modifier
                .shadow(
                    elevation = 6.dp,
                    spotColor = Color(0xFF000000),
                    ambientColor = Color(0xFF000000)
                )
                .fillMaxWidth()
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
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        if (tipo_anuncio == "Doação") {
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
                        } else if (tipo_anuncio == "Troca") {
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
                        } else {
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


                        androidx.compose.material.Card(
                            modifier = Modifier
                                .shadow(
                                    elevation = 6.dp,
                                    spotColor = Color(0xFF000000),
                                    ambientColor = Color(0xFF000000)
                                )
                                .fillMaxWidth()
                                .height(200.dp)
                                .clickable {
                                    onClick()
                                },
                        ) {
                            IconButton(
                                modifier = Modifier
                                    .size(50.dp)
                                    .padding(end = 8.dp,),
                                onClick = {
                                    // Cria uma chamada para o EndPoint
                                    val call = RetrofitHelper.getAnunciosFavoritadosService()
                                        .verificarFavorito(user.id, id)


                                    // Executar a chamada
                                    call.enqueue(object : Callback<VerificarFavoritoBaseResponse> {
                                        override fun onResponse(
                                            call: Call<VerificarFavoritoBaseResponse>,
                                            response: Response<VerificarFavoritoBaseResponse>
                                        ) {

                                            Log.e("BODY", "onResponse: ${response.body()}")


                                            if (response.isSuccessful) {

                                                Log.e("Ja ta favoritado bixo burro", "Plim")
                                                isChecked = false

                                                removerDosFavoritos(
                                                    id_anuncio = id,
                                                    id_usuario = user.id
                                                )

                                                visible = false

                                                coracaoClik()
                                            } else {
                                                Log.e("MORREU", "morreu")
                                                Log.e(
                                                    "ErrorBody",
                                                    "burrei: ${response.errorBody()?.string()!!}",
                                                )
                                                isChecked = true
                                                Log.e("Log de Hoje felipe", "${id}")
                                                Log.e("Log de Hoje felipe", "${user.id}")
                                                favoritarAnuncio(
                                                    id_anuncio = id,
                                                    id_usuario = user.id,
                                                    lifecycleScope = lifecycleScope
                                                )
                                            }
                                        }


                                        override fun onFailure(
                                            call: Call<VerificarFavoritoBaseResponse>,
                                            t: Throwable
                                        ) {
                                            Log.d("mudou o nome", "Depois da chamada da API:")
                                        }
                                    })
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
        }
    }
}