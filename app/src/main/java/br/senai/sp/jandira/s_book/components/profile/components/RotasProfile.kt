package br.senai.sp.jandira.s_book.components.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R

@Composable
fun RotasProfile(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        ButtonRota(icon = R.drawable.books, text = "Meus anúncios"){}
        ButtonRota(icon = R.drawable.heart, text = "Favoritos"){ navController.navigate("favorite")}
        ButtonRota(icon = R.drawable.user_profile, text = "Minhas informações") {}
        ButtonRota(icon = R.drawable.power, text = "Sair"){}
    }
}