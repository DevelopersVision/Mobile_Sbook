package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.Genero

class ViewModelDosGenerosSelecionados: ViewModel() {

    var selectedGeneros: List<Genero>? = emptyList()
}