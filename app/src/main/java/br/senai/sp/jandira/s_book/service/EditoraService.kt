package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.EditoraBaseResponse
import retrofit2.Call
import retrofit2.http.GET


interface EditoraService {
    @GET("v1/sbook/editoras")
    fun getEditoras(): Call<EditoraBaseResponse>
}