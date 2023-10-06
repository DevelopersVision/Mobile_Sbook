package br.senai.sp.jandira.s_book.components.Filters.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.Filters.components.CheckFilter
import br.senai.sp.jandira.s_book.components.Filters.components.ComponentsFiltro
import br.senai.sp.jandira.s_book.components.Filters.components.Form
import br.senai.sp.jandira.s_book.components.universal.HeaderFilter

@Preview(showSystemUi = true)
@Composable
fun FiltersScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            HeaderFilter(text = "Filtros")
            Form()
        }
    }
}
