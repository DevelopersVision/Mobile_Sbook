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
import br.senai.sp.jandira.s_book.components.category.screen.CategoryScreen
import br.senai.sp.jandira.s_book.components.create_account.screen.CreateContScreen
import br.senai.sp.jandira.s_book.components.create_account_endereco.screen.CreateAccountEndereco
import br.senai.sp.jandira.s_book.components.forgot_password.screen.ForgotPasswordScreen
import br.senai.sp.jandira.s_book.components.insert_code.screen.InsertCode
import br.senai.sp.jandira.s_book.components.login.screen.LoginScreen
import br.senai.sp.jandira.s_book.components.rediscover_password.screen.RediscoverPasswordScreen
import br.senai.sp.jandira.s_book.model.CreateAccountView
import br.senai.sp.jandira.s_book.model.ResetPasswordView
import br.senai.sp.jandira.s_book.model.UserCategoryViewModel
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
                            MainScreen(navController)
                        }
                        composable("login") {
                            LoginScreen(navController = navController, lifecycleScope = lifecycleScope)
                        }

                        composable("create_account") {
                            CreateContScreen(navController = navController, lifecycleScope = lifecycleScope, viewModel = viewModelCreateAccount)
                        }

                        composable("create_account_endereco") {
                            CreateAccountEndereco(navController = navController, lifecycleScope = lifecycleScope, viewModel = viewModelCreateAccount)
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

                        // A surface container using the 'background' color from the theme
                        //LoginScreen(lifecycleScope = lifecycleScope)
                        //CreateContScreen(navController = navController)
                        //CreateAccountEndereco()
                        //AddressScreen()
                        //RediscoverPasswordScreen()
                        //ThanksScreen()
                        //ForgotPasswordScreen()
                        //InsertCode()
                        //CategoryList()
                        //CategoryScreen()
                    }
                }
            }
        }
    }
}

