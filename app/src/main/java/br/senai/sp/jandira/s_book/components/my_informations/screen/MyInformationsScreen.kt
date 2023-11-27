package br.senai.sp.jandira.s_book.components.my_informations.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.my_informations.components.MyAdress
import br.senai.sp.jandira.s_book.components.my_informations.components.MyCategories
import br.senai.sp.jandira.s_book.components.my_informations.components.UserInformations
import br.senai.sp.jandira.s_book.components.perfil.components.converterData
import br.senai.sp.jandira.s_book.components.universal.HeaderProfile
import br.senai.sp.jandira.s_book.model.ResponseUsuarioV2
import br.senai.sp.jandira.s_book.model.UsuarioV2
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MyInformationsScreen(
    navController: NavController
) {
    val context = LocalContext.current

    val dadosUser = UserRepository(context).findUsers()

    var user by remember {
        mutableStateOf(
            UsuarioV2(
                id_usuario = 0,
                nome = "",
                data_nascimento = "",
                data_criacao = "",
                email = "",
                cpf = "",
                status_usuario = true,
                foto = "",
                logradouro = "",
                bairro = "",
                cidade = "",
                estado = "",
                cep = "",
                id_endereco = 0,
                generos = listOf()
            )
        )
    }

    // Cria uma chamada para o EndPoint
    val call = RetrofitHelper.getUserByIdService().getUsuarioByIdProfile(dadosUser[0].id)

    // Executar a chamada
    call.enqueue(object : Callback<ResponseUsuarioV2> {
        override fun onResponse(
            call: Call<ResponseUsuarioV2>,
            response: Response<ResponseUsuarioV2>
        ) {
            user = response.body()!!.dados
        }

        override fun onFailure(call: Call<ResponseUsuarioV2>, t: Throwable) {
            Log.e("Morreu User", "Morreu na call de user no feed")
        }
    })

    val data = converterData(user.data_nascimento)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 16.dp)
            .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        HeaderProfile {
            navController.popBackStack()
        }
        AsyncImage(
            model = user.foto,
            contentDescription = "",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x40000000),
                    ambientColor = Color(0x40000000)
                )
        )
        UserInformations(context, user.nome, user.email, user.cep, data)
        MyAdress(context)
        MyCategories(user.generos)
    }
}

@Preview
@Composable
fun Preview() {
    val navController = rememberNavController()

    MyInformationsScreen(navController = navController)

}