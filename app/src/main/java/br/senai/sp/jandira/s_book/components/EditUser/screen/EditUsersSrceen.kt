package br.senai.sp.jandira.s_book.components.EditUser.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.EditUser.components.Form
import br.senai.sp.jandira.s_book.components.EditUser.components.Header
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen

//@Preview(showSystemUi = true)
@Composable
fun EditUser(
    navController: NavController
){

    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(250.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Column(
                modifier = Modifier
                    .height(500.dp),
                verticalArrangement = Arrangement.spacedBy(34.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Header(navController)
                Form(context = context)
            }
            DefaultButtonScreen(
                text = "Continuar"
            ) {}
        }

    }
}

