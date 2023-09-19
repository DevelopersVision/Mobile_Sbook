package br.senai.sp.jandira.s_book.components.category.components

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.repository.CategoryList
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Preview(showSystemUi = true)
@Composable
fun ListCategory() {

    var listCategory by remember{
        mutableStateOf(listOf<Genero>())
    }

    //Cria uma chamada para o EndPoint
    val call = RetrofitHelper.getCategoryService().getGeneros()

    //Executar a chamada
    call.enqueue(object : Callback<CategoryList>{
        override fun onResponse(
            call: Call<CategoryList>,
            response: Response<CategoryList>
        ) {
            listCategory = response.body()!!.dados
        }

        override fun onFailure(call: Call<CategoryList>, t: Throwable) {

        }
    })

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 144.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        items(listCategory){
            var isChecked by remember { mutableStateOf(false) }

            Row (
                modifier = Modifier
                    .height(30.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFAA6231),
                        shape = RoundedCornerShape(size = 8.dp)
                    ),
                horizontalArrangement = Arrangement.spacedBy(3.dp),
                verticalAlignment = Alignment.CenterVertically
            ){

                Text(
                    text = it.nome,
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000)
                )
            }
        }
    }

}


