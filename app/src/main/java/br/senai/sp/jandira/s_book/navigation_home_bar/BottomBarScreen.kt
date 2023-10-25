package br.senai.sp.jandira.s_book.navigation_home_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route : String,
    val  title : String,
    var icon : ImageVector,
){
    object  Feed : BottomBarScreen(
        route = "feed",
        title = "Feed",
        icon = Icons.Default.Home
    )
    object  Chat : BottomBarScreen(
        route = "chats",
        title = "Chats",
        icon = Icons.Default.Chat
    )
    object  Pesquisar : BottomBarScreen(
        route = "pesquisar",
        title = "Pesquisar",
        icon = Icons.Default.Search
    )

    object  Profile : BottomBarScreen(
        route = "profile",
        title = "perfil",
        icon = Icons.Default.AccountCircle
    )

}
