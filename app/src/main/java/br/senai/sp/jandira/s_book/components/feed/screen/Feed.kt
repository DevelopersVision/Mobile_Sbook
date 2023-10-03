package br.senai.sp.jandira.s_book.components.feed.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.favorite.components.Card
import br.senai.sp.jandira.s_book.components.feed.components.AnunciosProximos
import br.senai.sp.jandira.s_book.components.feed.components.EscolhaFazer
import br.senai.sp.jandira.s_book.components.feed.components.Header
import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import br.senai.sp.jandira.s_book.model.AnunciosFavoritosBaseResponse
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.model.JsonFavoritados
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FeedScreen(
    navController: NavController,
    navRotasController: NavController
) {
    val context = LocalContext.current

    var listAnuncios by remember{
        mutableStateOf(listOf<JsonAnuncios>())
    }

    val call = RetrofitHelper.getAnunciosService().getAnuncios()

    Log.e("API Call", "Antes da chamada da API: ${listAnuncios}")

    // Executar a chamada
    call.enqueue(object : Callback<AnunciosBaseResponse> {
        override fun onResponse(
            call: Call<AnunciosBaseResponse>,
            response: Response<AnunciosBaseResponse>
        ) {
            listAnuncios = response.body()!!.anuncios

        }


        override fun onFailure(call: Call<AnunciosBaseResponse>, t: Throwable) {
            Log.d("API Call", "Depois da chamada da API: ${listAnuncios}")
        }
    })

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        color = Color(0xFFF5F5F5)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Header(navController, navRotasController, context)
            EscolhaFazer()
            Spacer(modifier = Modifier.height(16.dp))
            androidx.compose.material.Text(
                modifier = Modifier
                    .padding(start = 21.dp),
                text = "Anúncios mais próximos",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(400),
                color = Color.Black,
            )
            Spacer(modifier = Modifier.height(18.dp))
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(335.dp)
            ) {
                items(listAnuncios) { item ->
                    Card(
                        nome_livro = item.anuncio.nome,
                        ano_lancamento = item.anuncio.ano_lancamento,
                        foto = item.foto[0].foto,
                        tipo_anuncio = item.tipo_anuncio[0].tipo,
                        autor = item.autores[0].nome,
                        preco = item.anuncio.preco,
                        onClick = {
                            navRotasController.navigate("annouceDetail")
                        }
                    )
                }
            }

        }


    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    val navController = rememberNavController()

    FeedScreen(navController = navController, navController)
}