package br.senai.sp.jandira.s_book.components.pesquisar.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.favorite.components.Card
import br.senai.sp.jandira.s_book.components.feed.components.AnunciosProximos
import br.senai.sp.jandira.s_book.components.feed.screen.getAnunciante
import br.senai.sp.jandira.s_book.components.universal.HeaderFilter
import br.senai.sp.jandira.s_book.components.universal.SearchFilter
import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun SearchScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope?,
    viewModelQueVaiPassarOsDados: AnuncioViewModel
){

    var pesquisar by remember {
        mutableStateOf(value = "")
    }

    val context = LocalContext.current

    var listAnuncios by remember {
        mutableStateOf(listOf<JsonAnuncios>())
    }



    val call = RetrofitHelper.getAnunciosService().getAnuncios(1)

    // Executar a chamada
    call.enqueue(object : Callback<AnunciosBaseResponse> {
        override fun onResponse(
            call: Call<AnunciosBaseResponse>, response: Response<AnunciosBaseResponse>
        ) {
            listAnuncios = response.body()!!.anuncios
        }


        override fun onFailure(call: Call<AnunciosBaseResponse>, t: Throwable) {
            // Log.d("API Call", "Depois da chamada da API: ${listAnuncios}")
        }
    })


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HeaderFilter(
                text = "Procurar"
            ) {
                navController.navigate("feed")
            }
            SearchFilter(
                label = "Digite nome, gênero ou ..." ,
                valor =  pesquisar ,
                aoMudar = {
                    pesquisar = it
                }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(listAnuncios.filter  { anuncio -> anuncio.anuncio.nome.contains(pesquisar, ignoreCase = true) }) { item ->
                    if ( listAnuncios !== pesquisar.toList()){
                        Card(
                            nome_livro = item.anuncio.nome,
                            ano_lancamento = item.anuncio.ano_lancamento,
                            foto = item.foto[0].foto,
                            tipo_anuncio = item.tipo_anuncio[0].tipo,
                            autor = item.autores[0].nome,
                            preco = item.anuncio.preco,
                            lifecycleScope = lifecycleScope!!,
                            id = item.anuncio.id,
                            onClick = {},
                            coracaoClik = {}
                        )

                    }else{

                    }
                }
            }

        }
    }
}