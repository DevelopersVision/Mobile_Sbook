package br.senai.sp.jandira.s_book.components.announceDetail.screen

import android.content.Context
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import br.senai.sp.jandira.s_book.model.chat.SocketResponse
import br.senai.sp.jandira.s_book.model.chat.UserChat
import br.senai.sp.jandira.s_book.model.chat.view_model.ChatViewModel
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import com.google.gson.Gson
import io.socket.client.Socket


@Composable
fun AnnouceDetail(
    navController: NavController,
    viewMODEL: AnuncioViewModel,
    socket: Socket,
    idUsuario: Int,
    chatViewModel: ChatViewModel,
    client: ChatClient,
    lifecycleScope: LifecycleCoroutineScope,
    context: Context
) {
    //Log.e("viewLuiz", "${viewMODEL.autor}")

    val userRating by remember { mutableStateOf(0) }

    val dadaUser = UserRepository(context).findUsers()




    var listUsuario by remember {
        mutableStateOf(
            listOf(
                UserChat(
                    id = idUsuario,
                    foto = dadaUser[0].foto,
                    nome = dadaUser[0].nome
                ),
                UserChat(
                    id = viewMODEL.id_anunciante!!.toInt(),
                    foto = viewMODEL.anunciante_foto!!,
                    nome = chatViewModel.nome
                )
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

                socket.emit("createRooom", listUsuario)

                navController.navigate("conversa_chat")
                chatViewModel.idChat = listUsuario[0].id.toString()
                chatViewModel.idUser2 = listUsuario[1].id
                chatViewModel.foto = listUsuario[1].foto
                chatViewModel.nome = listUsuario[1].nome

            })
            Spacer(modifier = Modifier.height(12.dp))
            FooterDescricao(viewMODEL)

        }
    }
}
