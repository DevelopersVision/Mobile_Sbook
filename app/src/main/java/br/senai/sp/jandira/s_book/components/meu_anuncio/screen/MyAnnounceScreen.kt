package br.senai.sp.jandira.s_book.components.meu_anuncio.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.announce.components.DescricaoAnuncioBox
import br.senai.sp.jandira.s_book.components.announce.components.EspecificacoesAnuncioBox
import br.senai.sp.jandira.s_book.components.meu_anuncio.components.BoxMyAnnounce
import br.senai.sp.jandira.s_book.components.meu_anuncio.components.PhotoCarouselMyAnnounce
import br.senai.sp.jandira.s_book.model.Anuncio
import br.senai.sp.jandira.s_book.model.AnuncioResponseById
import br.senai.sp.jandira.s_book.model.Editora
import br.senai.sp.jandira.s_book.model.Endereco
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.Idioma
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.model.chat.UserChat
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MyAnnounceScreen(
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: AnuncioViewModelV2,
    navController: NavController,
    navRotasController: NavController
) {

    val context = LocalContext.current

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val scope = rememberCoroutineScope()

    //val dadosUser = UserRepository(context).findUsers()

    var dadosAnuncio by remember {
        mutableStateOf(
            JsonAnuncios(
                anuncio = Anuncio(
                    id = 0,
                    nome = "",
                    ano_lancamento = 0,
                    data_criacao = "",
                    status_anuncio = false,
                    edicao = "",
                    preco = 0.0,
                    descricao = "",
                    numero_paginas = 0,
                    anunciante = 0,
                    isbn = ""
                ),
                idioma = Idioma(
                    id = 0,
                    nome = ""
                ),
                endereco = Endereco(
                    cidade = "",
                    estado = "",
                    bairro = ""
                ),
                estado_livro = EstadoLivro(
                    id = 0,
                    estado = ""
                ),
                editora = Editora(
                    id = 0,
                    nome = ""
                ),
                foto = listOf(),
                generos = listOf(),
                tipo_anuncio = listOf(),
                autores = listOf(),
                anunciante = UserChat(
                    id = 0,
                    nome = "",
                    foto = ""
                )
            )
        )
    }


    val call = RetrofitHelper.getAnunciosService().getAnuncioByID(viewModel.idAnuncio!!)

    // Executar a chamada
    call.enqueue(object : Callback<AnuncioResponseById> {
        override fun onResponse(
            call: Call<AnuncioResponseById>, response: Response<AnuncioResponseById>
        ) {
            Log.e("ResponseAnuncio", "resposta: $response")

            if (response.isSuccessful) {
                dadosAnuncio = response.body()!!.anuncios
            }
        }

        override fun onFailure(call: Call<AnuncioResponseById>, t: Throwable) {
            Toast.makeText(
                context,
                "SERVIDOR ESTÁ FORA DO AR, TENTE NOVAMENTE MAIS TARDE",
                Toast.LENGTH_SHORT
            ).show()
            Log.d("ERROR_FEED-tmessage", "${t.message}")
            Log.d("ERROR_FEED-tstacktrace", "${t.stackTrace}")
        }
    })


    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(20.dp),
        sheetElevation = 10.dp,
        sheetContent = {
            viewModel.dadosAnuncio = dadosAnuncio
            AdOptionsScreen(navController = navController, lifecycleScope = lifecycleScope, navRotasController = navRotasController, viewModelV2 = viewModel)
        },
        sheetBackgroundColor = Color.White,
        sheetPeekHeight = 0.dp,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 12.dp, vertical = 32.dp)
        ) {
            items(1) {
                Log.e("ResponseFotos", "${dadosAnuncio}")
                PhotoCarouselMyAnnounce(jsonAnuncios = dadosAnuncio) {
                    scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                BoxMyAnnounce(
                    dadosAnuncio = dadosAnuncio,
                    context = context,
                    lifecycleScope = lifecycleScope,
                    navRotasController = navController
                )
                Spacer(modifier = Modifier.height(20.dp))
                DescricaoAnuncioBox(descricao = dadosAnuncio.anuncio.descricao)
                Spacer(modifier = Modifier.height(30.dp))
                EspecificacoesAnuncioBox(dadosAnuncios = dadosAnuncio)
            }
        }
    }
}