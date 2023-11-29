package br.senai.sp.jandira.s_book.components.tela_generica.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ScrollState
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.feed.components.AnunciosProximos
import br.senai.sp.jandira.s_book.components.feed.screen.getAnunciante
import br.senai.sp.jandira.s_book.components.tela_generica.components.Header
import br.senai.sp.jandira.s_book.components.universal.NoExist
import br.senai.sp.jandira.s_book.components.universal.ProgressBar
import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import br.senai.sp.jandira.s_book.view_model.ViweModelDosFiltros
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun GenericScreen(
    navController: NavController,
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope?,
    viewModelQueVaiPassarOsDados: AnuncioViewModel,
    viewModelQueVaiReceberOsgeneros: ViweModelDosFiltros
) {

    val context = LocalContext.current

    var listAnunciosFiltrados by remember {
        mutableStateOf(listOf<JsonAnuncios>())
    }

    var status by remember {
        mutableStateOf(false)
    }


    Log.e("Qu que ta vindo na viewmodel?", "${viewModelQueVaiReceberOsgeneros.estadoLivro}")
    Log.e("genero na viewmodel?", "${viewModelQueVaiReceberOsgeneros.generos}")

    val call = RetrofitHelper.getAnunciosFiltradosService().getAnunciosFiltrados(
        arrayGeneros = viewModelQueVaiReceberOsgeneros.generos,
        arrayEstadoLivro = viewModelQueVaiReceberOsgeneros.estadoLivro
    )


    call.enqueue(object : Callback<AnunciosBaseResponse> {
        override fun onResponse(
            call: Call<AnunciosBaseResponse>, response: Response<AnunciosBaseResponse>
        ) {
            Log.e("Responde . BODY: ", "${response.body()}")

            if(response.body() != null){
                listAnunciosFiltrados = response.body()!!.anuncios
            }else{
                status = true
            }
        }

        override fun onFailure(call: Call<AnunciosBaseResponse>, t: Throwable) {
            Toast.makeText(context, "SERVIÇO ESTÁ FORA DO AR TENTE MAIS TARDE", Toast.LENGTH_LONG)
                .show()
        }
    })

    var isLoading by remember { mutableStateOf(false) } // Variável para controlar a visibilidade da ProgressBar

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Header(text = "Resultado") {
            navRotasController.navigate("filters")
        }

        if(status){
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                NoExist(
                    onclick = {
                        //navRotasController.navigate("primeiro_anunciar")
                    },
                    textTitulo = "Nenhum anúncio ainda :(",
                    textSubTitulo = "Tente mudar a sua pesquisa",
                    textDecisão = ""
                )
            }
        }else if (listAnunciosFiltrados.isEmpty()) {
            isLoading == true
            ProgressBar(isDisplayed = !isLoading)
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                val pairs = listAnunciosFiltrados.chunked(2)

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
                                    val anunciante =
                                        getAnunciante(item.anuncio.anunciante) { usuario ->
                                            if (usuario != null) {

                                                viewModelQueVaiPassarOsDados.nome =
                                                    item.anuncio.nome

                                                viewModelQueVaiPassarOsDados.generos = item.generos
                                                viewModelQueVaiPassarOsDados.tipo_anuncio =
                                                    item.tipo_anuncio

                                                viewModelQueVaiPassarOsDados.anunciante_foto =
                                                    usuario.foto

                                                viewModelQueVaiPassarOsDados.anunciante_nome =
                                                    usuario.nome
                                                viewModelQueVaiPassarOsDados.cidade_anuncio =
                                                    usuario.cidade
                                                viewModelQueVaiPassarOsDados.estado_anuncio =
                                                    usuario.estado
                                                viewModelQueVaiPassarOsDados.descricao =
                                                    item.anuncio.descricao

                                                viewModelQueVaiPassarOsDados.ano_edicao =
                                                    item.anuncio.ano_lancamento
                                                viewModelQueVaiPassarOsDados.autor = item.autores
                                                viewModelQueVaiPassarOsDados.editora = item.editora
                                                viewModelQueVaiPassarOsDados.idioma = item.idioma
                                                viewModelQueVaiPassarOsDados.preco =
                                                    item.anuncio.preco
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
            }
        }
    }

    Log.e("Q porra e esaaaaaaaa", "${listAnunciosFiltrados} AAAAAAAa")

}