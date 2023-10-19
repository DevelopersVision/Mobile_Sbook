package br.senai.sp.jandira.s_book.components.profile.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.profile.components.CardProfile
import br.senai.sp.jandira.s_book.components.profile.components.RotasProfile
import br.senai.sp.jandira.s_book.components.universal.HeaderProfile

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(35.dp)
    ) {
        HeaderProfile(
            onclick = { navController.popBackStack()}
        )
        CardProfile( navController = navController, context)
        RotasProfile(navController = navController)
    }
}