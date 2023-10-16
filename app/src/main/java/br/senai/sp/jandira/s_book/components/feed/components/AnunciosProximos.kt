package br.senai.sp.jandira.s_book.components.feed.components
import android.util.Log
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.favorite.components.favoritarAnuncio
import br.senai.sp.jandira.s_book.components.favorite.components.removerDosFavoritos
import br.senai.sp.jandira.s_book.model.VerificarFavoritoBaseResponse
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnunciosProximos(
    id: Int,
    nome_livro: String,
    autor: String,
    tipo_anuncio: String,
    preco: Double?,
    foto: String,
    lifecycleScope: LifecycleCoroutineScope?,
    navController: NavController,
    onClick: () -> Unit
) {

    val context = LocalContext.current
    val array = UserRepository(context).findUsers()
    var user = User()

    if (array.isNotEmpty()) {
        user = array[0]
    }

    val coracao = Icons.Default.Favorite
    var isChecked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .width(156.dp)
            .height(260.dp)
            .clickable {
                onClick()
                navController.navigate("annouceDetail")
            }
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0xFF000000),
                ambientColor = Color(0xFF000000)
            ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = "${foto}",
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(96.dp)
                        .height(148.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${nome_livro}",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
            )
            Text(
                text = "${autor}",
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF9F9898),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (tipo_anuncio == "Doação") {
                    Text(
                        text = "Doa-se",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.poppinsmedium
                            )
                        ),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                } else if (tipo_anuncio == "Troca") {
                    Text(
                        text = "Troca-se",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.poppinsmedium
                            )
                        ),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                } else {
                    Text(
                        text = "R$" + preco,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.poppinsmedium
                            )
                        ),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                }
                IconButton(
                    modifier = Modifier
                        .width(50.dp)
                        .height(42.dp),
                    onClick = {
                        // Cria uma chamada para o EndPoint
                        val call = RetrofitHelper.getAnunciosFavoritadosService()
                            .verificarFavorito(user.id, id)


                        // Executar a chamada
                        call.enqueue(object : Callback<VerificarFavoritoBaseResponse> {
                            override fun onResponse(
                                call: Call<VerificarFavoritoBaseResponse>,
                                response: Response<VerificarFavoritoBaseResponse>
                            ) {

                                Log.e("BODY", "onResponse: ${response.body()}")

                                if (response.isSuccessful) {

                                    Log.e("Ja ta favoritado bixo burro", "Plim")
                                    isChecked = false


                                    removerDosFavoritos(id_anuncio = id, id_usuario = user.id)
                                } else {
                                    Log.e("MORREU", "morreu")
                                    Log.e(
                                        "ErrorBody",
                                        "burrei: ${response.errorBody()?.string()!!}",
                                    )
                                    isChecked = true

                                    Log.e("Log de Hoje felipe", "${id}")
                                    Log.e("Log de Hoje felipe", "${user.id}")
                                    if (lifecycleScope != null) {
                                        favoritarAnuncio(
                                            id_anuncio = id,
                                            id_usuario = user.id,
                                            lifecycleScope = lifecycleScope
                                        )
                                    }
                                }
                            }


                            override fun onFailure(
                                call: Call<VerificarFavoritoBaseResponse>,
                                t: Throwable
                            ) {
                                Log.d("mudou o nome", "Depois da chamada da API:")
                            }
                        })
                        Log.i("testando123", "${call}")
                    }
                ) {

                    val iconTint = if (isChecked) Color.Red else Color.Black

                    androidx.compose.material3.Icon(
                        imageVector = coracao,
                        contentDescription = "",
                        tint = iconTint
                    )
                }
            }
        }
    }
}