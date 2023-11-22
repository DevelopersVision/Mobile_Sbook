package br.senai.sp.jandira.s_book.components.donations.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.donations.components.HeaderDonations

@Preview
@Composable
fun DonationsScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HeaderDonations {
            navController.navigate("navigation_home_bar")
        }
    }
}