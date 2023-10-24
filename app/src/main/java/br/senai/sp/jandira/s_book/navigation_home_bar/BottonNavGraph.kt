package br.senai.sp.jandira.s_book.navigation_home_bar

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.s_book.components.chats.screen.ChatScreen
import br.senai.sp.jandira.s_book.components.favorite.screen.FavoritoScreen
import br.senai.sp.jandira.s_book.components.feed.screen.FeedScreen
import br.senai.sp.jandira.s_book.components.pesquisar.screen.SearchScreen
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import br.senai.sp.jandira.s_book.view_model.CoracaoViewModel

@Composable
fun ButtonNavGraph(
    navController: NavHostController,
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    context: Context,
    anuncioViewMODEL: AnuncioViewModel
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Feed.route,
    ){
        composable(route = BottomBarScreen.Feed.route){
            FeedScreen(navController = navController, lifecycleScope = lifecycleScope ,navRotasController = navRotasController, viewModelQueVaiPassarOsDados = anuncioViewMODEL, viewModel = CoracaoViewModel())
        }
        composable(route = BottomBarScreen.Favorite.route){
            val user = UserRepository(context).findUsers()

            if(user.isNotEmpty()){
                FavoritoScreen(
                    navController = navController, lifecycleScope = lifecycleScope,
                    navRotasController = navRotasController,
                    viewModelEssaTemQueSerAMesmaDoCard = CoracaoViewModel()
                )
            }else{
                navRotasController.navigate("login")
            }
        }
        composable(route = BottomBarScreen.Pesquisar.route){
            SearchScreen(
                navController = navController,
                lifecycleScope = lifecycleScope,
                viewModelQueVaiPassarOsDados = anuncioViewMODEL
            )
        }
        composable(route = BottomBarScreen.Profile.route){
            ChatScreen(navRotasController)
        }
    }
}