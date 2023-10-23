package br.senai.sp.jandira.s_book.functions

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.Editora
import br.senai.sp.jandira.s_book.model.Foto
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.TipoAnuncio
import br.senai.sp.jandira.s_book.repository.CadastroAnuncioRepository
import br.senai.sp.jandira.s_book.repository.CadastroRepository
import br.senai.sp.jandira.s_book.view_model.UserCategoryViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject

fun createAnnounceApp (
    nome: String,
    numeroPaginas: Int,
    anoLancamento: String,
    descricao: String,
    edicao: String,
    isbn: String,
    preco: Double,
    idUsuario: Int,
    idEstadoLivro: Int,
    idIdioma: Int,
    idEditora: Editora,
    fotos: List<Foto>,
    tiposAnuncio: List<TipoAnuncio>,
    generos: List<Genero>,
    autores: List<Autores>,
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    rota: String,
    context: Context,
    viewModelUserCategory: UserCategoryViewModel
){
    val createAnnounceRepository = CadastroAnuncioRepository()

    lifecycleScope.launch {

        val response = createAnnounceRepository.cadastroAnuncio(
            nome, numeroPaginas, anoLancamento, descricao, edicao, isbn, preco, idUsuario, idEstadoLivro, idIdioma
        )
        val code = response.code()

        if(response.isSuccessful){

            Log.e("CADASTRO - SUCESS - 201", "cadastro: ${response.body()}")

            val jsonString = response.body().toString()
            val jsonObject = JSONObject(jsonString)
            val id = jsonObject.getInt("id")

            Log.e("jsonString", "$jsonString")
            Log.e("jsonObject", "$jsonObject")
            Log.e("id", "$id")

            viewModelUserCategory.id_usuario = id

            Toast.makeText(context, "Bem Vindo $nome", Toast.LENGTH_SHORT).show()

            navController.navigate(rota)
        }else{
            when (code) {
                404 -> {
                    Log.e("CADASTRO - ERROR - 404", "cadastro: ${response.errorBody()?.string()}")
                    Toast.makeText(
                        context, "ALGUMA INFORMAÇÃO NÃO É VALIDADA", Toast.LENGTH_LONG
                    ).show()
                }
                500 -> {
                    Log.e("CADASTRO - ERROR - 500", "cadastro: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "SERVIDOR INDISPONIVEL NO MOMENTO", Toast.LENGTH_LONG)
                        .show()
                }
                400 -> {
                    Log.e("CADASTRO - ERROR - 400", "cadastro: ${response.errorBody()?.string()}")
                    Toast.makeText(
                        context,
                        "NÃO FORAM PREENCHIDO TODOS OS CAMPOS OBRIGATÓRIOS",
                        Toast.LENGTH_LONG
                    ).show()
                }
                403 -> {
                    Log.e("CADASTRO - ERROR - 403", "cadastro: ${response.errorBody()?.string()}")
                    Toast.makeText(context, "A CONTA ESTÁ DESATIVADA", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}