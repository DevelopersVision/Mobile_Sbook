package br.senai.sp.jandira.s_book.repository

import br.senai.sp.jandira.s_book.service.RetrofitHelper
import com.google.gson.JsonObject
import retrofit2.Response

class LoginRepository {
    private val apiService = RetrofitHelper.getLoginService()

    suspend fun loginUsuario(email: String?, senha: String?): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("email", email)
            addProperty("senha", senha)
        }

        return apiService.postUsuario(requestBody)
    }
}