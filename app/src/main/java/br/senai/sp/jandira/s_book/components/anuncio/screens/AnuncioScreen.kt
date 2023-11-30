package br.senai.sp.jandira.s_book.components.anuncio.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.s_book.components.anuncio.components.BoxAnuncio
import br.senai.sp.jandira.s_book.components.my_announces.components.favoritarAnuncio
import br.senai.sp.jandira.s_book.components.my_announces.components.removerDosFavoritos
import br.senai.sp.jandira.s_book.model.VerificarFavoritoBaseResponse
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun AnuncioScreen() {

    val context = LocalContext.current

    val dadosUser = UserRepository(context).findUsers()

    val call = RetrofitHelper.getAnunciosFavoritadosService()
        .verificarFavorito(dadosUser[0].id, 113)


    // Executar a chamada
    if (call != null) {
//        call.enqueue(object : Callback<VerificarFavoritoBaseResponse> {
//            override fun onResponse(
//                call: Call<VerificarFavoritoBaseResponse>,
//                response: Response<VerificarFavoritoBaseResponse>
//            ) {
//
//                Log.e("BODY", "onResponse: ${response.body()}")
//
//
//                if (response.isSuccessful) {
//
//                    Log.e("Ja ta favoritado bixo burro", "Plim")
//                    isChecked = false
//
//                    Log.e("Log de Hoje felipe", "${viewModel.id}")
//
//                    viewModel.id?.let {
//                        removerDosFavoritos(
//                            id_anuncio = viewModel.id!!,
//                            id_usuario = user.id
//                        )
//                    }
//
//                    visible = false
//
//
//                } else {
//                    Log.e("MORREU", "morreu")
//                    Log.e(
//                        "ErrorBody",
//                        "burrei: ${response.errorBody()?.string()!!}",
//                    )
//                    isChecked = true
//                    Log.e("Log de Hoje felipe", "${viewModel.id}")
//                    Log.e("Log de Hoje felipe", "${user.id}")
//                    viewModel.id?.let {
//                        favoritarAnuncio(
//                            id_anuncio = viewModel.id!!,
//                            id_usuario = user.id,
//                            lifecycleScope = lifecycleScope
//                        )
//                    }
//                }
//            }


            override fun onFailure(
                call: Call<VerificarFavoritoBaseResponse>,
                t: Throwable
            ) {
                Log.d("mudou o nome", "Depois da chamada da API:")
            }
        })


//    BoxAnuncio(
//        idAnuncio = ,
//        nomeAnuncio = ,
//        listaGeneros = ,
//        tipoAnuncio = ,
//        fotoUser = ,
//        nomeUser = ,
//        cidade = ,
//        estado = ,
//        context = ,
//        lifecycleScope = ,
//        navRotasController =
//    )
    }
}