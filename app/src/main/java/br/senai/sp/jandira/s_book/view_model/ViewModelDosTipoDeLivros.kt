package br.senai.sp.jandira.s_book.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.TipoAnuncio

class ViewModelDosTipoDeLivros: ViewModel() {
    var tiposDoAnuncio: List<Int>? = emptyList()
    var tiposSelecionados by mutableStateOf<Set<String>>(emptySet())
    var tiposSelectedsObjetoInteiro: List<TipoAnuncio>? = emptyList()
}