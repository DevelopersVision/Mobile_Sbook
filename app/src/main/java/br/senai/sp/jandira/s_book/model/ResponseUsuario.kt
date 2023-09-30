package br.senai.sp.jandira.s_book.model

data class ResponseUsuario(
    var status: Int,
    var message: String,
    var dados: Usuario
)
