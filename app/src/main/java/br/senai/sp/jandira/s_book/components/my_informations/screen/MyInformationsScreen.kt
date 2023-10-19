package br.senai.sp.jandira.s_book.components.my_informations.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.s_book.components.my_informations.components.PhotoMyInformations
import br.senai.sp.jandira.s_book.components.my_informations.components.UserInformations
import br.senai.sp.jandira.s_book.components.universal.HeaderProfile

@Composable
fun MyInformationsScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp, 16.dp)
    ) {
        HeaderProfile {}
        PhotoMyInformations()
        UserInformations()
    }
}

@Preview
@Composable
fun Preview() {
    val navController = rememberNavController()

    MyInformationsScreen(navController = navController)

}