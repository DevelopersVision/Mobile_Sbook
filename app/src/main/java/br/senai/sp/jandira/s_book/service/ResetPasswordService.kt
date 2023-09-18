package br.senai.sp.jandira.s_book.service

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ResetPasswordService {

    @Headers("Content-Type: application/json")
    @POST("/v1/sbook/esqueci-senha")
    suspend fun postResetPassword(@Body body: JsonObject): Response<JsonObject>

    @Headers("Content-Type: application/json")
    @POST("/v1/sbook/validar-token")
    suspend fun postValidateToken(@Body body: JsonObject): Response<JsonObject>

}