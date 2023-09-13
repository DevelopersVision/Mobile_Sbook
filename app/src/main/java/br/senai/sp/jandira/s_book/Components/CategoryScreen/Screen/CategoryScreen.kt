package br.senai.sp.jandira.s_book.Components.CategoryScreen.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.Components.CategoryScreen.Components.Botton
import br.senai.sp.jandira.s_book.Components.CategoryScreen.Components.CategoryList
import br.senai.sp.jandira.s_book.Components.CategoryScreen.Components.Header

@Preview(showSystemUi = true)
@Composable
fun CategoryScreen(){
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Header()
            CategoryList()
            Spacer(modifier = Modifier.height(30.dp))
            Botton(
                text = "Seguir  e continuar"
            ) {
            }
        }
    }
}