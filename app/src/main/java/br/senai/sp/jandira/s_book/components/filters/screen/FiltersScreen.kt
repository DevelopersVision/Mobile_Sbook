package br.senai.sp.jandira.s_book.components.filters.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.filters.components.Form
import br.senai.sp.jandira.s_book.components.universal.HeaderFilter
import br.senai.sp.jandira.s_book.view_model.ViweModelDosFiltros


@Composable
fun FiltersScreen(
    navController: NavController,
    viewModelParaFiltragem: ViweModelDosFiltros
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeaderFilter(
            text = "Filtrar",
            onclick = {navController.navigate("navigation_home_bar")}
        )
        Form(navController = navController, viewModelParaFiltragem)
        
        
        Button(
            onClick = { navController.navigate("tela_generica") }
        ) {
            Text(text = "Filtrar")
        }
    }
}
