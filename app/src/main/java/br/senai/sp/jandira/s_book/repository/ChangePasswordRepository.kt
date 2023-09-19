package br.senai.sp.jandira.s_book.repository

import br.senai.sp.jandira.s_book.service.RetrofitHelper
import com.google.gson.JsonObject
import retrofit2.Response


class ChangePasswordRepository {

    private val apiService = RetrofitHelper.putChangePassword()

    suspend fun mudarSenha(
        id: Int?,
        password: String
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("id", id)
            addProperty("password", password)
        }

        return apiService.mudarSenha(requestBody)
    }



}