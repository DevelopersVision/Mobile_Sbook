package br.senai.sp.jandira.s_book.functions

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.repository.CadastroRepository
import kotlinx.coroutines.launch
import org.json.JSONObject

fun createAccountApp (
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
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    rota: String,
    context: Context
){
    val createAccountRepository = CadastroRepository()

    lifecycleScope.launch {

        val response = createAccountRepository.cadastroUsuario(
            nome, cpf, dataNascimento, email, senha, cep, ufEstado, cidade, bairro, logradouro
        )
        val code = response.code()

        Log.e("Response", "createAccountApp: $response", )
        Log.e("ResponseBody", "createAccountApp: ${response.body()}", )

        if(response.isSuccessful){

            Log.e("CADASTRO - SUCESS - 201", "cadastro: ${response.body()}")
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