package br.senai.sp.jandira.s_book.repository

import br.senai.sp.jandira.s_book.service.RetrofitHelper
import com.google.gson.JsonObject
import retrofit2.Response

class ResetPasswordRepository {

    private val apiService = RetrofitHelper.postResetPasswordService()

    suspend fun resetPassword(email: String): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("email", email)
        }

        return apiService.postResetPassword(requestBody)
    }

    suspend fun validateToken(id: Int?, token: Int?): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("id", id)
        }

        return apiService.postValidateToken(requestBody)
    }

}