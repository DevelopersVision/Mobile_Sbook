package br.senai.sp.jandira.s_book.service

import br.senai.sp.jandira.s_book.model.Advertiser
import br.senai.sp.jandira.s_book.model.AnuncioNoPageBaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AdvertiserService {

    @GET("/v1/sbook/anunciante/{id}")
    fun getAdvertiser(@Path("id") id: Int): Call<Advertiser>
}