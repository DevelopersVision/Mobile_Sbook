package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.TipoAnuncioBaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface TipoAnuncioService {
    @GET("v1/sbook/tipo-anuncio")
    fun getTipoAnuncio(): Call<TipoAnuncioBaseResponse>
}