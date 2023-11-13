package br.senai.sp.jandira.s_book.components.chats.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.model.chat.ChatClient
import br.senai.sp.jandira.s_book.model.chat.SocketResponse
import br.senai.sp.jandira.s_book.model.chat.view_model.ChatViewModel
import coil.compose.AsyncImage
import com.google.gson.Gson
import io.socket.client.Socket

//@Preview()
@Composable
fun ChatScreen(
    navRotasController: NavController,
    socket: Socket,
    idUsuario: Int,
    chatViewModel: ChatViewModel,
    client: ChatClient,
    ){
    val TAG = "Teste tela chat"

    Log.e(TAG, "ChatScreen: auiiiiiiiiiiii", )

    var listaContatos by remember {
        mutableStateOf(
            SocketResponse(
                users = listOf()
            )
        )
    }

    // Ouça o evento do socket
    socket.on("receive_contacts") { args ->
        args.let { d ->
            if (d.isNotEmpty()) {
                val data = d[0]
                if (data.toString().isNotEmpty()) {
                    val chat = Gson().fromJson(data.toString(), SocketResponse::class.java)

                    listaContatos = chat
                }
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
            backgroundColor = Color(120, 79, 52, 255)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Chat",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF)
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .shadow(
                    elevation = 6.dp,
                    spotColor = Color(0xFF000000),
                    ambientColor = Color(0xFF000000)
                )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = "Conversas",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        color = Color(120, 79, 52, 255)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(
                        modifier = Modifier
                            .height(2.8.dp)
                            .width(120.dp),
                        color = Color(120, 79, 52, 255)
                    )
                }
                Text(
                    text = "Grupos",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF808080)
                )
            }
        }


        LazyColumn(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            items(listaContatos.users){

                var contato = it.users.filter { user -> user.id != idUsuario }

                Log.e("oii", "aquiiii: ${contato[0].id}")

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navRotasController.navigate("conversa_chat")
                            chatViewModel.idChat = it.id_chat
                            chatViewModel.idUser2 = contato[0].id
                            chatViewModel.foto = contato[0].foto
                            chatViewModel.nome = contato[0].nome
                            socket.emit("listMessages", it.id_chat)
                            Log.e("luiz", "ChatScreen: ${contato[0].id}", )
                        }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Card (
                            modifier = Modifier
                                .size(56.dp),
                            shape = CircleShape
                        ){
                            AsyncImage(
                                model = contato[0].foto ,
                                contentDescription = "",
                                modifier = Modifier
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = contato[0].nome,
                                fontSize = 16.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Bom trabalho, fico feliz em v...",
                                fontSize = 14.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF3B4A54)
                            )
                        }
                    }
                    Text(
                        text = it.data_criacao,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF3B4A54)
                    )
                }
            }
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = Color(0xFFCECECE)
        )
    }
}