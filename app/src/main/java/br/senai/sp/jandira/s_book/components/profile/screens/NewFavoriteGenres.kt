package br.senai.sp.jandira.s_book.components.profile.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.profile.components.ButtomConfirmList
import br.senai.sp.jandira.s_book.components.profile.components.HeaderNewGenres
import br.senai.sp.jandira.s_book.components.profile.components.ListGenres
import br.senai.sp.jandira.s_book.components.universal.HeaderProfile
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.repository.UserCategoryRepository
import br.senai.sp.jandira.s_book.view_model.UserGenresViewModel
import kotlinx.coroutines.launch

@Composable
fun NewFavoriteGenres(
    navController: NavController,
    userGenresViewModel: UserGenresViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {
    val context = LocalContext.current

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HeaderNewGenres(onclick = {navController.navigate("editUser")}, text = "Escolha seus generos ")
        ListGenres(userGenresViewModel)
        ButtomConfirmList(onClick = {
            val id_usuario = userGenresViewModel.id_usuario
            val lista = userGenresViewModel.newList
            saveNewGenreList(id_usuario = id_usuario,lista, lifecycleScope = lifecycleScope,navController, context)
        }, text = "Confirmar")
    }
}

fun saveNewGenreList(id_usuario: Int, generos_preferidos: List<Genero>, lifecycleScope: LifecycleCoroutineScope, navController: NavController, context: Context) {

    val userCategoryRepository = UserCategoryRepository()
    lifecycleScope.launch {
        val response = userCategoryRepository.newFavoriteGenres(id_usuario, generos_preferidos)

        if (response.isSuccessful) {
            Log.e("registrar as categorias do usuario", "userCategory: ${response.body()}")

            navController.navigate("editUser")
        } else {
            val erroBody = response.errorBody()?.string()

            Toast.makeText(context, "Ocorreu algum erro em nosso servidor porFavor tente mais tarde", Toast.LENGTH_LONG).show()

            Log.e("registrar as categorias do usuario", "userCategory: $erroBody")
        }
    }
}