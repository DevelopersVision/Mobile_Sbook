package br.senai.sp.jandira.s_book.repository

import br.senai.sp.jandira.s_book.service.RetrofitHelper
import com.google.gson.JsonObject
import retrofit2.Response

class CadastroRepository {
    private val apiService = RetrofitHelper.postCadastroService()

    suspend fun cadastroUsuario(
        nome: String,
        cpf: String,
        dataNascimento: String,
        email: String,
        senha: String,
        cep: String,
        ufEstado: String,
        cidade: String,
        bairro: String,
        logradouro: String,
    ): Response<JsonObject> {
        val requestBody = JsonObject().apply {
            addProperty("nome_usuario", nome)
            addProperty("cpf_usuario", cpf)
            addProperty("data_nascimento_usuario", dataNascimento)
            addProperty("email_usuario", email)
            addProperty("senha_usuario", senha)

            addProperty("cep_endereco", cep)
            addProperty("estado_endereco", ufEstado)
            addProperty("cidade_endereco", cidade)
            addProperty("bairro_endereco", bairro)
            addProperty("logradouro_endereco", logradouro)
        }

        return apiService.cadastroUsuario(requestBody)
    }
}