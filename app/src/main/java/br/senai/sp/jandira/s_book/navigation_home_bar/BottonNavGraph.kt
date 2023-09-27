package br.senai.sp.jandira.s_book.navigation_home_bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.senai.sp.jandira.s_book.components.address.screen.AddressScreen
import br.senai.sp.jandira.s_book.components.favorite.screen.FavoritoScreen
import br.senai.sp.jandira.s_book.components.feed.screen.FeedScreen
import br.senai.sp.jandira.s_book.components.login.screen.LoginScreen

@Composable
fun ButtonNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Feed.route,
    ){
        composable(route = BottomBarScreen.Feed.route){
<<<<<<< HEAD
            FeedScreen(navController)
=======
            FeedScreen(navController = navController)
>>>>>>> a0fcac8dfdfca7521cc19275d2420e11d281abaf
        }
        composable(route = BottomBarScreen.Anuncio.route){
            AddressScreen()
        }
        composable(route = BottomBarScreen.Favorite.route){
            FavoritoScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            AddressScreen()
        }
        composable(route = "login"){
            LoginScreen(navController = navController, lifecycleScope = null)
        }
    }
}