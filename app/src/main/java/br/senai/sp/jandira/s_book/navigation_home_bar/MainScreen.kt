package br.senai.sp.jandira.s_book.navigation_home_bar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navRotasController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    anuncioViewMODEL: AnuncioViewModel
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottonBar(navController = navController)}
    ) {
        ButtonNavGraph(
            navController = navController,
            navRotasController = navRotasController,
            lifecycleScope = lifecycleScope,
            context,
            anuncioViewMODEL
        )
    }
}

@Composable
fun BottonBar(navController: NavHostController) {

    val screen = listOf(
        BottomBarScreen.Feed,
        BottomBarScreen.Chat,
        BottomBarScreen.Pesquisar,
        BottomBarScreen.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentdestination = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier
            .height(60.dp)
    ){
        screen.forEach{screen ->
            AddItem(screen = screen, currentDestination = currentdestination, navController = navController)

        }
    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val currentRoute = currentDestination?.route
    val selected = currentRoute == screen.route

    BottomNavigationItem(
        modifier = Modifier
            .background(Color.White),
        label = {
            Text(
                text = screen.title,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(400),
                color = if(selected){
                    Color(170, 98, 49)
                }else {
                    Color(0, 0, 0)
                }
            )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                tint =
                if(selected){
                    Color(170, 98, 49)
                }else {
                    Color(0, 0, 0)
                }
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route)
        }
    )
}