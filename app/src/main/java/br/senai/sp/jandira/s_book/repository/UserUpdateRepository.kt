package br.senai.sp.jandira.s_book.repository

import br.senai.sp.jandira.s_book.service.RetrofitHelper
import com.google.gson.JsonObject
import retrofit2.Response

class UserUpdateRepository {
    private val apiService = RetrofitHelper.updateUserService()

    suspend fun atualizarDadosUsuario(
        id_usuario: Int,
        id_endereco: Int,
        logradouro: String,
        bairro: String,
        cidade: String,
        estado: String,
        nome: String,
        data_nascimento: String,
        cep: String
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("id_usuario", id_usuario)
            addProperty("id_endereco", id_endereco)
            addProperty("logradouro_endereco", logradouro)
            addProperty("bairro_endereco", bairro)
            addProperty("cidade_endereco", cidade)
            addProperty("estado_endereco", estado)
            addProperty("cep_endereco", cep)
            addProperty("nome_usuario", nome)
            addProperty("data_nascimento_usuario", data_nascimento)
        }

        return apiService.updateUserData(requestBody)
    }

    fun atualizarFotoUsuario(id: Int, foto: String): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("id", id)
            addProperty("foto", foto)
        }

        return apiService.updateUserPhoto(requestBody)
    }
}