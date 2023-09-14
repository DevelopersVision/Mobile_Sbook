package br.senai.sp.jandira.s_book.components.login.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.s_book.components.login.components.Form
import br.senai.sp.jandira.s_book.components.login.components.Header
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.components.universal.GoogleScreen
import br.senai.sp.jandira.s_book.components.universal.TextContinueScreen
import br.senai.sp.jandira.s_book.components.universal.TextNotContScreen

@Preview(showSystemUi = true)
@Composable
fun LoginScreen(){

    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
            ,
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,



        ) {

            Header()

//            Spacer(modifier = Modifier.height(63.dp))
            Form()

            DefaultButtonScreen(text = "Entrar") {}

//            Spacer(modifier = Modifier.height(53.dp))
            TextContinueScreen()

//            Spacer(modifier = Modifier.height(12.dp))
            GoogleScreen()

//            Spacer(modifier = Modifier.height(1.dp))
            TextNotContScreen()
        }


    }

}