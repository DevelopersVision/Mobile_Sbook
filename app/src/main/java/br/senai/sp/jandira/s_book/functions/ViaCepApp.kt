package br.senai.sp.jandira.s_book.functions

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.model.ViaCep
import br.senai.sp.jandira.s_book.service.RetrofitHelperViaCep
import br.senai.sp.jandira.s_book.view_model.CreateAccountView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun pegarCEP(cep: String, navController: NavController, rota: String, viewModel: CreateAccountView, context: Context) {

    val validacaoCEP = validationCEP(cep)

    if(validacaoCEP){
        val call = RetrofitHelperViaCep.getLocal().getEndereco(cep)

        call.enqueue(object: Callback<ViaCep> {
            override fun onResponse(call: Call<ViaCep>, response: Response<ViaCep>) {

                val response = response.body()

                if(response != null){
                    viewModel.cep = response.cep
                    viewModel.bairro = response.bairro
                    viewModel.ufEstado = response.uf
                    viewModel.cidade = response.localidade
                    viewModel.logradouro = response.logradouro

                    Log.e("VIACEP - SUCESS - 200", "cep: $response")
                    navController.navigate(rota)
                }
            }

            override fun onFailure(call: Call<ViaCep>, t: Throwable) {
                TODO("Not yet implemented")
            }
        } )
    }else{
        Toast.makeText(context, "CEP INVÁLIDO", Toast.LENGTH_LONG).show()
    }
}