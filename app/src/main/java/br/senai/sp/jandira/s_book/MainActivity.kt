package br.senai.sp.jandira.s_book

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.s_book.components.edit_user.screen.EditUser
import br.senai.sp.jandira.s_book.components.filters.screen.FiltersScreen
import br.senai.sp.jandira.s_book.components.announceDetail.screen.AnnouceDetail
import br.senai.sp.jandira.s_book.components.category.screen.CategoryScreen
import br.senai.sp.jandira.s_book.components.cep.screen.CepScreen
import br.senai.sp.jandira.s_book.components.conversation_chat.screen.ConversationChatScreen
import br.senai.sp.jandira.s_book.components.create_account.screen.CreateContScreen
import br.senai.sp.jandira.s_book.components.create_account_endereco.screen.CreateAccountEndereco
import br.senai.sp.jandira.s_book.components.favorite.screen.FavoritoScreen
import br.senai.sp.jandira.s_book.components.fifth_create_announce.screen.FifthCreateAnnounceScreen
import br.senai.sp.jandira.s_book.components.filterGenero.screen.FilterGeneroScreen
import br.senai.sp.jandira.s_book.components.filter_ano.screen.FilterAnoScreen
import br.senai.sp.jandira.s_book.components.filter_idioma.screen.FilterIdiomaScreen
import br.senai.sp.jandira.s_book.components.filter_localizacao.screen.FilterLocalizacaoScreen
import br.senai.sp.jandira.s_book.components.filter_localizacao_cidades.screen.FilterLocalizacaoCidadeScreen
import br.senai.sp.jandira.s_book.components.first_create_announce.screen.FirstCreateAnnounceScreen
//import br.senai.sp.jandira.s_book.components.first_create_announce.screen.FirstCreateAnnounceScreen
import br.senai.sp.jandira.s_book.components.forgot_password.screen.ForgotPasswordScreen
import br.senai.sp.jandira.s_book.components.fourth_create_announce.screen.FourthCreateAnnounceScreen
import br.senai.sp.jandira.s_book.components.insert_code.screen.InsertCode
import br.senai.sp.jandira.s_book.components.login.screen.LoginScreen
import br.senai.sp.jandira.s_book.components.my_announces.screen.MyAnnounceScreen
import br.senai.sp.jandira.s_book.components.my_informations.screen.MyInformationsScreen
import br.senai.sp.jandira.s_book.components.profile.screens.ProfileScreen
import br.senai.sp.jandira.s_book.components.rediscover_password.screen.RediscoverPasswordScreen
import br.senai.sp.jandira.s_book.components.second_create_announce.screen.SecondCreateAnnounceScreen
import br.senai.sp.jandira.s_book.components.seventh_create_announce.screen.SeventhCreateAnnounceScreen
import br.senai.sp.jandira.s_book.components.sixth_create_announce.screen.SixthCreateAnnounceScreen
import br.senai.sp.jandira.s_book.components.tela_generica.screen.GenericScreen
import br.senai.sp.jandira.s_book.components.third_create_announce.screen.ThirdCreateAnnounceScreen
import br.senai.sp.jandira.s_book.model.chat.ChatClient
import br.senai.sp.jandira.s_book.model.chat.view_model.ChatViewModel
import br.senai.sp.jandira.s_book.models_private.User
//import br.senai.sp.jandira.s_book.components.second_create_announce.screen.SecondCreateAnnounceScreen
import br.senai.sp.jandira.s_book.view_model.CreateAccountView
import br.senai.sp.jandira.s_book.view_model.ResetPasswordView
import br.senai.sp.jandira.s_book.view_model.UserCategoryViewModel
import br.senai.sp.jandira.s_book.navigation_home_bar.MainScreen
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository

