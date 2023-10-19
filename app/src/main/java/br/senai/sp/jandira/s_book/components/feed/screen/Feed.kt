package br.senai.sp.jandira.s_book.components.feed.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.feed.components.AnunciosProximos
import br.senai.sp.jandira.s_book.components.feed.components.EscolhaFazer
import br.senai.sp.jandira.s_book.components.feed.components.Header
import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.model.ResponseUsuario
import br.senai.sp.jandira.s_book.model.Usuario
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FeedScreen(
    navController: NavController,
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope?,
    viewModelQueVaiPassarOsDados: AnuncioViewModel
) {


    val context = LocalContext.current

    var listAnuncios by remember {
        mutableStateOf(listOf<JsonAnuncios>())
    }

    val call = RetrofitHelper.getAnunciosService().getAnuncios(2)

    Log.e("TAG-Teste1", "onResponse: teste", )

    // Executar a chamada
    call.enqueue(object : Callback<AnunciosBaseResponse> {
        override fun onResponse(
            call: Call<AnunciosBaseResponse>, response: Response<AnunciosBaseResponse>
        ) {
            Log.e("TAG-Teste2", "onResponse: teste", )
            listAnuncios = response.body()!!.anuncios
            Log.e("lista", "onResponse: $listAnuncios", )
        }


        override fun onFailure(call: Call<AnunciosBaseResponse>, t: Throwable) {
             Log.d("ERROR_FEED", "ERROR NA CHAMADA DE FEED")
             Log.d("ERROR_FEED-t", "$t")
             Log.d("ERROR_FEED-tmessage", "${t.message}")
             Log.d("ERROR_FEED-tstacktrace", "${t.stackTrace}")
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Header(navController, navRotasController, context)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            EscolhaFazer(
                filter = { navRotasController.navigate("Filters") },
                anuncio = { navRotasController.navigate("primeiro_anunciar") }
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "Anúncios mais próximos",
                fontSize = 16.sp,
                fontWeight = FontWeight(700),
                color = Color(92, 44, 12, 255)
            )
            Spacer(modifier = Modifier.height(18.dp))

            val pairs = listAnuncios.chunked(2)

            for (pair in pairs) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp, 0.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    for (item in pair) {
                        AnunciosProximos(
                            nome_livro = item.anuncio.nome,
                            foto = item.foto[0].foto,
                            tipo_anuncio = item.tipo_anuncio[0].tipo,
                            autor = item.autores[0].nome,
                            preco = item.anuncio.preco,
                            id = item.anuncio.id,
                            navController = navRotasController,
                            lifecycleScope = lifecycleScope,
                            onClick = {
                                val anunciante = getAnunciante(item.anuncio.anunciante) { usuario ->
                                    if (usuario != null) {
                                        Log.e("usuario-luiz", "FeedScreen: $usuario")
                                        viewModelQueVaiPassarOsDados.foto = item.foto

                                        viewModelQueVaiPassarOsDados.nome = item.anuncio.nome

                                        viewModelQueVaiPassarOsDados.generos = item.generos
                                        viewModelQueVaiPassarOsDados.tipo_anuncio =
                                            item.tipo_anuncio

                                        viewModelQueVaiPassarOsDados.anunciante_foto = usuario.foto

                                        viewModelQueVaiPassarOsDados.anunciante_nome = usuario.nome
                                        viewModelQueVaiPassarOsDados.cidade_anuncio = usuario.cidade
                                        viewModelQueVaiPassarOsDados.estado_anuncio = usuario.estado
                                        viewModelQueVaiPassarOsDados.descricao =
                                            item.anuncio.descricao

                                        viewModelQueVaiPassarOsDados.ano_edicao =
                                            item.anuncio.ano_lancamento
                                        viewModelQueVaiPassarOsDados.autor = item.autores
                                        viewModelQueVaiPassarOsDados.editora = item.editora
                                        viewModelQueVaiPassarOsDados.idioma = item.idioma
                                        viewModelQueVaiPassarOsDados.preco = item.anuncio.preco
//                                        Log.e("Valor Preco", "${viewModelQueVaiPassarOsDados.preco}")
                                    } else {
                                        Log.e("Anunciante", "null")
                                    }
                                }
                            },
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            Spacer(modifier = Modifier.height(48.dp))
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