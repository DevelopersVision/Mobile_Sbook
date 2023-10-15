package br.senai.sp.jandira.s_book.components.filters.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R


@Composable
fun Form(
    navController: NavController
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
    ) {
        ComponentsFiltro(
            text = "Gênero",
            icon = painterResource(
                id = R.drawable.book
            ),
            onclick = {
                navController.navigate("filterGenero")
            }
        )
        ComponentsFiltro(
            text = "Localização",
            icon = painterResource(
                id = R.drawable.localizar
            ),
            onclick = {
                navController.navigate("filter_localizacao")
            }
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
            ),
            onclick = {
                navController.navigate("filter_ano")
            }
        )
        ComponentsFiltro(
            text = "Idioma",
            icon = painterResource(
                id = R.drawable.idioma
            ),
            onclick = {
                navController.navigate("filter_idioma")
            }
        )
    }
}