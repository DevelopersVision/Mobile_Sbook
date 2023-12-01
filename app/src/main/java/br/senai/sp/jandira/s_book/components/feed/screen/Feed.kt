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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.feed.components.AnunciosProximos
import br.senai.sp.jandira.s_book.components.feed.components.ButtonCarregar
import br.senai.sp.jandira.s_book.components.feed.components.EscolhaFazer
import br.senai.sp.jandira.s_book.components.feed.components.Header
import br.senai.sp.jandira.s_book.components.perfil.components.converterData
import br.senai.sp.jandira.s_book.components.universal.ProgressBar
import br.senai.sp.jandira.s_book.functions.deleteUserSQLite
import br.senai.sp.jandira.s_book.functions.saveLogin
import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.model.ResponseUsuario
import br.senai.sp.jandira.s_book.model.Usuario
import br.senai.sp.jandira.s_book.model.chat.view_model.viewModelId
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FeedScreen(
    navController: NavController,
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope?,
    viewModelQueVaiPassarOsDados: AnuncioViewModelV2,
    viewModelId: viewModelId
) {

    val TAG = "Teste FEED"

    val context = LocalContext.current

    val dadaUser = UserRepository(context).findUsers()

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

    var imagemPefil by remember {
        mutableStateOf("")
    }

    var statusPerfil by remember {
        mutableStateOf(false)
    }

    var user2 by remember {
        mutableStateOf(
            Usuario(
                0,
                "",
                "",
                "",
                "",
                "",
                false,
                "",
                "",
                "",
                "",
                "",
                "",
                id_endereco = 0
            )
        )
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

                if(cont && listAnuncios.isNotEmpty() && response.body()!!.page == page){
                    listAnunciosFeed += listAnuncios

                    cont = false
                }
            }else{
                cont = false
                Toast.makeText(context, "Não tem mais anuncios a ser mostrado", Toast.LENGTH_SHORT).show()
            }

        }

        override fun onFailure(call: Call<AnunciosBaseResponse>, t: Throwable) {
//            Log.d("ERROR_FEED", "ERROR NA CHAMADA DE FEED")
//            Log.d("ERROR_FEED-t", "$t")
            Log.d("ERROR_FEED-tmessage", "${t.message}")
            Log.d("ERROR_FEED-tstacktrace", "${t.stackTrace}")
//            Log.d("ERROR_FEED-tlocalized", t.localizedMessage!!)
//            Log.d("ERROR_FEED-tcause", "${t.cause}")
        }
    })


    LaunchedEffect(key1 = true){
            Thread{
                var array = User()

                var data = ""

                if (dadaUser.isNotEmpty()) {
                    statusPerfil = true

                    array = dadaUser[0]

                    imagemPefil = array.foto

                    data = converterData(array.dataNascimento)

                    // Cria uma chamada para o EndPoint
                    val call = RetrofitHelper.getUserByIdService().getUsuarioById(array.id)

                    // Executar a chamada
                    call.enqueue(object : Callback<ResponseUsuario> {
                        override fun onResponse(
                            call: Call<ResponseUsuario>,
                            response: Response<ResponseUsuario>
                        ) {
                            Log.e("TAG", "onResponse: ${response.body()}")
                            user2 = response.body()?.dados!!

                            deleteUserSQLite(context = context, array.id.toInt())

                            saveLogin(
                                context = context,
                                id = user2.id_usuario,
                                nome = user2.nome,
                                token = array.token,
                                email = user2.email,
                                cep = user2.cep,
                                idEndereco = user2.id_endereco,
                                foto = user2.foto,
                                dataNascimento = user2.data_nascimento,
                                logradouro = user2.logradouro,
                                bairro = user2.bairro,
                                cidade = user2.cidade,
                                ufEstado = user2.estado,
                                senha = array.senha,
                                cpf = user2.cpf
                            )
                        }

                        override fun onFailure(call: Call<ResponseUsuario>, t: Throwable) {
                            Log.e("Morreu User", "Morreu na call de user no feed")
                        }
                    })
                }
            }.start()
    }

    var isLoading by remember { mutableStateOf(false) } // Variável para controlar a visibilidade da ProgressBar


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color.White)
    ) {
        Header(navController, navRotasController, context, imagemPefil, statusPerfil)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            EscolhaFazer(
                filter = { navRotasController.navigate("Filters") },
                anuncio = {
                    if(dadaUser.isNotEmpty()){
                        navRotasController.navigate("primeiro_anunciar")
                    }else{
                        navRotasController.navigate("login")
                    }
                },
                doacao = {
                    navController.navigate("donations")
                }
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
                                navController = navController,
                                lifecycleScope = lifecycleScope,
                                onClick = {
                                   // viewModelQueVaiPassarOsDados.foto = item.foto
                                    val anunciante = getAnunciante(item.anuncio.anunciante) { usuario ->
                                        if (usuario != null) {
                                            viewModelQueVaiPassarOsDados.idAnuncio = item.anuncio.id
                                            viewModelQueVaiPassarOsDados.dadosAnuncio = item
                                            viewModelQueVaiPassarOsDados.idAnunciante = item.anuncio.anunciante.toInt()

//                                            viewModelQueVaiPassarOsDados.id = item.anuncio.id
//
//                                            viewModelQueVaiPassarOsDados.id_anunciante = item.anuncio.anunciante
//
//                                            viewModelId.id_anunciante = item.anuncio.anunciante
//                                            viewModelId.foto_anunciante = usuario.foto
//                                            viewModelId.nome_anunciante = usuario.nome
//
//                                            Log.e("Id do Anunciante", "${viewModelQueVaiPassarOsDados.id_anunciante}")
//
//                                            viewModelQueVaiPassarOsDados.nome = item.anuncio.nome
//
//                                            viewModelQueVaiPassarOsDados.generos = item.generos
//                                            viewModelQueVaiPassarOsDados.tipo_anuncio =
//                                                item.tipo_anuncio
//
//                                            viewModelQueVaiPassarOsDados.anunciante_foto = usuario.foto
//
//                                            viewModelQueVaiPassarOsDados.anunciante_nome = usuario.nome
//                                            viewModelQueVaiPassarOsDados.cidade_anuncio = usuario.cidade
//                                            viewModelQueVaiPassarOsDados.estado_anuncio = usuario.estado
//                                            viewModelQueVaiPassarOsDados.descricao =
//                                                item.anuncio.descricao
//
//                                            viewModelQueVaiPassarOsDados.ano_edicao =
//                                                item.anuncio.ano_lancamento
//                                            viewModelQueVaiPassarOsDados.autor = item.autores
//                                            viewModelQueVaiPassarOsDados.editora = item.editora
//                                            viewModelQueVaiPassarOsDados.idioma = item.idioma
//                                            viewModelQueVaiPassarOsDados.preco = item.anuncio.preco
                                        }
                                    }
                                },

                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }

                Log.e("Morreeeu", "FeedScreen: ${isLoading}", )
                if (cont == true) {
                    isLoading = true
                    ProgressBar(isDisplayed = isLoading)
                    Spacer(modifier = Modifier.height(48.dp))
                    Log.e("favela venceu", "FeedScreen: ${isLoading}", )
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
                            Log.e("favela morreu", "FeedScreen: ${isLoading}", )
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
//            Log.e("lista", "onResponse: $listAnuncios")
        }


        override fun onFailure(call: Call<AnunciosBaseResponse>, t: Throwable) {
//            Log.d("ERROR_FEED", "ERROR NA CHAMADA DE FEED")
//            Log.d("ERROR_FEED-t", "$t")
//            Log.d("ERROR_FEED-tmessage", "${t.message}")
//            Log.d("ERROR_FEED-tstacktrace", "${t.stackTrace}")
//            Log.d("ERROR_FEED-tlocalized", "${t.localizedMessage}")
//            Log.d("ERROR_FEED-tcause", "${t.cause}")
        }
    })

    return listAnuncios
}