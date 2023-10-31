package br.senai.sp.jandira.s_book.components.feed.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.feed.components.AnunciosProximos
import br.senai.sp.jandira.s_book.components.feed.components.ButtonCarregar
import br.senai.sp.jandira.s_book.components.feed.components.EscolhaFazer
import br.senai.sp.jandira.s_book.components.feed.components.Header
import br.senai.sp.jandira.s_book.components.universal.ProgressBar
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
    viewModelQueVaiPassarOsDados: AnuncioViewModel,

) {
    val TAG = "Teste FEED"

    val context = LocalContext.current

    var page by remember {
        mutableIntStateOf(1)
    }

    var cont by remember {
        mutableStateOf(true)
    }

    var listAnuncios by remember {
        mutableStateOf(listOf<JsonAnuncios>())
    }

    var listAnunciosFeed by remember {
        mutableStateOf(listOf<JsonAnuncios>())
    }

    val call = RetrofitHelper.getAnunciosService().getAnuncios(page)

    // Executar a chamada
    call.enqueue(object : Callback<AnunciosBaseResponse> {
        override fun onResponse(
            call: Call<AnunciosBaseResponse>, response: Response<AnunciosBaseResponse>
        ) {
            Log.e(TAG, "resposta: $response", )

            if(response.code() == 200){
                listAnuncios = response.body()!!.anuncios
                Log.e("Cont", "Contador: $cont ")
                Log.e("Lista", "Lista: $listAnuncios")
                Log.e("ListaEmpty", "Lista: ${listAnuncios.isNotEmpty()}")
                if(cont && listAnuncios.isNotEmpty() && response.body()!!.page == page){
                    listAnunciosFeed += listAnuncios

                    cont = false
                }else{
                    Log.e(TAG, "Contador morreu", )
                }
                Log.e("lista", "onResponse: $listAnuncios")
            }else{
                Toast.makeText(context, "erro da api", Toast.LENGTH_SHORT).show()
            }

        }

        override fun onFailure(call: Call<AnunciosBaseResponse>, t: Throwable) {
            Log.d("ERROR_FEED", "ERROR NA CHAMADA DE FEED")
            Log.d("ERROR_FEED-t", "$t")
            Log.d("ERROR_FEED-tmessage", "${t.message}")
            Log.d("ERROR_FEED-tstacktrace", "${t.stackTrace}")
            Log.d("ERROR_FEED-tlocalized", t.localizedMessage!!)
            Log.d("ERROR_FEED-tcause", "${t.cause}")
        }
    })

    val array = UserRepository(context).findUsers()
    var user = User()

    if (array.isNotEmpty()) {
        user = array[0]
    }

    var isLoading by remember { mutableStateOf(false) } // Variável para controlar a visibilidade da ProgressBar


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
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

            if (listAnuncios.isEmpty() ) {
                isLoading == true
                ProgressBar(isDisplayed = !isLoading)
            }else{
                val pairs = listAnunciosFeed.chunked(2)



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
                                    viewModelQueVaiPassarOsDados.foto = item.foto
                                    val anunciante = getAnunciante(item.anuncio.anunciante) { usuario ->
                                        if (usuario != null) {

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
                if (cont == true) {
                    isLoading == true
                    ProgressBar(isDisplayed = !isLoading)
                    Spacer(modifier = Modifier.height(48.dp))
                }else{
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 5.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ButtonCarregar {
                            page++
                            cont = true
                        }
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                }
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

fun getAnuncios(page: Int): List<JsonAnuncios> {
    val call = RetrofitHelper.getAnunciosService().getAnuncios(page)

    var listAnuncios = listOf<JsonAnuncios>()

    // Executar a chamada
    call.enqueue(object : Callback<AnunciosBaseResponse> {
        override fun onResponse(
            call: Call<AnunciosBaseResponse>, response: Response<AnunciosBaseResponse>
        ) {

            listAnuncios = response.body()!!.anuncios
            Log.e("lista", "onResponse: $listAnuncios")
        }


        override fun onFailure(call: Call<AnunciosBaseResponse>, t: Throwable) {
            Log.d("ERROR_FEED", "ERROR NA CHAMADA DE FEED")
            Log.d("ERROR_FEED-t", "$t")
            Log.d("ERROR_FEED-tmessage", "${t.message}")
            Log.d("ERROR_FEED-tstacktrace", "${t.stackTrace}")
            Log.d("ERROR_FEED-tlocalized", "${t.localizedMessage}")
            Log.d("ERROR_FEED-tcause", "${t.cause}")
        }
    })

    return listAnuncios
}

//val call = RetrofitHelper.getAnunciosFavoritadosService()
//                                    .verificarFavorito(user.id, id)
//
//
//                                // Executar a chamada
//                                call.enqueue(object : Callback<VerificarFavoritoBaseResponse> {
//                                    override fun onResponse(
//                                        call: Call<VerificarFavoritoBaseResponse>,
//                                        response: Response<VerificarFavoritoBaseResponse>
//                                    ) {
//
//                                        Log.e("BODY", "onResponse: ${response.body()}")
//
//
//                                        if (response.isSuccessful) {
//
//                                            Log.e("Ja ta favoritado bixo burro", "Plim")
//                                            isChecked = false
//
//
//                                            removerDosFavoritos(id_anuncio = id, id_usuario = user.id)
//                                            coracaoClik()
//                                        } else {
//                                            Log.e("MORREU", "morreu")
//                                            Log.e(
//                                                "ErrorBody",
//                                                "burrei: ${response.errorBody()?.string()!!}",
//                                            )
//                                            isChecked = true
//
//                                            Log.e("Log de Hoje felipe", "${id}")
//                                            Log.e("Log de Hoje felipe", "${user.id}")
//                                            favoritarAnuncio(id_anuncio = id, id_usuario = user.id, lifecycleScope = lifecycleScope)
//                                        }
//                                    }
//
//
//                                    override fun onFailure(
//                                        call: Call<VerificarFavoritoBaseResponse>,
//                                        t: Throwable
//                                    ) {
//                                        Log.d("mudou o nome", "Depois da chamada da API:")
//                                    }
//                                })
//                            Log.i("testando123", "${call}")