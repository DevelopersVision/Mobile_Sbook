package br.senai.sp.jandira.s_book.components.profile.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.profile.components.ButtomConfirmList
import br.senai.sp.jandira.s_book.components.profile.components.HeaderNewGenres
import br.senai.sp.jandira.s_book.components.profile.components.ListGenres
import br.senai.sp.jandira.s_book.components.universal.HeaderProfile

@Composable
fun NewFavoriteGenres(
    navController: NavController
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HeaderNewGenres(onclick = { /*TODO*/ }, text = "Escolha seus generos ")
        ListGenres()
        ButtomConfirmList(onClick = { /*TODO*/ }, text = "Confirmar")
    }
}