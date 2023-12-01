package br.senai.sp.jandira.s_book.view_model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class SharedViewModel : ViewModel() {
    val navController = mutableStateOf<NavController?>(null)
}