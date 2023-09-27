package br.senai.sp.jandira.s_book.model

import androidx.lifecycle.ViewModel

class CreateAccountView: ViewModel() {
    var nome: String? = ""
    var email: String? = ""
    var cpf: String? = ""
    var dataNascimento: String? = ""
    var senha: String? = ""
    var cep: String? = ""
    var estado: String? = ""
    var bairro: String? = ""
    var cidade: String? = ""
    var ufEstado: String? = ""
}