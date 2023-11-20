package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.AutoresParaPostAnuncio

class ViewModelDosAutores: ViewModel() {
    var autores: List<AutoresParaPostAnuncio>? = emptyList()
}