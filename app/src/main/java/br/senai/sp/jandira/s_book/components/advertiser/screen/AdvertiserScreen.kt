package br.senai.sp.jandira.s_book.components.advertiser.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.advertiser.components.Annunces
import br.senai.sp.jandira.s_book.components.advertiser.components.HeaderBoxAdvertiser
import br.senai.sp.jandira.s_book.components.advertiser.components.ListCategory
import br.senai.sp.jandira.s_book.model.Advertiser
import br.senai.sp.jandira.s_book.model.Anuncio
import br.senai.sp.jandira.s_book.model.AnuncioAdvertiserUser
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun AdvertiserScreen(
    navController: NavController,
    viewModelV2: AnuncioViewModelV2
) {

    var listAnuncio by remember{
        mutableStateOf(
            AnuncioAdvertiserUser(
                id = 0,
                nome = "",
                ano_lancamento = 0,
                edicao = "",
                preco = 0.0,
                anunciante = 0
            ))
    }

    val call = RetrofitHelper.getAdvertiserService().getAdvertiser(1)

    // Executar a chamada
    call.enqueue(object : Callback<Advertiser> {
        override fun onResponse(
            call: Call<Advertiser>,
            response: Response<Advertiser>
        ) {
            Log.e("TAG", "onResponse: ${response.body()}", )
            val listAnuncio = response.body()?.dados?.anuncios
        }
        override fun onFailure(call: Call<Advertiser>, t: Throwable) {
        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        HeaderBoxAdvertiser(navController = navController)
        ListCategory()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 19.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = listAnuncio.nome,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(170, 98, 49, 255),
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement. spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Annunces()
                Annunces()
            }
        }

        Spacer(modifier = Modifier.height(38.dp))
    }
}