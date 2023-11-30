package br.senai.sp.jandira.s_book.components.anuncio.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.my_announces.components.favoritarAnuncio
import br.senai.sp.jandira.s_book.components.my_announces.components.removerDosFavoritos
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.TipoAnuncio
import br.senai.sp.jandira.s_book.model.VerificarFavoritoBaseResponse
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun BoxAnuncio(
    idAnuncio: Int,
    nomeAnuncio: String,
    listaGeneros: List<Genero>,
    tipoAnuncio: List<TipoAnuncio>,
    fotoUser: String,
    nomeUser: String,
    cidade: String,
    estado: String,
    context: Context,
    lifecycleScope: LifecycleCoroutineScope,
    navRotasController: NavController
) {
    val dadosUser = UserRepository(context).findUsers()
    var isChecked by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .padding(horizontal = 24.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Diário de um Banana",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF404040)
                    )
                )

                IconButton(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 8.dp),
                    onClick = {

                        if(dadosUser.isNotEmpty()){
                            // Cria uma chamada para o EndPoint
                            val call = RetrofitHelper.getAnunciosFavoritadosService()
                                .verificarFavorito(dadosUser[0].id, idAnuncio)


                            // Executar a chamada
                            if (call != null) {
                                call.enqueue(object : Callback<VerificarFavoritoBaseResponse> {
                                    override fun onResponse(
                                        call: Call<VerificarFavoritoBaseResponse>,
                                        response: Response<VerificarFavoritoBaseResponse>
                                    ) {

                                        Log.e("BODY", "onResponse: ${response.body()}")


                                        if (response.isSuccessful) {

                                            Log.e("Ja ta favoritado bixo burro", "Plim")
                                            isChecked = false

                                            removerDosFavoritos(
                                                id_anuncio = idAnuncio,
                                                id_usuario = dadosUser[0].id
                                            )


                                            visible = false


                                        } else {
                                            Log.e("MORREU", "morreu")
                                            Log.e(
                                                "ErrorBody",
                                                "burrei: ${response.errorBody()?.string()!!}",
                                            )
                                            isChecked = true
                                            favoritarAnuncio(
                                                id_anuncio = idAnuncio,
                                                id_usuario = dadosUser[0].id,
                                                lifecycleScope = lifecycleScope
                                            )
                                        }
                                    }


                                    override fun onFailure(
                                        call: Call<VerificarFavoritoBaseResponse>,
                                        t: Throwable
                                    ) {
                                        Log.d("mudou o nome", "Depois da chamada da API:")
                                    }
                                })
                            }
                        }else{
                            navRotasController.navigate("login")
                        }
                    }
                ) {
                    if (isChecked) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.coracao_certo
                            ),
                            contentDescription = ""
                        )


                    } else {
                        Image(
                            painter = painterResource(
                                id = R.drawable.desfavoritar
                            ),
                            contentDescription = ""
                        )


                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.Black)
            ) {}
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            for (genero in listaGeneros){
                if(genero == listaGeneros.last()){
                    Text(
                        text = "${genero.nome} ",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF808080),
                        modifier = Modifier
                    )
                }else{
                    Text(
                        text = "${genero.nome}, ",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF808080),
                        modifier = Modifier
                    )
                }
            }
        }
    }
}