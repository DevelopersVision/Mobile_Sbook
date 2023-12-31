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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.advertiser.components.Annunces
import br.senai.sp.jandira.s_book.components.advertiser.components.HeaderBoxAdvertiser
import br.senai.sp.jandira.s_book.components.advertiser.components.ListCategory
import br.senai.sp.jandira.s_book.model.Advertiser
import br.senai.sp.jandira.s_book.model.Anuncio
import br.senai.sp.jandira.s_book.model.AnuncioAdvertiser
import br.senai.sp.jandira.s_book.model.AnuncioAdvertiserUser
import br.senai.sp.jandira.s_book.model.AnuncioResponse
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.DadosAdvertiser
import br.senai.sp.jandira.s_book.model.Editora
import br.senai.sp.jandira.s_book.model.Endereco
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.Foto
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.GenerosAdvertiser
import br.senai.sp.jandira.s_book.model.Idioma
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.model.TipoAnuncio
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun AdvertiserScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope?,
    viewModelV2: AnuncioViewModelV2
) {


    var id = viewModelV2.idAnunciante

    Log.d("id do anunciante", "${id}")

    var usuarioAnuncios by remember {
        mutableStateOf(
            listOf<AnuncioAdvertiser>()
        )

    }


    Log.e("Porque ta vindo nullo?", "${id}")

    if (id != null) {
        Log.d("id do anunciante", "${id}")

        val call = RetrofitHelper.getAdvertiserService().getAdvertiser(id)

        call.enqueue(object : Callback<Advertiser> {
            override fun onResponse(
                call: Call<Advertiser>,
                response: Response<Advertiser>
            ) {
                val body = response.body()

                if (body != null) {
                    usuarioAnuncios = body.dados.anuncios
                } else {
                    Log.e("Erro", "Resposta do servidor é nula")
                }
            }

            override fun onFailure(call: Call<Advertiser>, t: Throwable) {
                Log.e("Erro", "Falha na chamada Retrofit: ${t.message}")
            }
        })

        // Restante do código...
    } else {
        Log.e("Erro", "idAnunciante é nulo")
    }


    // Executar a chamada


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        HeaderBoxAdvertiser(navController = navController, viewModelV2)
        ListCategory(viewModel = viewModelV2)
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
                    text = "Anuncio",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(170, 98, 49, 255),
                )
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(usuarioAnuncios) { anuncio ->

                    var shortDesc = anuncio.anuncio.descricao
                    var titleList =anuncio.anuncio.nome.split("")
                    var shortTitle = ""

                    if (shortDesc.length > 30) {
                        shortDesc = shortDesc.substring(0, 30).plus("...")
                    }
                    titleList.forEach { string ->
                        if (titleList.indexOf(string) < 4) {
                            shortTitle += "$string "
                        } else if (titleList.indexOf(string) == 4) {
                            shortTitle += "..."
                        }
                    }
                    Annunces(
                        id = anuncio.anuncio.id,
                        nome_livro = shortDesc,
                        autor = anuncio.autores[0].nome,
                        tipo_anuncio = anuncio.tipo_anuncio[0].tipo,
                        preco = anuncio.anuncio.preco,
                        foto = anuncio.foto[0].foto,
                        lifecycleScope = lifecycleScope,
                        navController = navController
                    ) {}
                }
            }
        }

        Spacer(modifier = Modifier.height(38.dp))
    }
}