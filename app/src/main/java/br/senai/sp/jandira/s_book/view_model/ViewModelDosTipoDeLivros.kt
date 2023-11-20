package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.TipoAnuncio

class ViewModelDosTipoDeLivros: ViewModel() {
    var tiposDoAnuncio: List<Int>? = emptyList()
}