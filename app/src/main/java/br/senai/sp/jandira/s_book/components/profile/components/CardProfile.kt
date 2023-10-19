package br.senai.sp.jandira.s_book.components.profile.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.perfil.components.converterData
import br.senai.sp.jandira.s_book.components.universal.ButtonProfile
import br.senai.sp.jandira.s_book.functions.deleteUserSQLite
import br.senai.sp.jandira.s_book.functions.saveLogin
import br.senai.sp.jandira.s_book.model.ResponseUsuario
import br.senai.sp.jandira.s_book.model.Usuario
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CardProfile(
    navController: NavController,
    context: Context
) {

    val userRating by remember { mutableStateOf(0) }

    val dadaUser = UserRepository(context).findUsers()

    var array = User()

    var data = ""

    var user by remember {
        mutableStateOf(Usuario(0, "", "", "", "", "", false, "", "", "", "", "", "", id_endereco = 0))
    }

    if (dadaUser.isNotEmpty()) {
        array = dadaUser[0]

        // Cria uma chamada para o EndPoint
        val call = RetrofitHelper.getUserByIdService().getUsuarioById(array.id)

        // Executar a chamada
        call.enqueue(object : Callback<ResponseUsuario> {
            override fun onResponse(
                call: Call<ResponseUsuario>,
                response: Response<ResponseUsuario>
            ) {
                Log.e("TAG", "onResponse: ${response.body()}")
                user = response.body()?.dados!!

                deleteUserSQLite(context = context, array.id.toInt())

                saveLogin(
                    context = context,
                    id = user.id_usuario,
                    nome = user.nome,
                    token = array.token,
                    email = user.email,
                    cep = user.cep,
                    idEndereco = user.id_endereco,
                    foto = user.foto,
                    dataNascimento = user.data_nascimento,
                    logradouro = user.logradouro,
                    bairro = user.bairro,
                    cidade = user.cidade,
                    ufEstado = user.estado,
                    senha = array.senha,
                    cpf = user.cpf
                )
            }

            override fun onFailure(call: Call<ResponseUsuario>, t: Throwable) {

            }
        })

        data = converterData(array.dataNascimento)
    }else{
        navController.navigate("navigation_home_bar")
    }





    Card(
        modifier = Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0xFF000000),
                ambientColor = Color(0xFF000000)
            )
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(size = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                AsyncImage(
                    model = user.foto,
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillBounds
                )
//                Image(
//                    painter = painterResource(id = R.drawable.susanna_profile),
//                    contentDescription = "Foto de perfil",
//                    modifier = Modifier
//                        .size(100.dp)
//                        .clip(RoundedCornerShape(8.dp)),
//                    contentScale = ContentScale.FillBounds
//                )
                Column() {
                    Text(
                        text = user.nome,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFDDA35D)
                    )
                    Text(
                        text = user.email,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF808080)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
//                    br.senai.sp.jandira.s_book.components.perfil.components.RatingBar(
//                        maxRating = 5,
//                        initialRating = userRating
//                    )
                }
            }
            ButtonProfile(
                "Editar Conta",
                onclick = { navController.navigate("editUser") }
            )
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