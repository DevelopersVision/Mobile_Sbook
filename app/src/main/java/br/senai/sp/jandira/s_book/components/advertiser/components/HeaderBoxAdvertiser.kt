package br.senai.sp.jandira.s_book.components.advertiser.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.model.Advertiser
import br.senai.sp.jandira.s_book.model.AnuncioAdvertiser
import br.senai.sp.jandira.s_book.model.AnuncioAdvertiserUser
import br.senai.sp.jandira.s_book.model.AnuncioNoPageBaseResponse
import br.senai.sp.jandira.s_book.model.AnuncioResponse
import br.senai.sp.jandira.s_book.model.DadosAdvertiser
import br.senai.sp.jandira.s_book.model.Editora
import br.senai.sp.jandira.s_book.model.Endereco
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.Foto
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.GenerosAdvertiser
import br.senai.sp.jandira.s_book.model.Idioma
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun HeaderBoxAdvertiser(
    navController: NavController,
    viewModel: AnuncioViewModelV2
) {


    val id = viewModel.idAnunciante

    Log.d("id do anunciante", "${id}")

    var usuarioHeader by remember {
        mutableStateOf(
            DadosAdvertiser(
                id_usuario = 0,
                nome = "",
                email = "",
                foto = "",
                cidade = "",
                estado = "",
                generos = mutableListOf(
                    GenerosAdvertiser(
                        id_genero_preferido_usuario = 0,
                        id_genero = 0,
                        nome_genero = ""
                    )
                ),
                anuncios = mutableListOf()
            )
        )
    }


    Log.e("Porque ta vindo nullo 2?", "${id}")
    val call = RetrofitHelper.getAdvertiserService().getAdvertiser(id!!)

    // Executar a chamada
    call.enqueue(object : Callback<Advertiser> {
        override fun onResponse(
            call: Call<Advertiser>,
            response: Response<Advertiser>
        ) {
            Log.e("TAG", "onResponse: ${response.body()}")
            usuarioHeader = response.body()!!.dados
            Log.e("Thiago2", "onResponse: ${usuarioHeader}")
            Log.d("nome do anunciante thiago", "${usuarioHeader}")

        }

        override fun onFailure(call: Call<Advertiser>, t: Throwable) {
            Log.e("ERR", "oMOrreu ")
        }
    })

    Log.e("VAr", "$usuarioHeader")


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF5E3D27),
                shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp),
            )
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp, bottom = 76.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_left_gray),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Text(
                text = "Perfil",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight(600),
            )
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier.size(52.dp)
            )
        }
        CardUserAdvertiser(
            nome = usuarioHeader.nome,
            foto = usuarioHeader.foto
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            InformacaoAdvertiser(
                nome = "Email",
                valor = usuarioHeader.email
            )
            InformacaoAdvertiser(
                nome = "Endereco",
                valor = "${usuarioHeader.cidade}, ${usuarioHeader.estado}"
            )
        }
    }
}