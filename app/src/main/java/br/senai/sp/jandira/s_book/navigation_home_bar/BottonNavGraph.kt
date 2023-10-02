package br.senai.sp.jandira.s_book.navigation_home_bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.s_book.components.favorite.screen.FavoritoScreen
import br.senai.sp.jandira.s_book.components.feed.screen.FeedScreen
import br.senai.sp.jandira.s_book.components.login.screen.LoginScreen

@Composable
fun ButtonNavGraph(
    navController: NavHostController,
    navRotasController: NavController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Feed.route,
    ){
        composable(route = BottomBarScreen.Feed.route){
            FeedScreen(navController = navController, navRotasController)
        }
        composable(route = BottomBarScreen.Anuncio.route){

        }
        composable(route = BottomBarScreen.Favorite.route){
            FavoritoScreen(
                navController = navController, navRotasController
            )
        }
        composable(route = BottomBarScreen.Profile.route){

        }
    }
}