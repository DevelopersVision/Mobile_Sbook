package br.senai.sp.jandira.s_book.navigation_home_bar

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.s_book.components.announce.screens.AnnounceScreen
import br.senai.sp.jandira.s_book.components.chats.screen.ChatScreen
import br.senai.sp.jandira.s_book.components.donations.screen.DonationsScreen
import br.senai.sp.jandira.s_book.components.feed.screen.FeedScreen
import br.senai.sp.jandira.s_book.components.login.screen.LoginScreen
import br.senai.sp.jandira.s_book.components.meu_anuncio.screen.MyAnnounceScreen
import br.senai.sp.jandira.s_book.components.pesquisar.screen.SearchScreen
import br.senai.sp.jandira.s_book.components.profile.screens.ProfileScreen
import br.senai.sp.jandira.s_book.model.chat.ChatClient
import br.senai.sp.jandira.s_book.model.chat.view_model.ChatViewModel
import br.senai.sp.jandira.s_book.model.chat.view_model.viewModelId
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import br.senai.sp.jandira.s_book.view_model.SharedViewModel


@Composable
fun ButtonNavGraph(
    navController: NavHostController,
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    context: Context,
    anuncioViewMODEL: AnuncioViewModel,
    chatViewModel: ChatViewModel,
    viewModelId: viewModelId,
    sharedViewModel: SharedViewModel
) {
    val context = LocalContext.current

    val dadaUser = UserRepository(context).findUsers()

    var array = User()

    var data = ""

    val viewModelAnuncioV2 = viewModel<AnuncioViewModelV2>()


    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Feed.route,
    ){
        composable(route = BottomBarScreen.Feed.route){

            FeedScreen(navController = navController, lifecycleScope = lifecycleScope ,navRotasController = navRotasController, viewModelQueVaiPassarOsDados = anuncioViewMODEL, viewModelId = viewModelId)
        }

        composable("my_announces"){
            br.senai.sp.jandira.s_book.components.my_announces.screen.MyAnnounceScreen(
                navRotasController = navRotasController,
                lifecycleScope = lifecycleScope,
                navController,
                viewModelAnuncioV2
            )
        }

        composable(route = BottomBarScreen.Pesquisar.route){
            SearchScreen(
                navController = navController,
                lifecycleScope = lifecycleScope,
                viewModelQueVaiPassarOsDados = anuncioViewMODEL
            )
        }

        composable("donations") {
            DonationsScreen(navController = navController, sharedViewModel, viewModelAnuncioV2)
        }

        composable("announce") {
            AnnounceScreen(lifecycleScope = lifecycleScope, viewModel = viewModelAnuncioV2, navController = navController)
        }

        composable("myAnnounce") {
            MyAnnounceScreen(lifecycleScope = lifecycleScope, viewModel = viewModelAnuncioV2, navController = navController)
        }

        composable(route = BottomBarScreen.Chat.route){


            if(dadaUser.isNotEmpty()){
                array = dadaUser[0]


                data = array.id.toString()

                Log.e("eu mandei", "id: ${data}", )


                val client = ChatClient()
                client.connect(data.toInt())
                val socket = client.getSocket()

                ChatScreen( navRotasController , socket = socket, idUsuario = data.toInt(), chatViewModel = chatViewModel, client )
            }else{
                LoginScreen(navController = navRotasController, lifecycleScope = lifecycleScope)
            }
        }

        composable(route = BottomBarScreen.Profile.route){
            if(dadaUser.isNotEmpty()){
                ProfileScreen(navRotasController = navRotasController, navController = navController)
            }else{
                LoginScreen(navController = navRotasController, lifecycleScope = lifecycleScope)
            }
        }
    }
}