package br.senai.sp.jandira.s_book.components.edit_user.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collection.mutableVectorOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.model.GeneroProfileV2
import br.senai.sp.jandira.s_book.view_model.UserGenresViewModel

@Composable
fun MyCategoriesEditUser(
    generos: List<GeneroProfileV2>,
    navController: NavController,
    userGenresViewModel: UserGenresViewModel,
    onChangeLista: (List<GeneroProfileV2>) -> Unit
) {

    var lista by remember {
        mutableStateOf(listOf<GeneroProfileV2>())
    }

    lista = generos

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categorias",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(170, 98, 49, 255),
            )
            Text(
                modifier = Modifier.clickable {
                    Log.e("Saida Lista", "${lista}" )
                    userGenresViewModel.generos = lista
                    navController.navigate("NewFavoriteGenres")
                },
                text = "adicionar mais",
                fontSize = 12.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF9F9898),
            )
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(lista) {
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color(0xFFAA6231),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .background(Color(0xFFFFFFFF))
                        .clickable {
                            lista = lista.filter { category ->
                                category != it
                            }

                            onChangeLista(lista)
                        }
                        .padding(18.5.dp, 7.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = it.nome_genero,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.intermedium)),
                            fontWeight = FontWeight(600),
                            color = Color.Black
                        )
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.icon_close),
                        contentDescription = "",
                        tint = Color.Black,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
        }
    }
}