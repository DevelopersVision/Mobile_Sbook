package br.senai.sp.jandira.s_book.components.meu_anuncio.components

import br.senai.sp.jandira.s_book.components.announce.components.ButtonAnuncio

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.model.TipoAnuncio
import br.senai.sp.jandira.s_book.model.VerificarFavoritoBaseResponse
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun BoxMyAnnounce(
    dadosAnuncio: JsonAnuncios,
    context: Context,
    lifecycleScope: LifecycleCoroutineScope,
    navRotasController: NavController
) {
    val dadosUser = UserRepository(context).findUsers()
    var isChecked by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(true) }

    var generosString = ""
    var tipoAnuncio by remember {
        mutableStateOf(listOf<TipoAnuncio>())
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
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
                    modifier = Modifier.width(248.dp),
                    text = dadosAnuncio.anuncio.nome,
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
                                .verificarFavorito(dadosUser[0].id, dadosAnuncio.anuncio.id)


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
                                                id_anuncio = dadosAnuncio.anuncio.id,
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
                                                id_anuncio = dadosAnuncio.anuncio.id,
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
//            for (genero in dadosAnuncio.generos){
//                if(genero == dadosAnuncio.generos.last()){
//                    generosString += genero.nome
//                    Text(
//                        text = generosString,
//                        fontSize = 14.sp,
//                        fontFamily = FontFamily(Font(R.font.intermedium)),
//                        fontWeight = FontWeight(600),
//                        color = Color(0xFF808080),
//                        modifier = Modifier
//                    )
//                }else{
//                    generosString += "${genero.nome}, "
//                }
//            }
            dadosAnuncio.generos.forEach {genero ->
                if(genero == dadosAnuncio.generos.last()){
                    generosString += genero.nome
                    Text(
                        text = generosString,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF808080),
                        modifier = Modifier
                    )
                }else{
                    generosString += "${genero.nome}, "
                }
            }
        }

        if(tipoAnuncio.isNotEmpty()){
            if (tipoAnuncio.size == 2) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(28.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(50.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "R$${dadosAnuncio.anuncio.preco}",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.intermedium)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF404040)
                            )
                        )
                        Text(
                            text = "Ou",
                            style = TextStyle(
                                fontSize = 22.sp,
                                fontFamily = FontFamily(Font(R.font.intermedium)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF404040)
                            )
                        )
                        Text(
                            text = "Troca-se",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily(Font(R.font.intermedium)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFF404040)
                            )
                        )
                    }
                    ButtonAnuncio(onClick = { /*TODO*/ }, text = "Clique Aqui")
                }
            } else if (tipoAnuncio[0].id == 3) {
                Log.e("Entrou", "$tipoAnuncio")
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(28.dp)
                ) {
                    Text(
                        text = "R$${dadosAnuncio.anuncio.preco}",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.intermedium)),
                            fontWeight = FontWeight(700),
                            color = Color(0xFF404040)
                        )
                    )

                    ButtonAnuncio(onClick = { /*TODO*/ }, text = "Clique Aqui")
                }
            } else {
                ButtonAnuncio(onClick = { /*TODO*/ }, text = "Doa-se, Clique Aqui")
            }
        }
    }
}