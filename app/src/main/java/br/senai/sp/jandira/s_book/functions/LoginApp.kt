package br.senai.sp.jandira.s_book.functions

import android.content.Context
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository

fun saveLogin (
    context: Context,
    nome: String,
    token: String,
    email: String,
    cep: String,
    idEndereco: Int
){
    val newUser = User(
        nome = nome,
        token = token,
        email = email,
        cep = cep,
        idEndereco = idEndereco
    )

    val id = UserRepository(context).save(newUser)
}