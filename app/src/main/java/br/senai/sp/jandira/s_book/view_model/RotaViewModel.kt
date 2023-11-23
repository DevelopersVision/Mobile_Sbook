package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class RotaViewModel: ViewModel() {
    var navRotasController: NavController? = null
}