package br.senai.sp.jandira.s_book.components.filters.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.R

@Preview(showSystemUi = true)
@Composable
fun Form(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(410.dp)
    ) {
        ComponentsFiltro(
            text = "Gênero",
            icon = painterResource(
                id = R.drawable.book
            )
        )
        ComponentsFiltro(
            text = "Localização",
            icon = painterResource(
                id = R.drawable.localizar
            )
        )
        CheckFilter(
            text = "Novo",
            icon = painterResource(
                id = R.drawable.novo
            )
        )
        CheckFilter(
            text = "Semi novo",
            icon = painterResource(
                id = R.drawable.seminovo
            )
        )
        CheckFilter(
            text = "Usado",
            icon = painterResource(
                id = R.drawable.usado
            )
        )
        ComponentsFiltro(
            text = "Ano de publicação",
            icon = painterResource(
                id = R.drawable.calendario
            )
        )
        ComponentsFiltro(
            text = "Idioma",
            icon = painterResource(
                id = R.drawable.idioma
            )
        )
    }
}