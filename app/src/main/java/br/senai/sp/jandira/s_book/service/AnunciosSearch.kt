package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.AnuncioNoPageBaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface AnunciosSearch {

    @GET("v1/sbook/mumu")
    fun getAnunciosNoPage(): Call<AnuncioNoPageBaseResponse>
}