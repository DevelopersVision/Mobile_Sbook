package br.senai.sp.jandira.s_book.components.conversation_chat.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.conversation_chat.components.CardMensagemCliente
import br.senai.sp.jandira.s_book.components.conversation_chat.components.CardMensagemUser
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


    var message by remember {
        mutableStateOf("")
    }



    Column(
        modifier = Modifier
            .padding(24.dp)
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


        // Ouça o evento do socket
        socket.on("receive_message") { args ->
            args.let { d ->
                if (d.isNotEmpty()) {
                    val data = d[0]
                    if (data.toString().isNotEmpty()) {
                        val mensagens =
                            Gson().fromJson(data.toString(), MesagensResponse::class.java)

                        listaMensagens = mensagens
                        Log.e("TesteIndo", "${listaMensagens.mensagens.reversed()}")
                    }
                }
            }
        }


        Log.e("jojo", "Lista de Mensagens: ${listaMensagens.mensagens}")



        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(painter = painterResource(id = R.drawable.seta_voltar),
                    contentDescription = "",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {})
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.susanna_profile),
                        contentDescription = "",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(56.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Max Kellerman",
                        fontSize = 18.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000)
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    Card(
                        modifier = Modifier.size(8.dp), backgroundColor = Color.Black
                    ) {}
                    Card(
                        modifier = Modifier.size(8.dp), backgroundColor = Color.Black
                    ) {}
                    Card(
                        modifier = Modifier.size(8.dp), backgroundColor = Color.Black
                    ) {}
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                modifier = Modifier
                    .width(140.dp)
                    .height(32.dp),
                backgroundColor = Color(0xFFD9D9D9),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "3 de agosto de 2023",
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                LazyColumn(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(listaMensagens.mensagens.reversed()) {
                        if (it.messageTo == idUsuario) {
                            CardMensagemUser(
                                menssagem = it.message ,
                                hora = it.hora_criacao!!,
                                envio = it.messageBy,
                                cor = Color(221, 163, 93, 255)
                            )
                        } else {
                            CardMensagemCliente(
                                menssagem = it.message,
                                hora = it.hora_criacao!!,
                                envio = it.messageBy,
                                cor = Color(0xFF000000)
                            )
                        }
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(
                value =  message,
                onValueChange = {
                    message = it
                },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "",
                            tint = Color.Black
                        )
                    }
                },
                shape = RoundedCornerShape(50.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.cinza),
                    unfocusedBorderColor = colorResource(id = R.color.cinza)
                )
            )

            Log.e("felipe", "ConversationChatScreen: ${idUsuario}", )
            Log.e("felipe", "ConversationChatScreen: ${idUser2}", )
            Button(
                onClick = {

                    val json = JSONObject().apply {
                        put("messageBy", idUsuario)
                        put("messageTo", idUser2)
                        put("message", message)
                        put("image", "")
                        put("chatId", idChat)
                    }

                    Log.e("JSON", "$json", )
                    // val jsonString = Json.encodeToString(json)

                    client.sendMessage(json)
                },
                modifier = Modifier.size(50.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(Color(221, 163, 93, 255))
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_send_24),
                    contentDescription = ""
                )
            }
        }
    }
}