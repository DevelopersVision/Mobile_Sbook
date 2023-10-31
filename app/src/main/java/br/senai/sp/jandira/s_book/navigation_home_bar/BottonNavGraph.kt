package br.senai.sp.jandira.s_book.navigation_home_bar

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.s_book.components.chats.screen.ChatScreen
import br.senai.sp.jandira.s_book.components.feed.screen.FeedScreen
import br.senai.sp.jandira.s_book.components.pesquisar.screen.SearchScreen
import br.senai.sp.jandira.s_book.components.profile.screens.ProfileScreen
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel

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
            FeedScreen(navController = navController, lifecycleScope = lifecycleScope ,navRotasController = navRotasController, viewModelQueVaiPassarOsDados = anuncioViewMODEL)
        }

        composable(route = BottomBarScreen.Pesquisar.route){
            SearchScreen(
                navController = navController,
                lifecycleScope = lifecycleScope,
                viewModelQueVaiPassarOsDados = anuncioViewMODEL
            )
        }
        composable(route = BottomBarScreen.Chat.route){
            ChatScreen(navRotasController)
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(navRotasController)
        }
    }
}