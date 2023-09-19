package br.senai.sp.jandira.s_book.service

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.PUT

interface ChangePasswordService {

    @Headers("Content-Type: application/json")
    @PUT("/v1/sbook/recuperar-conta")
    suspend fun mudarSenha(@Body body: JsonObject): Response<JsonObject>

}