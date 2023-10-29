//package br.senai.sp.jandira.s_book.repository
//
//import br.senai.sp.jandira.s_book.model.Autores
//import br.senai.sp.jandira.s_book.model.Editora
//import br.senai.sp.jandira.s_book.service.RetrofitHelper
//import com.google.gson.JsonObject
//import retrofit2.Response
//
//class CadastroLivroRepository {
//
//    private val apiService = RetrofitHelper.postCadastroLivroService()
//
//    suspend fun cadastroLivro(
//        nome: String,
//        numeroPaginas: Int,
//        anoLancamento: Int,
//        descricao: String,
//        edicao: String,
//        isbn: String,
//        preco: Double,
//        idUsuario: Int,
//        idEstadoLivro: Int,
//        idIdioma: Int,
//        idEditora: Editora,
//        fotos: List<String>,
//        tiposAnuncio: List<Int>,
//        generos: List<Int>,
//        autores: List<Autores>
//    ): Response<JsonObject> {
//
//        val idEditoraJson = JsonObject().apply {
//            addProperty("status_editora", false)
//            addProperty("nome_editora", idEditora.nome)
//        }
//
//
//        val requestBody = JsonObject().apply {
//            addProperty("nome", nome)
//            addProperty("numero_paginas", numeroPaginas)
//            addProperty("ano_lancamento", anoLancamento)
//            addProperty("descricao", descricao)
//            addProperty("edicao", edicao)
//            addProperty("isbn", isbn)
//            addProperty("preco", preco)
//            addProperty("id_usuario", idUsuario)
//            addProperty("id_estado_livro", idEstadoLivro)
//            addProperty("id_idioma", idIdioma)
//            addProperty("id_editora", idEditoraJson)
//            addProperty("fotos", fotos)
//            addProperty("tipos_anuncio", tiposAnuncio)
//            addProperty("generos", generos)
//            addProperty("autores", autores)
//        }
//
//        return apiService.cadastroLivro(requestBody)
//    }
//
//}