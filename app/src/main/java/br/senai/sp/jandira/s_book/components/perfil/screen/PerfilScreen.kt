package br.senai.sp.jandira.s_book.components.perfil.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.components.perfil.components.ButtonExit
import br.senai.sp.jandira.s_book.components.perfil.components.Dados
import br.senai.sp.jandira.s_book.components.perfil.components.Header

@Preview(showSystemUi = true)
@Composable
fun PerfilScreen(){

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header()
            Dados()
        }
    }
}