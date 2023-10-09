package br.senai.sp.jandira.s_book.components.filterGenero.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.filterGenero.components.CheckGenero
import br.senai.sp.jandira.s_book.components.universal.HeaderFilter
import br.senai.sp.jandira.s_book.components.universal.SearchFilter

@Preview(showSystemUi = true)
@Composable
fun FilterGeneroScreen(
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
                text = "Gênero",
                onclick = {
                    navController.navigate("filters")
                }
            )
            SearchFilter(
                label = "Gênero" ,
                valor = "",
                aoMudar = {}
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(9) {
                    CheckGenero(
                        text = "Terror"
                    )
                    CheckGenero(
                        text = "Romance"
                    )
                    CheckGenero(
                        text = "Ação"
                    )
                }
            }
        }
    }
}