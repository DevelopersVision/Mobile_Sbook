package br.senai.sp.jandira.s_book.components.create_account_endereco.screen

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.s_book.components.create_account_endereco.components.Form
import br.senai.sp.jandira.s_book.components.create_account.components.Header
import br.senai.sp.jandira.s_book.components.create_account.components.TextContScreen
import br.senai.sp.jandira.s_book.components.universal.GoogleScreen
import br.senai.sp.jandira.s_book.components.universal.TextContinueScreen
import br.senai.sp.jandira.s_book.view_model.CreateAccountView
import br.senai.sp.jandira.s_book.view_model.UserCategoryViewModel


@Composable
fun CreateAccountEndereco (
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: CreateAccountView,
    viewModelUserCategory: UserCategoryViewModel
) {

    androidx.compose.material3.Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    ScrollState(115)
                ),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,


            ) {

            Header()

//            Spacer(modifier = Modifier.height(63.dp))
            Form(navController, lifecycleScope, viewModel, viewModelUserCategory)

//            Spacer(modifier = Modifier.height(53.dp))
            TextContinueScreen()

//            Spacer(modifier = Modifier.height(12.dp))
            GoogleScreen()

//            Spacer(modifier = Modifier.height(6.dp))
            TextContScreen(
                onClick = {
                    navController.navigate("login")
                }
            )
        }
    }
}
