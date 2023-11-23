package br.senai.sp.jandira.s_book.navigation_home_bar

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.s_book.components.chats.screen.ChatScreen
import br.senai.sp.jandira.s_book.components.feed.screen.FeedScreen
import br.senai.sp.jandira.s_book.components.login.screen.LoginScreen
import br.senai.sp.jandira.s_book.components.perfil.components.converterData
import br.senai.sp.jandira.s_book.components.pesquisar.screen.SearchScreen
import br.senai.sp.jandira.s_book.components.profile.screens.ProfileScreen
import br.senai.sp.jandira.s_book.model.chat.ChatClient
import br.senai.sp.jandira.s_book.model.chat.view_model.ChatViewModel
import br.senai.sp.jandira.s_book.model.chat.view_model.viewModelId
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.service.RetrofitHelper.HttpClientProvider.client
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import br.senai.sp.jandira.s_book.view_model.RotaViewModel
import io.socket.client.Socket


@Composable
fun ButtonNavGraph(
    navController: NavHostController,
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    context: Context,
    anuncioViewMODEL: AnuncioViewModel,
    chatViewModel: ChatViewModel,
    viewModelId: viewModelId,
    rotaViewModel: RotaViewModel
) {
    val context = LocalContext.current

    val dadaUser = UserRepository(context).findUsers()

    var array = User()

    var data = ""


    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Feed.route,
    ){
        composable(route = BottomBarScreen.Feed.route){
            rotaViewModel.navRotasController = navController

            FeedScreen(navController = navController, lifecycleScope = lifecycleScope ,navRotasController = navRotasController, viewModelQueVaiPassarOsDados = anuncioViewMODEL, viewModelId = viewModelId)
        }

        composable(route = BottomBarScreen.Pesquisar.route){
            SearchScreen(
                navController = navController,
                lifecycleScope = lifecycleScope,
                viewModelQueVaiPassarOsDados = anuncioViewMODEL
            )
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
                ProfileScreen(navRotasController)
            }else{
                LoginScreen(navController = navRotasController, lifecycleScope = lifecycleScope)
            }
        }

        composable("doacoes"){

        }
    }
}