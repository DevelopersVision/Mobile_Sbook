package br.senai.sp.jandira.s_book.components.favorite.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.favorite.components.AddFavorite
import br.senai.sp.jandira.s_book.components.favorite.components.Card
import br.senai.sp.jandira.s_book.components.favorite.components.Header
import br.senai.sp.jandira.s_book.model.AnunciosFavoritosBaseResponse
import br.senai.sp.jandira.s_book.model.JsonFavoritados
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FavoritoScreen(
    navController: NavController,
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope?
) {

    val context = LocalContext.current

    var listAnuncios by remember{
        mutableStateOf(listOf<JsonFavoritados>())
    }

    Log.e("thiago", "Antes da chamada da API1: ${listAnuncios}")

    var filterState by remember {
        mutableStateOf("")
    }

    val array = UserRepository(context).findUsers()

    val user = array[0]

    // Cria uma chamada para o EndPoint
    val call = RetrofitHelper.getAnunciosFavoritadosService().getAnunciosFavoritosByUsuarioId(user.id)

    Log.e("API Call", "Antes da chamada da API1: ${listAnuncios}")

    // Executar a chamada
    call.enqueue(object : Callback<AnunciosFavoritosBaseResponse> {
        override fun onResponse(
            call: Call<AnunciosFavoritosBaseResponse>,
            response: Response<AnunciosFavoritosBaseResponse>
        ) {
            listAnuncios = response.body()!!.anuncios
            Log.e("thiago2", "Antes da chamada da API1: ${listAnuncios}")
        }


        override fun onFailure(call: Call<AnunciosFavoritosBaseResponse>, t: Throwable) {
            Log.e("API Call", "Depois da chamada da API: ${listAnuncios}")
        }
    })

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            Header(
                navController = navController,
                navRotasController = navRotasController
            )
            Spacer(modifier = Modifier.height(46.dp))
            Text(
                text = "Veja o que você mais gostou",
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier.width(228.dp)
            )

            TextField(
                value = filterState,
                onValueChange = {
                    filterState = it
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black,
                    disabledIndicatorColor = Color.Black,
                    errorIndicatorColor = Color.Black
                ),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.pesquisa),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
            if(listAnuncios.isNotEmpty()){
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(listAnuncios.filter { it.anuncio.nome.contains(filterState, ignoreCase = true) }) { item ->
                        AnimatedVisibility(
                            visible = !listAnuncios.contains(item),
                            enter = expandVertically(),
                            exit = slideOutHorizontally()
                        ) {
                        }
                            Card(
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
                                coracaoClik = {

                                }
                            )


                    }
                }
            }else{
                Spacer(modifier = Modifier.height(100.dp))
                AddFavorite(
                    onclick = {
                        navController.navigate("feed")
                    }
                )
            }
            Spacer(modifier = Modifier.height(135.dp))
        }
    }



//@Composable
//@Preview
//fun FavoritoScreenPreview() {
//    FavoritoScreen()
//}