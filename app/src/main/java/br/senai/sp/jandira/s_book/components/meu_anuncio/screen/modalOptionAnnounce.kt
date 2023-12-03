package br.senai.sp.jandira.s_book.components.meu_anuncio.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.model.AnuncioResponseById
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun modalOptionAnnounce(
    statusEncerrar: Boolean,
    navController: NavController,
    dadosAnuncios: JsonAnuncios
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.bar_icon),
                contentDescription = "",
                Modifier.size(75.dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 60.dp, start = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(33.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                if (statusEncerrar) {
                    Text(
                        modifier = Modifier.width(280.dp),
                        text = "Deseja mesmo encerrar esse anuncio confirmando a a venda, troca ou doação?",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.intermedium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                    )
                } else {
                    Text(
                        modifier = Modifier.width(280.dp),
                        text = "Deseja mesmo excluir esse anuncio?",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.intermedium)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .shadow(
                            elevation = 4.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        )
                        .width(120.dp)
                        .height(40.dp)
                        .background(Color(0xFFDA6C27), shape = RoundedCornerShape(size = 4.dp))
                        .padding()
                        .clickable {
                            if (statusEncerrar) {
                                Log.e("Encerrar", "vai ser o encerrar", )
                            } else {
                                val call = RetrofitHelper.getAnunciosByIdUserService().deleteAnuncioById(dadosAnuncios.anuncio.id.toLong())

                                // Executar a chamada
                                call.enqueue(object : Callback<JsonObject> {
                                    override fun onResponse(
                                        call: Call<JsonObject>, response: Response<JsonObject>
                                    ) {
                                        Log.e("ResponseAnuncio", "resposta: $response")

                                        if (response.isSuccessful) {
                                            Toast.makeText(
                                                context,
                                                "ANUNCIO EXCLUIDO COM SUCESSO",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            navController.navigate("my_announces")
                                        }
                                    }

                                    override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                                        Toast.makeText(
                                            context,
                                            "SERVIDOR ESTÁ FORA DO AR, TENTE NOVAMENTE MAIS TARDE",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        Log.d("ERROR_FEED-tmessage", "${t.message}")
                                        Log.d("ERROR_FEED-tstacktrace", "${t.stackTrace}")
                                    }
                                })
                            }
                        },
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Sim",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                }
                Spacer(modifier = Modifier.width(38.dp))
                Row(
                    modifier = Modifier
                        .shadow(
                            elevation = 4.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        )
                        .width(120.dp)
                        .height(40.dp)
                        .background(Color(0xFFE6E6E6), shape = RoundedCornerShape(size = 4.dp))
                        .padding()
                        .clickable {
                            navController.navigate("myAnnounce")
                        },
                    horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Não",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF808080),
                        )
                    )
                }
            }
        }
    }
}