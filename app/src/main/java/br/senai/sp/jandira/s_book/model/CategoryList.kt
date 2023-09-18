package br.senai.sp.jandira.s_book.repository

import br.senai.sp.jandira.s_book.model.Genero

data class CategoryList(
    val status: Int,
    val message: String,
    val quantidade: Int,
    val dados: List<Genero>
)

