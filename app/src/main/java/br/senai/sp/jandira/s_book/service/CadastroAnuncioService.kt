package br.senai.sp.jandira.s_book.service

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT

interface CadastroAnuncioService {

    @Headers("Content-Type: application/json")
    @POST("/v1/sbook/anuncio")
    suspend fun cadastroAnuncio(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @PUT("/v1/sbook/anuncio-put")
    suspend fun updateAnuncio(@Body body: JsonObject): Response<JsonObject>

}