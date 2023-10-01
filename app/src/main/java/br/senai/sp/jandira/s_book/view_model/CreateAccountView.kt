package br.senai.sp.jandira.s_book.view_model

import androidx.lifecycle.ViewModel

class CreateAccountView: ViewModel() {
    var nome: String? = ""
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