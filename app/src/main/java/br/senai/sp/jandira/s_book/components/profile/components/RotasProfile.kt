package br.senai.sp.jandira.s_book.components.profile.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.perfil.components.converterData
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository

@Composable
fun RotasProfile(
    navController: NavController
) {
    val context = LocalContext.current

    val dadaUser = UserRepository(context).findUsers()

    var array = User()

    var data = ""

    if(dadaUser.isNotEmpty()){
        array = dadaUser[0]

        data = converterData(array.dataNascimento)
    }
    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(2.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(22.dp)
    ) {
        ButtonRota(icon = R.drawable.books, text = "Meus anúncios"){navController.navigate("my_announces")}
        ButtonRota(icon = R.drawable.heart, text = "Favoritos"){ navController.navigate("favorite")}
        ButtonRota(icon = R.drawable.user_profile, text = "Minhas informações") {navController.navigate("my_informations")}
        ButtonRota(icon = R.drawable.power, text = "Sair"){
            navController.navigate("navigation_home_bar")
            UserRepository(context).deleteUser(array.id.toInt())
        }
    }
}