package br.senai.sp.jandira.s_book.components.feed.components

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.favorite.screen.FavoritoScreen
import br.senai.sp.jandira.s_book.components.perfil.components.converterData
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
fun Header(
    navController: NavController,
    navRotasController: NavController,
    context: Context
) {

    val dadaUser = UserRepository(context).findUsers()

    var array = User()

    var data = ""

    var user by remember {
        mutableStateOf(Usuario(0, "", "", "", "", "", false, "", "", "", "", "", "", id_endereco = 0))
    }

    if (dadaUser.isNotEmpty()) {
        array = dadaUser[0]

        data = converterData(array.dataNascimento)

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
    }




    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.fundo),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "",
                    modifier = Modifier.size(52.dp)
                )
                if(user.id_usuario.toInt() == 0){
                    Image(
                        painter = painterResource(id = R.drawable.padrao),
                        contentDescription = "",
                        modifier = Modifier.size(32.dp)
                            .clickable {
                                if(dadaUser.isNotEmpty()){
                                    navRotasController.navigate("profile")
                                }else{
                                    navRotasController.navigate("login")
                                }
                            }
                    )
                }else{
                    AsyncImage(
                        model = user.foto,
                        contentDescription = "",
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .clickable {
                                if(dadaUser.isNotEmpty()){
                                    navRotasController.navigate("profile")
                                }else{
                                    navRotasController.navigate("login")
                                }
                            }
                    )
                }
            }
            Text(
                text = "Bem-Vindo, Usuário(a)!",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight(700)
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun HeaderPreview() {
//    Header()
}