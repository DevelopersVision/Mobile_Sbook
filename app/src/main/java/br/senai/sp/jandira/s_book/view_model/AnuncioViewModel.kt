package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.s_book.model.Foto

class AnuncioViewMODEL: ViewModel() {
    //var foto: Foto? = ""
    var email: String? = ""
    var cpf: String? = ""
    var dataNascimento: String? = ""
    var senha: String? = ""
    var cep: String = ""
    var logradouro: String = ""
    var bairro: String = ""
    var cidade: String = ""
    var ufEstado: String = ""
}

//var foto: Foto = Foto(id = 0, foto = ""),
//var nome: String? = "",