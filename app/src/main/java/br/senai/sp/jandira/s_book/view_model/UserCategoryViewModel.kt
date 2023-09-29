package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.Genero

class UserCategoryViewModel : ViewModel() {

    var id_usuario: Int = 0
    var generos_preferidos: List<Genero>? = null

}