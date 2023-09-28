package br.senai.sp.jandira.s_book.components.favorite.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.components.favorite.components.Card
import br.senai.sp.jandira.s_book.components.favorite.components.Header
import br.senai.sp.jandira.s_book.model.Anuncio
import br.senai.sp.jandira.s_book.model.AnunciosFavoritosBaseResponse
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.JsonFavoritados
import br.senai.sp.jandira.s_book.repository.CategoryList
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FavoritoScreen() {

    var listAnuncios by remember{
        mutableStateOf(listOf<JsonFavoritados>())
    }



    // Cria uma chamada para o EndPoint
    val call = RetrofitHelper.getAnunciosFavoritadosService().getAnunciosFavoritosByUsuarioId(3)

    Log.e("Call", "${call}")

    // Executar a chamada
    call.enqueue(object : Callback<AnunciosFavoritosBaseResponse> {
        override fun onResponse(
            call: Call<AnunciosFavoritosBaseResponse>,
            response: Response<AnunciosFavoritosBaseResponse>
        ) {
            listAnuncios = response.body()!!.anuncios
            Log.e("ListaAAAAAAAAANUNCIOS", "${response}")
            Log.e("ListaBOOOOOOOOODYY", "${response.body()}")
            Log.e("AAAAAAAAAAAAAAAA", "${listAnuncios}")

        }


        override fun onFailure(call: Call<AnunciosFavoritosBaseResponse>, t: Throwable) {

        }
    })


    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize(),
    ){
        Header()

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            items(listAnuncios) { item ->
                Card(
                    nome_livro = item.anuncio.nome,
                    ano_lancamento = item.anuncio.ano_lancamento,
                    foto = item.foto.foto,
                    autor = item.autores.nome,
                    preco = item.anuncio.preco,
                    onClick = {}
                )
            }
        }


    }
}

@Composable
@Preview
fun FavoritoScreenPreview() {
    FavoritoScreen()
}