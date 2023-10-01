package br.senai.sp.jandira.s_book.models_private

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_user")
data class User(
    @PrimaryKey val id: Long = 0,
    val nome: String = "",
    val token: String = "",
    val email: String = "",
    val senha: String = "",
    val foto: String = "",
    val dataNascimento: String = "",
    val cpf: String = "",
    val idEndereco: Int = 0,
    val cep: String = "",
    val logradouro: String = "",
    val bairro: String = "",
    val cidade: String = "",
    val ufEstado: String = ""
)
