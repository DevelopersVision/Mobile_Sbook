package br.senai.sp.jandira.s_book.components.category.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.view_model.UserCategoryViewModel
import br.senai.sp.jandira.s_book.repository.CategoryList
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier,
    viewModel: UserCategoryViewModel
) {

    var listCategory by remember{
        mutableStateOf(listOf<Genero>())
    }

    // Cria uma chamada para o EndPoint
    val call = RetrofitHelper.getCategoryService().getGeneros()

    // Executar a chamada
    call.enqueue(object : Callback<CategoryList>{
        override fun onResponse(
            call: Call<CategoryList>,
            response: Response<CategoryList>
        ) {
            listCategory = response.body()!!.dados
            val id = response.body()!!.dados[0].id
        }

        override fun onFailure(call: Call<CategoryList>, t: Throwable) {

        }
    })

    var arrayGeneros by remember { mutableStateOf(listOf<Genero>()) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxHeight(0.85f)
    ) {
        items(listCategory) { it ->
            var cor by remember{
                mutableStateOf(0x5C2C0C)
            }

            var isChecked by remember { mutableStateOf(false) }

            var altura = 30

            if(it.nome.length > 26){
                altura = 48
            }

            Row (

            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(altura.dp)
                        .border(
                            width = 1.dp,
                            color = Color(170, 98, 49, 255),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .clickable {
                            val nome = it.nome
                            val id = it.id

                            if (!isChecked) {
                                isChecked = true
                                cor = 0x5C2C0C
                                var jsonGenero = Genero(it.id, it.nome)
                                arrayGeneros = arrayGeneros + jsonGenero
                                Log.e("Murilo e Luiz e Eu", "${arrayGeneros}")
                            } else {
                                isChecked = false
                                cor = 0xFFCCFFFF.toInt()
                                arrayGeneros = arrayGeneros.filter { it.id != id }
                                Log.e("Murilo e Luiz e Eu", "${arrayGeneros}")
                            }
                        },
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor =
                    if (!isChecked) {
                        Color(0xFFFFFFFF)
                    } else {
                        Color(170, 98, 49, 255)
                    }

                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = it.nome,
                            fontSize = 14.sp,
                            fontWeight = FontWeight(600),
                            color = if (!isChecked){
                                Color.Black
                            } else {
                                Color.White
                            },
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
        }
        viewModel.generos_preferidos = arrayGeneros
    }
}


@Composable
fun ListCategory(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope?,
    viewModel: UserCategoryViewModel
) {
    FavoriteCollectionsGrid(viewModel = viewModel)
}