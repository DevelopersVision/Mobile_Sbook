package br.senai.sp.jandira.s_book.components.announceDetail.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.announceDetail.components.CardInformacao
import br.senai.sp.jandira.s_book.components.announceDetail.components.FooterDescricao
import br.senai.sp.jandira.s_book.components.announceDetail.components.Header
import br.senai.sp.jandira.s_book.model.chat.ChatClient
import br.senai.sp.jandira.s_book.model.chat.MesagensResponse
import br.senai.sp.jandira.s_book.model.chat.UserChat
import br.senai.sp.jandira.s_book.model.chat.view_model.ChatViewModel
import br.senai.sp.jandira.s_book.model.chat.view_model.viewModelId
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.socket.client.Socket
import org.json.JSONObject


@Composable
fun AnnouceDetail(
    navController: NavController,
    viewMODEL: AnuncioViewModel,
    socket: Socket,
    idUsuario: Int,
    chatViewModel: ChatViewModel,
    client: ChatClient,
    lifecycleScope: LifecycleCoroutineScope,
    context: Context,
    viewModelId: viewModelId
) {
    val dadaUser = UserRepository(context).findUsers()

    val fotoAnunciante = viewModelId.foto_anunciante
    val nomeAnunciante = viewModelId.nome_anunciante
    val idAnunciante = viewModelId.id_anunciante


    var listUsuario by remember {
        mutableStateOf(
            listOf<UserChat>()
        )
    }

    Log.e("estamos aquiiiii", "${listUsuario}")

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





    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header(viewMODEL)
            CardInformacao(viewMODEL, lifecycleScope, onClick = {

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
                    id = idUsuario,
                    foto = dadaUser[0].foto,
                    nome = dadaUser[0].nome
                )

                Log.w("idmeu", "id meu: $idUsuario", )

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
                                    Gson().fromJson(data.toString(), MesagensResponse::class.java)

                                newChat = chat
                                Log.e("luiz aquiiii", "AnnouceDetail: ${chat}")
                                Log.e("luiz testando dentro", "${ newChat.id_chat}", )
                                chatViewModel.idChat = newChat.id_chat
                            }
                        }
                    }
                }


                Log.e("luiz testando fora", "${ newChat.id_chat}", )
                chatViewModel.idUser2 = idAnunciante.toInt()
                chatViewModel.foto = fotoAnunciante
                chatViewModel.nome = nomeAnunciante

                navController.navigate("conversa_chat")
            })
            Spacer(modifier = Modifier.height(12.dp))
            FooterDescricao(viewMODEL)

        }
    }
}