package br.senai.sp.jandira.s_book.components.cep.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.cep.components.Form
import br.senai.sp.jandira.s_book.components.cep.components.Header
import br.senai.sp.jandira.s_book.components.create_account.components.Button
import br.senai.sp.jandira.s_book.functions.pegarCEP
import br.senai.sp.jandira.s_book.view_model.CreateAccountView

@Composable
fun CepScreen(
    navController: NavController,
    viewModel: CreateAccountView
){
    var cepState by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

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
            Form(cepState, onCepChange = {cepState = it})
            Spacer(modifier = Modifier.height(48.dp))
            Button(text = "Continuar", onClick = {
                pegarCEP(cepState, navController, "create_account_endereco", viewModel, context = context)
            })
        }
    }//220
}
