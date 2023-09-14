package br.senai.sp.jandira.s_book.navigation_home_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route : String,
    val  title : String,
    val icon : ImageVector,
){
    object  Feed : BottomBarScreen(
        route = "feed",
        title = "Feed",
        icon = Icons.Default.Home
    )
    object  Favorite : BottomBarScreen(
        route = "favorite",
        title = "Favoritos",
        icon = Icons.Default.CheckCircle
    )
    object  Anuncio : BottomBarScreen(
        route = "address",
        title = "AddressScreen",
        icon = Icons.Default.DateRange
    )
    object  Profile : BottomBarScreen(
        route = "address",
        title = "AddressScreen",
        icon = Icons.Default.AccountCircle
    )

}
