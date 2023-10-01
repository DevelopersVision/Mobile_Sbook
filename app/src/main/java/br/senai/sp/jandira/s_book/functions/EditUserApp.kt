package br.senai.sp.jandira.s_book.functions

import android.content.Context
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository

fun EditUser(
    context: Context,
    nome: String,
    cep: String,
    dataNascimento: String,
    logradouro: String,
    bairro: String,
    cidade: String,
    ufEstado: String,
    senha: String
): Long {

    val userUpdate = User(
        nome = nome,
        cep = cep,
        dataNascimento = dataNascimento,
        logradouro = logradouro,
        bairro = bairro,
        cidade = cidade,
        ufEstado = ufEstado,
        senha = senha
    )

    return UserRepository(context).update(userUpdate)
}

//fun EditData(
//    context: Context,
//    token: String,
//){
//
//}