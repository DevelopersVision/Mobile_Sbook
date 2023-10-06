package br.senai.sp.jandira.s_book.functions


fun validationCEP (cep: String): Boolean{
    return !(cep.length > 12 || cep.length < 6)
}

fun dataValidation(email: String, senha: String): Boolean {
    return !(email == "" || email.length > 255 || senha == "")
}