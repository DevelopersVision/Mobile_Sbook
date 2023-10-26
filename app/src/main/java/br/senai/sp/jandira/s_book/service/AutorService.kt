package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.AutorBaseResponse
import br.senai.sp.jandira.s_book.model.EditoraBaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface AutorService {
    @GET("v1/sbook/autores")
    fun getAutores(): Call<AutorBaseResponse>
}