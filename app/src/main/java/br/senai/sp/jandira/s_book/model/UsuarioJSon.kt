package br.senai.sp.jandira.s_book.model

data class UsuarioJSon(
    val token: String,
    val status: Int? = 0,
    val message: String,
    val usuario: List<Usuario>
)
