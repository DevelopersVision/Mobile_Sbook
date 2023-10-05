package br.senai.sp.jandira.s_book.components.category.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.s_book.components.category.components.Button
import br.senai.sp.jandira.s_book.components.category.components.Header
import br.senai.sp.jandira.s_book.components.category.components.ListCategory
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.view_model.UserCategoryViewModel
import br.senai.sp.jandira.s_book.repository.UserCategoryRepository
import kotlinx.coroutines.launch

@Composable
fun CategoryScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope?,
    viewModel: UserCategoryViewModel
){

    Surface (
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Header( navController = navController)
            ListCategory(navController, lifecycleScope, viewModel)
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                text = "Seguir  e continuar",
                onClick = {
                    val id_usuario = viewModel.id_usuario

                    Log.e("idUsuario", "CategoryScreen: $id_usuario", )
                    val generos_preferidos = viewModel.generos_preferidos
                    if (generos_preferidos != null) {
                        if (lifecycleScope != null) {
                            mandarProBanco(id_usuario, generos_preferidos, lifecycleScope)
                        }
                    }
                    navController.navigate("login")
                }
            )
        }
    }
}

fun mandarProBanco(id_usuario: Int, generos_preferidos: List<Genero>, lifecycleScope: LifecycleCoroutineScope) {

    val userCategoryRepository = UserCategoryRepository()
    lifecycleScope.launch {
        Log.e("ID do Usuário", "${id_usuario}")
        Log.e("Generos Preferidos", "${generos_preferidos}")
        val response = userCategoryRepository.usuarioCategoria(id_usuario, generos_preferidos)

        Log.e("Response", "mandarProBanco: $response", )
        Log.e("ResponseBidy", "mandarProBanco: ${response.body()}", )


        if (response.isSuccessful) {

            Log.e("registrar as categorias do usuario", "userCategory: ${response.body()}")
        } else {
            val erroBody = response.errorBody()?.string()

            Log.e("registrar as categorias do usuario", "userCategory: $erroBody")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun preview(){
    val navController = rememberNavController()
    val viewModelUserCategory = viewModel<UserCategoryViewModel>()

    CategoryScreen(navController = navController, lifecycleScope = null, viewModel = viewModelUserCategory)
}