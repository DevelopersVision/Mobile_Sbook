package br.senai.sp.jandira.s_book.Components.CategoryScreen.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun CategoryList(){

    val itemList = listOf(List())

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ){
        items(itemList) { item ->
            List()
            List()
            List()
            List()
            List()
            List()
            List()
            List()
            List()
            List()
        }
    }
}

