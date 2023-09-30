package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.ResponseUsuario
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("v1/sbook/usuario/{id}")
    fun getUsuarioById(@Path("id") id: Long): Call<ResponseUsuario>
}