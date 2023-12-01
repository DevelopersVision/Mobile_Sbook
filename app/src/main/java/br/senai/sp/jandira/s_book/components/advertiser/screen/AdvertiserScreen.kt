package br.senai.sp.jandira.s_book.components.advertiser.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.advertiser.components.HeaderBoxAdvertiser
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2

@Composable
fun AdvertiserScreen(
    navController: NavController,
    viewModelV2: AnuncioViewModelV2
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeaderBoxAdvertiser(navController = navController)
    }
}