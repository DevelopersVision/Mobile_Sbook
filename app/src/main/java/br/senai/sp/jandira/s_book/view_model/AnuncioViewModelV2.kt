package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.JsonAnuncios

class AnuncioViewModelV2: ViewModel(){
    var idAnuncio: Int? = 0
    var idAnunciante: Int? = 0
    var foto: String? = ""
    var nome: String? = ""
    var endereco: String? = ""
    lateinit var dadosAnuncio: JsonAnuncios
}

