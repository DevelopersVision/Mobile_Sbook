package br.senai.sp.jandira.s_book.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ViewModelDosIds: ViewModel() {
    var id_editora: Int? = null
    var id_estadoLivro: Int? = null
    var id_idioma: Int? = null
    var estadosSelecionados by mutableStateOf<Set<String>>(emptySet())
}