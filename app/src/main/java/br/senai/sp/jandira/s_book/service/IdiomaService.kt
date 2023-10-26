package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.IdiomaBaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface IdiomaService {

    @GET("v1/sbook/idiomas")
    fun getIdiomas(): Call<IdiomaBaseResponse>
}