package br.senai.sp.jandira.s_book.components.advertiser.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.model.Advertiser
import br.senai.sp.jandira.s_book.model.AnuncioAdvertiser
import br.senai.sp.jandira.s_book.model.AnuncioAdvertiserUser
import br.senai.sp.jandira.s_book.model.AnuncioResponse
import br.senai.sp.jandira.s_book.model.DadosAdvertiser
import br.senai.sp.jandira.s_book.model.Editora
import br.senai.sp.jandira.s_book.model.Endereco
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.Foto
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.GeneroProfileV2
import br.senai.sp.jandira.s_book.model.GenerosAdvertiser
import br.senai.sp.jandira.s_book.model.Idioma
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun ListCategory(
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
//    autores = mutableListOf(),
//                            editora = Editora(
//                                id = 0,
//                                nome = "",
//                            ),
//                            endereco = Endereco(
//                                estado = "",
//                                cidade = "",
//                                bairro = ""
//                            ),
//                            estado_livro = EstadoLivro(
//                                id = 0,
//                                estado = ""
//                            ),
//                            foto = mutableListOf(
//                                Foto(
//                                    id = 0,
//                                    foto = ""
//                                )
//                            ),
//                            generos = mutableListOf(
//                                Genero(
//                                    id = 0,
//                                    nome = ""
//                                )
//                            ),
//                            idioma = Idioma(
//                                id = 0,
//                                nome = ""
//                            ),
//                            tipo_anuncio = mutableListOf()

    val call = RetrofitHelper.getAdvertiserService().getAdvertiser(id)

    // Executar a chamada
    call.enqueue(object : Callback<Advertiser> {
        override fun onResponse(
            call: Call<Advertiser>,
            response: Response<Advertiser>
        ) {
            if (response.isSuccessful) {
                val dados = response.body()?.dados
                if (dados != null) {
                    usuarioHeader = dados
                    Log.e("Thiago2", "onResponse: $usuarioHeader")
                    Log.d("nome do anunciante thiago", "${usuarioHeader.nome}")
                } else {
                    // Tratar o caso em que dados é nulo
                }
            } else {
                // Tratar a resposta não bem-sucedida
            }

        }

        override fun onFailure(call: Call<Advertiser>, t: Throwable) {
            Log.e("ERR", "oMOrreu ")
        }
    })

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
                text = "Categorias",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(170, 98, 49, 255),
            )
        }

//        LazyHorizontalGrid(
//            rows = GridCells.Adaptive(50.dp) ,
//            contentPadding = PaddingValues(4.dp),
//            modifier = Modifier
//                .fillMaxWidth(),
//            horizontalArrangement = Arrangement.spacedBy(5.dp)
//        ) {
//            items(usuarioHeader.generos) { item ->
//
//
//            }
//        }


        val pairs = usuarioHeader.generos.chunked(3)

        for (pair in pairs) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                for (item in pair) {

                    Column(
                        modifier = Modifier
                            .height(40.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFAA6231),
                                    shape = RoundedCornerShape(size = 8.dp)
                                )
                                .background(Color(0xFFFFFFFF))
                                .padding(18.5.dp, 7.dp),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.nome_genero,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = FontFamily(Font(R.font.intermedium)),
                                    fontWeight = FontWeight(600),
                                    color = Color.Black
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

