package br.senai.sp.jandira.s_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.s_book.components.EditUser.screen.EditUser
import br.senai.sp.jandira.s_book.components.filters.screen.FiltersScreen
import br.senai.sp.jandira.s_book.components.announceDetail.screen.AnnouceDetail
import br.senai.sp.jandira.s_book.components.category.screen.CategoryScreen
import br.senai.sp.jandira.s_book.components.cep.screen.CepScreen
import br.senai.sp.jandira.s_book.components.create_account.screen.CreateContScreen
import br.senai.sp.jandira.s_book.components.create_account_endereco.screen.CreateAccountEndereco
import br.senai.sp.jandira.s_book.components.favorite.screen.FavoritoScreen
import br.senai.sp.jandira.s_book.components.filterGenero.screen.FilterGeneroScreen
import br.senai.sp.jandira.s_book.components.filter_ano.screen.FilterAnoScreen
import br.senai.sp.jandira.s_book.components.filter_idioma.screen.FilterIdiomaScreen
import br.senai.sp.jandira.s_book.components.filter_localizacao.screen.FilterLocalizacaoScreen
import br.senai.sp.jandira.s_book.components.filter_localizacao_cidades.screen.FilterLocalizacaoCidadeScreen
import br.senai.sp.jandira.s_book.components.forgot_password.screen.ForgotPasswordScreen
import br.senai.sp.jandira.s_book.components.insert_code.screen.InsertCode
import br.senai.sp.jandira.s_book.components.login.screen.LoginScreen
import br.senai.sp.jandira.s_book.components.my_announces.screen.MyAnnounceScreen
import br.senai.sp.jandira.s_book.components.perfil.screen.PerfilScreen
import br.senai.sp.jandira.s_book.components.profile.screens.ProfileScreen
import br.senai.sp.jandira.s_book.components.rediscover_password.screen.RediscoverPasswordScreen
import br.senai.sp.jandira.s_book.view_model.CreateAccountView
import br.senai.sp.jandira.s_book.view_model.ResetPasswordView
import br.senai.sp.jandira.s_book.view_model.UserCategoryViewModel
import br.senai.sp.jandira.s_book.navigation_home_bar.MainScreen

import br.senai.sp.jandira.s_book.ui.theme.SBOOKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SBOOKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

                    val navController = rememberNavController()

                    val viewModelCreateAccount = viewModel<CreateAccountView>()
                    val viewModelResetPassword = viewModel<ResetPasswordView>()
                    val viewModelUserCategory = viewModel<UserCategoryViewModel>()

                    NavHost(
                        navController = navController, startDestination = "navigation_home_bar"
                    ){
                        composable("navigation_home_bar") {
                            MainScreen(navController, lifecycleScope)
                        }
                        composable("login") {
                            LoginScreen(navController = navController, lifecycleScope = lifecycleScope)
                        }

                        composable("create_account") {
                            CreateContScreen(navController = navController, viewModel = viewModelCreateAccount)
                        }

                        composable("create_account_endereco") {
                            CreateAccountEndereco(navController = navController, lifecycleScope = lifecycleScope, viewModel = viewModelCreateAccount, viewModelUserCategory)
                        }

                        composable("forgot_password") {
                            ForgotPasswordScreen(navController = navController, lifecycleScope = lifecycleScope, viewModel = viewModelResetPassword)
                        }

                        composable("insert_code") {
                            InsertCode(navController = navController, lifecycleScope = lifecycleScope, viewModel = viewModelResetPassword)
                        }

                        composable("change_password") {
                            RediscoverPasswordScreen(navController = navController, lifecycleScope = lifecycleScope, viewModel = viewModelResetPassword)
                        }

                        composable("category"){
                            CategoryScreen(navController = navController, lifecycleScope = lifecycleScope, viewModel = viewModelUserCategory)
                        }

                        composable("favorite"){
                            FavoritoScreen(navController = navController, lifecycleScope = lifecycleScope ,navRotasController = navController)
                        }

                        composable("cep"){
                            CepScreen(navController = navController, viewModel = viewModelCreateAccount)
                        }

                        composable("perfil"){
                            PerfilScreen(navController)
                        }

                        composable("profile"){
                            ProfileScreen()
                        }

                        composable("annouceDetail"){
                            AnnouceDetail(navController)
                        }

                        composable("editUser"){
                            EditUser(navController)
                        }

                        composable("filters"){
                            FiltersScreen(navController = navController)
                        }

                        composable("my_announce"){
                            MyAnnounceScreen(navRotasController = navController, lifecycleScope = lifecycleScope)
                        }

                        composable("filterGenero"){
                            FilterGeneroScreen(navController)
                        }

                        composable("filter_localizacao"){
                            FilterLocalizacaoScreen(navController)
                        }

                        composable("filter_localizacao_cidades"){
                            FilterLocalizacaoCidadeScreen(navController)
                        }

                        composable("filter_idioma"){
                            FilterIdiomaScreen(navController)
                        }

                        composable("filter_ano"){
                            FilterAnoScreen(navController)
                        }
                    }
                }
            }
        }
    }
}