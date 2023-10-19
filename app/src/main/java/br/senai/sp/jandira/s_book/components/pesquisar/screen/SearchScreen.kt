package br.senai.sp.jandira.s_book.components.pesquisar.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.universal.HeaderFilter
import br.senai.sp.jandira.s_book.components.universal.SearchFilter

@Composable
fun SearchScreen(
    navController: NavController
){

    var pesquisar by remember {
        mutableStateOf(value = "")
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HeaderFilter(
                text = "Procurar"
            ) {
                navController.navigate("feed")
            }
            SearchFilter(
                label = "Digite nome, gênero ou ..." ,
                valor =  pesquisar ,
                aoMudar = {
                    pesquisar = it
                }
            )
        }
    }
}