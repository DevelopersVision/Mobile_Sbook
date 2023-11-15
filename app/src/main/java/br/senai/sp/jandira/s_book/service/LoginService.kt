package br.senai.sp.jandira.s_book.service

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @Headers("Content-Type: application/json")
    @POST("/v1/sbook/login")
    suspend fun loginUsuario(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/v2/sbook/login")
    suspend fun loginUsuarioV2(@Body body: JsonObject): Response<JsonObject>

}