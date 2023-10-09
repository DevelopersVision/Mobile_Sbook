package br.senai.sp.jandira.s_book.components.filter_idioma.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.universal.HeaderFilter
import br.senai.sp.jandira.s_book.components.universal.ListItems
import br.senai.sp.jandira.s_book.components.universal.SearchFilter

@Composable
fun FilterIdiomaScreen(
    navController: NavController
){
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HeaderFilter(
                text = "Idioma",
                onclick = {
                    navController.navigate("filters")
                }
            )
            SearchFilter(
                label = "Digite um idioma" ,
                valor = "",
                aoMudar = {}
            )
            ListItems(
                text = "Chinês"
            )
        }
    }
}