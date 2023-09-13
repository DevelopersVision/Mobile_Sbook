package br.senai.sp.jandira.s_book.Components.CreateContScreen.Screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.s_book.Components.CreateContScreen.Components.TextContScreen
import br.senai.sp.jandira.s_book.Components.UniversalScreen.GoogleScreen
import br.senai.sp.jandira.s_book.Components.UniversalScreen.TextContinueScreen

@Preview(showSystemUi = true)
@Composable
fun CreateContScreen(){

    Surface (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    ScrollState(115)
                )
            ,
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,




            ) {

            br.senai.sp.jandira.s_book.Components.CreateContScreen.Components.Header()

//            Spacer(modifier = Modifier.height(63.dp))
            br.senai.sp.jandira.s_book.Components.CreateContScreen.Components.Form()

//            Spacer(modifier = Modifier.height(53.dp))
            TextContinueScreen()

//            Spacer(modifier = Modifier.height(12.dp))
            GoogleScreen()

//            Spacer(modifier = Modifier.height(6.dp))
            TextContScreen()
        }


    }
}

