package br.senai.sp.jandira.s_book.components.filter_localizacao_cidades.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.universal.HeaderFilter
import br.senai.sp.jandira.s_book.components.universal.ListItems
import br.senai.sp.jandira.s_book.components.universal.SearchFilter

@Preview(showSystemUi = true)
@Composable
fun FilterLocalizacaoCidadeScreen (
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
                text = "Localização",
                onclick = {
                   navController.navigate("filter_localizacao")
                }
            )
            SearchFilter(
                label = "Estados" ,
                valor = "",
                aoMudar = {}
            )
            ListItems(
                text = "Russia"
            )
        }
    }
 }