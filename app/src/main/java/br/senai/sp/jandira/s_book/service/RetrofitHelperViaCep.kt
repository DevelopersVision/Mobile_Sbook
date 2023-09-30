package br.senai.sp.jandira.s_book.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelperViaCep {
    private const val baseurl = "https://viacep.com.br/ws/"

    private val retrofitFactoryViaCep =
        Retrofit.Builder().
        baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getLocal(): ViaCepService {
        return retrofitFactoryViaCep.create(ViaCepService::class.java)
    }
}