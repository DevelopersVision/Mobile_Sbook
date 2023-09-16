package br.senai.sp.jandira.s_book.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val baseurl = "https://app-nodejs.cyclic.cloud"

    private val retrofitFactory =
        Retrofit.Builder().
        baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()



    fun getLoginService(): LoginService {
        return retrofitFactory.create(LoginService::class.java)
    }
}