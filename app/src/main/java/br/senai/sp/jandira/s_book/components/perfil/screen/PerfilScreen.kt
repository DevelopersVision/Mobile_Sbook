package br.senai.sp.jandira.s_book.components.perfil.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.perfil.components.Dados
import br.senai.sp.jandira.s_book.components.perfil.components.Header

@Composable
fun PerfilScreen(
    navController: NavController
){

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header(navController = navController)
            Dados(navController)
        }
    }
}