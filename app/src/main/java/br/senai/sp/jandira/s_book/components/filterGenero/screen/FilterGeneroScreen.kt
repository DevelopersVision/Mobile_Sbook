package br.senai.sp.jandira.s_book.components.filterGenero.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.filterGenero.components.CheckGenero
import br.senai.sp.jandira.s_book.components.universal.HeaderFilter
import br.senai.sp.jandira.s_book.components.universal.SearchFilter
import br.senai.sp.jandira.s_book.model.AnunciosFavoritosBaseResponse
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.JsonFavoritados
import br.senai.sp.jandira.s_book.repository.CategoryList
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FilterGeneroScreen(
    navController: NavController
){
    var listGeneros by remember{
        mutableStateOf(listOf<Genero>())
    }

    var generosState by remember {
        mutableStateOf(value = "")
    }

    var originalListGeneros by remember {
        mutableStateOf(listOf<Genero>())
    }

    var generosSelecionados by remember {
        mutableStateOf<Set<String>>(emptySet())
    }

    val call = RetrofitHelper.getCategoryService().getGeneros()


    // Executar a chamada
    call.enqueue(object : Callback<CategoryList> {
        override fun onResponse(
            call: Call<CategoryList>,
            response: Response<CategoryList>
        ) {
            listGeneros = response.body()!!.dados
            originalListGeneros = response.body()!!.dados
        }


        override fun onFailure(call: Call<CategoryList>, t: Throwable) {

        }
    })

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HeaderFilter(
            text = "Gênero",
            onclick = {
                navController.navigate("filters")
            }
        )
        SearchFilter(
            label = "Gênero" ,
            valor = generosState,
            aoMudar = {
                generosState = it

                //listGeneros = originalListGeneros.filter { genero -> genero.nome.contains(generosState, ignoreCase = true) }
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(listGeneros.filter { genero -> genero.nome.contains(generosState, ignoreCase = true) }) {
                val isChecked = generosSelecionados.contains(it.nome)
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = it.nome,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.intermedium)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF808080),
                        )
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { isChecked ->
                                if (isChecked) {
                                    generosSelecionados = generosSelecionados + it.nome
                                } else {
                                    generosSelecionados = generosSelecionados - it.nome
                                }
                                Log.e("thiago", "${generosSelecionados}")
                            }
                        )
                    }
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFFE0E0E0)
                    )
                }
            }
        }
    }

}