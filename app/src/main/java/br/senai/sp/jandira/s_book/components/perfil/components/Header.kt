package br.senai.sp.jandira.s_book.components.perfil.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.model.Usuario
import br.senai.sp.jandira.s_book.model.ResponseUsuario
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun Header(
    navController: NavController
) {

    var icons = Icons.Default.ArrowBack

    var camera = Icons.Default.AddAPhoto

    var userRating by remember { mutableStateOf(0) }

    var user by remember {
        mutableStateOf(Usuario(0, "", "", "", "", "", false, "", "", "", "", "", "", id_endereco = 0))
    }

    // Cria uma chamada para o EndPoint
    val call = RetrofitHelper.getUserByIdService().getUsuarioById(2)

    // Executar a chamada
    call.enqueue(object : Callback<ResponseUsuario> {
        override fun onResponse(
            call: Call<ResponseUsuario>,
            response: Response<ResponseUsuario>
        ) {
            Log.e("TAG", "onResponse: ${response.body()}", )
            user = response.body()?.dados!!
        }

        override fun onFailure(call: Call<ResponseUsuario>, t: Throwable) {

        }
    })

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(top = 32.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(43.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
        ) {
            IconButton(
                onClick = {
                         navController.navigate("navigation_home_bar")
                },
                modifier = Modifier
                    .height(64.dp)
                    .width(64.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFCECECE),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
            ) {
                Icon(
                    imageVector = icons,
                    contentDescription = ""
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.Top),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier.size(100.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Card(
                        modifier = Modifier
                            .size(100.dp),
                        shape = CircleShape,
                        border = BorderStroke(
                            width = 2.dp,
                            Brush.horizontalGradient(
                                listOf(
                                    colorResource(id = R.color.purple_200),
                                    Color.White
                                )
                            )
                        )
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.susanna_profile
                            ),
                            contentDescription = "",
                            modifier = Modifier.size(64.dp)
                        )
                    }
                    Icon(
                        imageVector = camera,
                        contentDescription = "",
                        modifier = Modifier
                            .offset(x = 5.dp, y = 5.dp)
                            .size(28.dp),
                    )
                }
                RatingBar(maxRating = 5, initialRating = userRating)

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Text(
                        text = user.nome,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )

                    Text(
                        text = "${user.cidade}, ${user.estado}",
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF565454),
                    )

                }

            }
            IconButton(
                onClick = {
                    navController.navigate("EditUser")
                },
                modifier = Modifier
                    .height(64.dp)
                    .width(64.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFCECECE),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
            ) {
                Image(
                    modifier = Modifier
                        .height(22.dp)
                        .width(25.dp),
                    painter = painterResource(
                        id = R.drawable.editar
                    ),
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun RatingBar(maxRating: Int = 5, initialRating: Int = 0) {
    var currentRating by remember { mutableStateOf(initialRating) }

    Row {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= currentRating) Icons.Default.Star else Icons.Default.Star,
                contentDescription = null, // Pode ser null neste caso
                tint = if (i <= currentRating) Color.Yellow else Color.Gray,
                modifier = Modifier
                    .clickable {
                        currentRating = i
                    }
                    .width(25.dp)
                    .height(25.dp)
            )
        }
    }
}


@Composable
fun RatingScreen() {
    var userRating by remember { mutableStateOf(0) }

    Column {

    }
}