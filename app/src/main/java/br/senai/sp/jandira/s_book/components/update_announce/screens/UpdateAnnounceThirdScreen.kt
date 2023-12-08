package br.senai.sp.jandira.s_book.components.update_announce.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.update_announce.components.HeaderUpdateAnnounce

@Composable
fun UpdateAnnounceThirdScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderUpdateAnnounce {
            navController.popBackStack()
        }
    }
}