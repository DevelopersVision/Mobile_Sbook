package br.senai.sp.jandira.s_book.components.announce.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.announce.components.BoxAnuncio
import br.senai.sp.jandira.s_book.components.announce.components.DescricaoAnuncioBox
import br.senai.sp.jandira.s_book.components.announce.components.EspecificacoesAnuncioBox
import br.senai.sp.jandira.s_book.components.announce.components.PhotoCarousel
import br.senai.sp.jandira.s_book.model.Anuncio
import br.senai.sp.jandira.s_book.model.AnuncioResponseById
import br.senai.sp.jandira.s_book.model.Editora
import br.senai.sp.jandira.s_book.model.Endereco
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.Idioma
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.model.chat.MesagensResponse
import br.senai.sp.jandira.s_book.model.chat.UserChat
import br.senai.sp.jandira.s_book.model.chat.view_model.ChatViewModel
import br.senai.sp.jandira.s_book.model.chat.view_model.viewModelId
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.socket.client.Socket
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AnnounceScreen(
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: AnuncioViewModelV2,
    navController: NavController,
    viewModelId: viewModelId,
    socket: Socket?,
    chatViewModel: ChatViewModel,
    navRotasController: NavController
) {

    val context = LocalContext.current

    val dadaUser = UserRepository(context).findUsers()

    val fotoAnunciante = viewModelId.foto_anunciante
    val nomeAnunciante = viewModelId.nome_anunciante
    val idAnunciante = viewModelId.id_anunciante

    var listUsuario by remember {
        mutableStateOf(
            listOf<UserChat>()
        )
    }

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

    var newChat by remember {
        mutableStateOf(
            MesagensResponse(
                status = 0,
                message = "",
                id_chat = "",
                usuarios = listOf(),
                data_criacao = "",
                hora_criacao = "",
                mensagens = mutableStateListOf()
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

            if(response.isSuccessful){
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 32.dp)
    ) {
        items(1){
            Log.e("ResponseFotos", "${dadosAnuncio}" )
            PhotoCarousel(jsonAnuncios = dadosAnuncio)
            Spacer(modifier = Modifier.height(5.dp))
            BoxAnuncio(
                dadosAnuncio = dadosAnuncio,
                context = context,
                lifecycleScope = lifecycleScope,
                navRotasController = navController,
                viewModel = viewModel,
                onClick = {
                    if (socket != null) {
                        //                val jsonUser1 = JSONObject().apply {
//                    put("id", idUsuario )
//                    put("foto", dadaUser[0].foto)
//                    put("nome", dadaUser[0].nome)
//                }
//
//                val jsonUserAnunciante = JSONObject().apply {
//                    put("id", idAnunciante)
//                    put("foto", fotoAnunciante)
//                    put("nome", nomeAnunciante)
//                }

                        val jsonUser1 = UserChat(
                            id = dadaUser[0].id.toInt(),
                            foto = dadaUser[0].foto,
                            nome = dadaUser[0].nome
                        )

                        Log.w("idmeu", "id meu: ${dadaUser[0].id.toInt()}")

                        val jsonUserAnunciante = UserChat(
                            id = idAnunciante.toInt(),
                            foto = fotoAnunciante,
                            nome = nomeAnunciante
                        )

                        listUsuario = listUsuario + jsonUser1

                        listUsuario = listUsuario + jsonUserAnunciante

//                val jsonBody = JSONObject().apply {
////                    val usersArray = JsonArray()
////
////                    if (listUsuario.isNotEmpty()) {
////                        for (user in listUsuario) {
////                            val userObject = JsonObject().apply {
////                                addProperty("id", user.id)
////                                addProperty("nome", user.nome)
////                                addProperty("foto", user.foto)
////                            }
////                            usersArray.add(userObject)
////                        }
////                    }
//
//                    accumulate("users", listUsuario)
//                }

                        val jsonBody = JsonObject().apply {
                            val usersArray = JsonArray()

                            for (user in listUsuario) {
                                val userObject = JsonObject().apply {
                                    addProperty("id", user.id)
                                    addProperty("nome", user.nome)
                                    addProperty("foto", user.foto)
                                }
                                usersArray.add(userObject)
                            }

                            add("users", usersArray)
                            //addProperty("status", true)
                        }


                        socket.emit("createRooom", jsonBody)


                        // Ouça o evento do socket
                        socket.on("newChat") { args ->
                            args.let { d ->
                                if (d.isNotEmpty()) {
                                    val data = d[0]

                                    Log.e("Data", "$data")
                                    if (data.toString().isNotEmpty()) {
                                        val chat =
                                            Gson().fromJson(
                                                data.toString(),
                                                MesagensResponse::class.java
                                            )

                                        newChat = chat
                                        Log.e("luiz aquiiii", "AnnouceDetail: ${chat}")
                                        Log.e("luiz testando dentro", "${newChat.id_chat}")
                                        chatViewModel.idChat = newChat.id_chat
                                    }
                                }
                            }
                        }



                        Log.e("luiz testando fora", "${newChat.id_chat}")
                        chatViewModel.idUser2 = idAnunciante.toInt()
                        chatViewModel.foto = fotoAnunciante
                        chatViewModel.nome = nomeAnunciante

                        navRotasController.navigate("conversa_chat")
                    } else {
                        navRotasController.navigate("login")
                    }
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            DescricaoAnuncioBox(descricao = dadosAnuncio.anuncio.descricao)
            Spacer(modifier = Modifier.height(30.dp))
            EspecificacoesAnuncioBox(dadosAnuncios = dadosAnuncio)
        }
    }
}