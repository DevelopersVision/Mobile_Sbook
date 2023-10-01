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
    idEndereco: Int,
    id: Long,
    foto: String,
    dataNascimento: String,
    logradouro: String,
    bairro: String,
    cidade: String,
    ufEstado: String,
    senha: String,
    cpf: String
): Long {
    val newUser = User(
        id = id,
        nome = nome,
        token = token,
        email = email,
        cep = cep,
        idEndereco = idEndereco,
        foto = foto,
        dataNascimento = dataNascimento,
        logradouro = logradouro,
        bairro = bairro,
        cidade = cidade,
        ufEstado = ufEstado,
        senha = senha,
        cpf = cpf
    )

    return UserRepository(context).save(newUser)
}

fun deleteUserSQLite(context: Context, id: Int): Int {
    return UserRepository(context).deleteUser(id)
}
