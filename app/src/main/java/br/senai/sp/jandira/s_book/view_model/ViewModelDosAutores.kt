package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.Autores

class ViewModelDosAutores: ViewModel() {
    var autores: List<Autores>? = emptyList()
}