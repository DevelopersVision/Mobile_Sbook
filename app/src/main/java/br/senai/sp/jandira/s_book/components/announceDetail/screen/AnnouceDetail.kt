package br.senai.sp.jandira.s_book.components.announceDetail.screen

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
import br.senai.sp.jandira.s_book.model.chat.view_model.ChatViewModel
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import com.google.gson.Gson
import io.socket.client.Socket


@Composable
fun AnnouceDetail(
    navController: NavController,
    viewMODEL: AnuncioViewModel,
//    socket: Socket,
//    idUsuario: Int,
//    chatViewModel: ChatViewModel,
//    client: ChatClient,
    lifecycleScope: LifecycleCoroutineScope
) {
    //Log.e("viewLuiz", "${viewMODEL.autor}")


//
//    socket.emit("createRooom")
//
//    var listUsuario by remember {
//        mutableStateOf(
//            listOf<Int>(
//                idUsuario,
//                viewMODEL.id
//            )
//        )
//    }




    // Ouça o evento do socket
//    socket.on("receive_contacts") { args ->
//        args.let { d ->
//            if (d.isNotEmpty()) {
//                val data = d[0]
//                if (data.toString().isNotEmpty()) {
//                    val chat = Gson().fromJson(data.toString(), SocketResponse::class.java)
//
//                    listaContatos = chat
//                }
//            }
//        }
//    }




    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header(viewMODEL)
            CardInformacao(viewMODEL, lifecycleScope, onClick = {

            })
            Spacer(modifier = Modifier.height(12.dp))
            FooterDescricao(viewMODEL)

        }
    }
}
