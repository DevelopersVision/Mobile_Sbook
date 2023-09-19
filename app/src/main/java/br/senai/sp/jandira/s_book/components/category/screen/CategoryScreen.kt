package br.senai.sp.jandira.s_book.components.category.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.components.category.components.Button
import br.senai.sp.jandira.s_book.components.category.components.Header
import br.senai.sp.jandira.s_book.components.category.components.ListCategory

@Preview(showSystemUi = true)
@Composable
fun CategoryScreen(){
    Surface (
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Header()
            ListCategory()
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                text = "Seguir  e continuar"
            ) {}
        }
    }
}