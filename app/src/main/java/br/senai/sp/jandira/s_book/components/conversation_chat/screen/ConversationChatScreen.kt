package br.senai.sp.jandira.s_book.components.conversation_chat.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.conversation_chat.components.CardFotoMessageCliente
import br.senai.sp.jandira.s_book.components.conversation_chat.components.CardFotoMessageUser
import br.senai.sp.jandira.s_book.components.conversation_chat.components.CardMensagemCliente
import br.senai.sp.jandira.s_book.components.conversation_chat.components.CardMensagemClienteFoto
import br.senai.sp.jandira.s_book.components.conversation_chat.components.CardMensagemUser
import br.senai.sp.jandira.s_book.components.conversation_chat.components.CardMensagemUserFoto
import br.senai.sp.jandira.s_book.components.conversation_chat.components.Header
import br.senai.sp.jandira.s_book.components.conversation_chat.components.InputMenssagem
import br.senai.sp.jandira.s_book.model.chat.ChatClient
import br.senai.sp.jandira.s_book.model.chat.MesagensResponse
import br.senai.sp.jandira.s_book.model.chat.view_model.ChatViewModel
import com.google.gson.Gson
import io.socket.client.Socket
import org.json.JSONObject

@OptIn(ExperimentalMaterial3Api::class)
//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConversationChatScreen(
    navController: NavController,
    client: ChatClient,
    socket: Socket,
    chatViewModel: ChatViewModel,
    idUsuario: Int
) {

    val idChat = chatViewModel.idChat
    val idUser2 = chatViewModel.idUser2
    var foto = chatViewModel.foto
    var nome = chatViewModel.nome


    var message by remember {
        mutableStateOf("")
    }


    val listState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {


        var listaMensagens by remember {
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

        socket.emit("listMessages", idChat)
        LaunchedEffect(listaMensagens) {
            // Ouça o evento do socket
            socket.on("receive_message") { args ->
                args.let { d ->
                    if (d.isNotEmpty()) {
                        val data = d[0]
                        if (data.toString().isNotEmpty()) {
                            val mensagens =
                                Gson().fromJson(data.toString(), MesagensResponse::class.java)

                            if (mensagens.id_chat == idChat) {
                                listaMensagens = mensagens
                            }
                            Log.e("TesteIndo", "${listaMensagens.mensagens.reversed()}")
                        }
                    }
                }
            }
        }

//        socket.on("receive_message") { args ->
//            args.let { d ->
//                if (d.isNotEmpty()) {
//                    val data = d[0]
//                    if (data.toString().isNotEmpty()) {
//                        val mensagens =
//                            Gson().fromJson(data.toString(), MesagensResponse::class.java)
//
//                        listaMensagens = mensagens
//                        Log.e("TesteIndo", "${listaMensagens.mensagens.reversed()}")
//                    }
//                }
//            }
//        }


        Log.e("jojo", "Lista de Mensagens: ${listaMensagens.mensagens}")



        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(730.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.fundo),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Header(foto = foto, nome = nome) {
                    Log.d("Click", "Click")

                    navController.popBackStack()
                }

                Spacer(modifier = Modifier.height(8.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (listaMensagens.mensagens != null) {
                        LazyColumn(
                            modifier = Modifier
                                .height(730.dp)
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            state = listState
                        ) {
                            items(listaMensagens.mensagens) {

                                if (it.message == "" && it.image != "") {
                                    if (it.messageTo == idUsuario) {
                                        CardMensagemClienteFoto(
                                            menssagem = "",
                                            hora = it.hora_criacao!!.substring(0, 5),
                                            cor = Color(0xFF000000),
                                            foto = it.image
                                        )
                                    } else {
                                        CardMensagemUserFoto(
                                            menssagem = "",
                                            hora = it.hora_criacao!!.substring(0, 5),
                                            cor = Color(221, 163, 93, 255),
                                            foto = it.image,
                                            onDelete = {
                                                socket.on("deleteMessage") { args ->
                                                    args.let { d ->
                                                        if (d.isNotEmpty()) {
                                                            val idMessageDeleted = d[0] as String
                                                            listaMensagens = listaMensagens.copy(
                                                                mensagens = listaMensagens.mensagens
                                                                    .filterNot { it.message == idMessageDeleted }
                                                                    .toMutableList()
                                                            )
                                                            Log.e("IDMANO", "${idMessageDeleted}")
                                                        }
                                                    }
                                                }
                                                client.deleteMessage(it._id.toString())
                                            }
                                        )
                                    }
                                } else if (it.message != "" && it.image == "") {
                                    if (it.messageTo == idUsuario) {
                                        CardMensagemCliente(
                                            menssagem = it.message,
                                            hora = it.hora_criacao!!.substring(0, 5),
                                            cor = Color(0xFF000000)
                                        )
                                    } else {
                                        CardMensagemUser(
                                            menssagem = it.message,
                                            hora = it.hora_criacao!!.substring(0, 5),
                                            cor = Color(221, 163, 93, 255),
                                            onDelete = {
                                                socket.on("deleteMessage") { args ->
                                                    args.let { d ->
                                                        if (d.isNotEmpty()) {
                                                            val idMessageDeleted = d[0] as String
                                                            listaMensagens = listaMensagens.copy(
                                                                mensagens = listaMensagens.mensagens
                                                                    .filterNot { it.message == idMessageDeleted }
                                                                    .toMutableList()
                                                            )
                                                            Log.e("IDMANO", "${idMessageDeleted}")
                                                        }
                                                    }
                                                }
                                                client.deleteMessage(it._id.toString())
                                            }
                                        )
                                    }
                                } else {
                                    if (it.messageTo == idUsuario) {
                                        CardFotoMessageCliente(
                                            menssagem = it.message,
                                            hora = it.hora_criacao!!.substring(0, 5),
                                            cor = Color(0xFF000000),
                                            foto = it.image
                                        )
                                    } else {
                                        CardFotoMessageUser(
                                            menssagem = it.message,
                                            hora = it.hora_criacao!!.substring(0, 5),
                                            cor = Color(221, 163, 93, 255),
                                            foto = it.image
                                        ) {
                                            socket.on("deleteMessage") { args ->
                                                args.let { d ->
                                                    if (d.isNotEmpty()) {
                                                        val idMessageDeleted = d[0] as String
                                                        listaMensagens = listaMensagens.copy(
                                                            mensagens = listaMensagens.mensagens
                                                                .filterNot { it.message == idMessageDeleted }
                                                                .toMutableList()
                                                        )
                                                        Log.e("IDMANO", "${idMessageDeleted}")
                                                    }
                                                }
                                            }
                                            client.deleteMessage(it._id.toString())
                                        }
                                    }
                                }
                                LaunchedEffect(true) {
                                    listState.scrollToItem(listaMensagens.mensagens.size - 1)
                                }
                            }
                        }

                    }
                }
            }
        }



        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .border(
                    width = 0.5.dp,
                    color = Color(0xFF808080),
                    shape = RoundedCornerShape(topEnd = 8.dp, topStart = 8.dp)
                ),
        ) {
            InputMenssagem(
                mensagem = message,
                onclick = {
                    message = it

                    val json = JSONObject().apply {
                        put("messageBy", idUsuario)
                        put("messageTo", idUser2)
                        put("message", message)
                        put("image", "")
                        put("chatId", idChat)
                    }

                    Log.e("JSON", "$json")
                    // val jsonString = Json.encodeToString(json)

                    client.sendMessage(json)
                },
                navController = navController
            )
        }
    }
}

