package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.GeneroProfileV2

class UserGenresViewModel: ViewModel() {
    var id_usuario: Int = 0
    var generos: List<GeneroProfileV2> = listOf()
    var newList: List<Genero> = listOf()
}