import br.senai.sp.jandira.s_book.ui.theme.SBOOKTheme
import br.senai.sp.jandira.s_book.view_model.AnnouncePhotosViewModel
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import br.senai.sp.jandira.s_book.view_model.ViewModelDosGenerosSelecionados
import br.senai.sp.jandira.s_book.view_model.ViweModelDosFiltros

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SBOOKTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = Color.White
                ) {

                    val navController = rememberNavController()

                    val viewModelCreateAccount = viewModel<CreateAccountView>()
                    val viewModelResetPassword = viewModel<ResetPasswordView>()
                    val viewModelUserCategory = viewModel<UserCategoryViewModel>()
                    val viewModelAnuncio = viewModel<AnuncioViewModel>()
                    val localStorage: Storage = Storage()
                    val chatViewModel = viewModel<ChatViewModel>()
                    val viewModelFilters = viewModel<ViweModelDosFiltros>()
                    val viewModelDasImagens = viewModel<AnnouncePhotosViewModel>()
                    val viewModelGeneros = viewModel<ViewModelDosGenerosSelecionados>()

                    val client = ChatClient()

                    NavHost(
                        navController = navController, startDestination = "navigation_home_bar"
                    ){
                        composable("navigation_home_bar") {
                            MainScreen(navController, lifecycleScope, anuncioViewMODEL = viewModelAnuncio, chatViewModel)
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
                            FavoritoScreen(navController = navController, lifecycleScope = lifecycleScope ,navRotasController = navController, viewModelQueVaiPassarOsDados = viewModelAnuncio)
                        }

                        composable("cep"){
                            CepScreen(navController = navController, viewModel = viewModelCreateAccount)
                        }

                        composable("profile"){
                            ProfileScreen(navController = navController)
                        }

                        composable("my_informations"){
                            MyInformationsScreen(navController = navController)
                        }

                        composable("annouceDetail"){
                            AnnouceDetail(navController, viewModelAnuncio, lifecycleScope = lifecycleScope)
                        }

                        composable("editUser"){
                            EditUser(navController)
                        }

                        composable("filters"){
                            FiltersScreen(navController = navController, viewModelFilters)
                        }

                        composable("my_announces"){
                            MyAnnounceScreen(navRotasController = navController, lifecycleScope = lifecycleScope)
                        }

                        composable("filterGenero"){
                            FilterGeneroScreen(navController, viewModel = viewModelFilters)
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

                        composable("primeiro_anunciar"){
                            FirstCreateAnnounceScreen(navController, localStorage = localStorage)
                        }

                        composable("segundo_anunciar"){
                            SecondCreateAnnounceScreen(navController, localStorage)
                        }

                        composable("terceiro_anunciar"){
                            ThirdCreateAnnounceScreen(navController, localStorage, viewModelDasImagens)
                        }

                        composable("quarto_anunciar"){
                            FourthCreateAnnounceScreen(navController, localStorage, viewModelGeneros)
                        }

                        composable("quinto_anunciar"){
                            FifthCreateAnnounceScreen(navController, localStorage)
                        }

                        composable("sexto_anunciar"){
                            SixthCreateAnnounceScreen(navController, localStorage)
                        }

                        composable("setimo_anunciar"){
                            SeventhCreateAnnounceScreen(localStorage, viewModelDasImagens, viewModelGeneros)
                        }

                        composable("conversa_chat"){

                            val context = LocalContext.current

                            val dadaUser = UserRepository(context).findUsers()

                            var array = User()

                            var data = ""

                            if(dadaUser.isNotEmpty()){
                                array = dadaUser[0]


                                data = array.id.toString()
                            }

                            Log.e("eu mandei", "id: ${data}", )

                            val client = ChatClient()
                            client.connect(data.toInt())
                            val socket = client.getSocket()
                            ConversationChatScreen( navController,socket = socket, idUsuario = data.toInt(), chatViewModel = chatViewModel, client = client)
                        }
                        composable("tela_generica"){
                            GenericScreen(navController = navController, lifecycleScope = lifecycleScope ,navRotasController = navController, viewModelQueVaiPassarOsDados = viewModelAnuncio, viewModelQueVaiReceberOsgeneros = viewModelFilters)
                        }
                    }
                }
            }
        }
    }
}