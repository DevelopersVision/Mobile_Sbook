package br.senai.sp.jandira.s_book.components.favorite.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
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
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import br.senai.sp.jandira.s_book.components.favorite.components.Card
import br.senai.sp.jandira.s_book.components.favorite.components.Header
import br.senai.sp.jandira.s_book.components.feed.screen.getAnunciante
import br.senai.sp.jandira.s_book.components.universal.NoExist
import br.senai.sp.jandira.s_book.model.AnunciosFavoritosBaseResponse
import br.senai.sp.jandira.s_book.model.JsonFavoritados
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FavoritoScreen(
    navController: NavController,
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope?,
    viewModelQueVaiPassarOsDados: AnuncioViewModel

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
                                    viewModelQueVaiPassarOsDados.foto = item.foto
                                    val anunciante = getAnunciante(item.anuncio.anunciante) { usuario ->
                                        if (usuario != null) {

                                            viewModelQueVaiPassarOsDados.nome = item.anuncio.nome

                                            viewModelQueVaiPassarOsDados.generos = item.generos
                                            viewModelQueVaiPassarOsDados.tipo_anuncio =
                                                item.tipo_anuncio

                                            viewModelQueVaiPassarOsDados.anunciante_foto = usuario.foto

                                            viewModelQueVaiPassarOsDados.anunciante_nome = usuario.nome
                                            viewModelQueVaiPassarOsDados.cidade_anuncio = usuario.cidade
                                            viewModelQueVaiPassarOsDados.estado_anuncio = usuario.estado
                                            viewModelQueVaiPassarOsDados.descricao =
                                                item.anuncio.descricao

                                            viewModelQueVaiPassarOsDados.ano_edicao =
                                                item.anuncio.ano_lancamento
                                            viewModelQueVaiPassarOsDados.autor = item.autores
                                            viewModelQueVaiPassarOsDados.editora = item.editora
                                            viewModelQueVaiPassarOsDados.idioma = item.idioma
                                            viewModelQueVaiPassarOsDados.preco = item.anuncio.preco
//                                        Log.e("Valor Preco", "${viewModelQueVaiPassarOsDados.preco}")
                                        } else {
                                            Log.e("Anunciante", "null")
                                        }
                                    }
                                    navRotasController.navigate("annouceDetail")
                                },
                                coracaoClik = {

                                },

                            )


                    }
                }
            }else{
                Spacer(modifier = Modifier.height(100.dp))
                NoExist(
                    onclick = {
                        navController.navigate("feed")
                    },
                    textTitulo = "Nenhum favorito ainda :(",
                    textSubTitulo = "Escolha o que você mais gostou",
                    textDecisão = "Ir para o início"
                )
            }
            Spacer(modifier = Modifier.height(135.dp))
        }
    }