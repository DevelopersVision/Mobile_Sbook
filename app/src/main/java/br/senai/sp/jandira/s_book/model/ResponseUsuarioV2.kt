package br.senai.sp.jandira.s_book.model

data class ResponseUsuarioV2(
    var status: Int,
    var message: String,
    var dados: UsuarioV2
)
