package br.senai.sp.jandira.s_book.components.my_announces.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.insert_code.components.Form
import br.senai.sp.jandira.s_book.components.insert_code.components.Header
import br.senai.sp.jandira.s_book.components.my_announces.components.HeaderAnnounce
import br.senai.sp.jandira.s_book.model.AnunciosUserBaseResponse
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MyAnnounceScreen(
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope
){

    val context = LocalContext.current

    var listAnuncios by remember{
        mutableStateOf(listOf<JsonAnuncios>())
    }

    val array = UserRepository(context).findUsers()

    val user = array[0]

    // Cria uma chamada para o EndPoint
    val call = RetrofitHelper.getAnunciosByIdUserService().getAnunciosByUsuarioId(user.id)

    Log.e("Thiago", "Antes da chamada da API: ${listAnuncios}")

    // Executar a chamada
    call.enqueue(object : Callback<AnunciosUserBaseResponse> {
        override fun onResponse(
            call: Call<AnunciosUserBaseResponse>,
            response: Response<AnunciosUserBaseResponse>
        ) {
            listAnuncios = response.body()!!.anuncios
            Log.e("Thiago2", "Antes da chamada da API: ${listAnuncios}")
        }

        override fun onFailure(call: Call<AnunciosUserBaseResponse>, t: Throwable) {
            Log.d("API Call", "Depois da chamada da API: ${listAnuncios}")
        }
    })

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            HeaderAnnounce(
                onclick = { navRotasController.navigate("profile")}
            )
            LazyColumn(
                modifier = Modifier.padding(24.dp).fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(listAnuncios){item ->
                    br.senai.sp.jandira.s_book.components.favorite.components.Card(
                        nome_livro = item.anuncio.nome,
                        ano_lancamento = item.anuncio.ano_lancamento,
                        foto = item.foto[0].foto,
                        tipo_anuncio = item.tipo_anuncio[0].tipo,
                        autor = item.autores[0].nome,
                        preco = item.anuncio.preco,
                        lifecycleScope = lifecycleScope!!,
                        id = item.anuncio.id,
                        onClick = {
                            navRotasController.navigate("annouceDetail")
                        },
                        coracaoClik = {}
                    )
                }
            }
        }
    }
}