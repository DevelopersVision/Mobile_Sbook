package br.senai.sp.jandira.s_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.senai.sp.jandira.s_book.components.create_account.screen.CreateContScreen
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.components.universal.GoogleScreen
import br.senai.sp.jandira.s_book.navigation_home_bar.MainScreen
import br.senai.sp.jandira.s_book.ui.theme.SBOOKTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SBOOKTheme {
                // A surface container using the 'background' color from the theme
//                LoginScreen()
//                CreateContScreen()
//                AddressScreen()
//                RediscoverPasswordScreen()
//                ThanksScreen()
//                ForgotPasswordScreen()
//                InsertCode()
//                CategoryList()
//                CategoryScreen()
                MainScreen()
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

    GoogleScreen()

    DefaultButtonScreen(text = "Entrar") {
        
    }
}


