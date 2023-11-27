package br.senai.sp.jandira.s_book.components.edit_user.screen

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.edit_user.components.ButtonsEditUser
import br.senai.sp.jandira.s_book.components.edit_user.components.Form
import br.senai.sp.jandira.s_book.components.edit_user.components.MyCategoriesEditUser
import br.senai.sp.jandira.s_book.components.edit_user.components.PhotoEdit
import br.senai.sp.jandira.s_book.components.perfil.components.converterData
import br.senai.sp.jandira.s_book.components.universal.ButtonProfile
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.components.universal.HeaderProfile
import br.senai.sp.jandira.s_book.functions.deleteUserSQLite
import br.senai.sp.jandira.s_book.functions.saveLogin
import br.senai.sp.jandira.s_book.model.GeneroProfileV2
import br.senai.sp.jandira.s_book.model.ResponseUsuario
import br.senai.sp.jandira.s_book.model.ResponseUsuarioV2
import br.senai.sp.jandira.s_book.model.Usuario
import br.senai.sp.jandira.s_book.model.UsuarioV2
import br.senai.sp.jandira.s_book.model.ViaCep
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.repository.UserUpdateRepository
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.service.RetrofitHelperViaCep
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.UserGenresViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//@Preview(showSystemUi = true)
@Composable
fun EditUser(
    navController: NavController,
    userGenresViewModel: UserGenresViewModel,
    lifecycleScope: LifecycleCoroutineScope
) {

    val context = LocalContext.current

    val dadosUser = UserRepository(context).findUsers()

    var user by remember {
        mutableStateOf(
            UsuarioV2(
                id_usuario = 0,
                nome = "",
                data_nascimento = "",
                data_criacao = "",
                email = "",
                cpf = "",
                status_usuario = true,
                foto = "",
                logradouro = "",
                bairro = "",
                cidade = "",
                estado = "",
                cep = "",
                id_endereco = 0,
                generos = listOf()
            )
        )
    }

    // Cria uma chamada para o EndPoint
    val call = RetrofitHelper.getUserByIdService().getUsuarioByIdProfile(dadosUser[0].id)

    // Executar a chamada
    call.enqueue(object : Callback<ResponseUsuarioV2> {
        override fun onResponse(
            call: Call<ResponseUsuarioV2>,
            response: Response<ResponseUsuarioV2>
        ) {
            user = response.body()!!.dados
        }

        override fun onFailure(call: Call<ResponseUsuarioV2>, t: Throwable) {
            Log.e("Morreu User", "Morreu na call de user no feed")
        }
    })

    var date by remember {
        mutableStateOf("")
    }
    userGenresViewModel.id_usuario = dadosUser[0].id.toInt()

    date = converterData(dadosUser[0].dataNascimento)

    var nomeState by remember {
        mutableStateOf(dadosUser[0].nome)
    }

    var ruaState by remember {
        mutableStateOf(dadosUser[0].logradouro)
    }

    var isPersonOver18 by remember {
        mutableStateOf(true)
    }

    var cidadeState by remember {
        mutableStateOf(dadosUser[0].cidade)
    }

    var ufEstadoState by remember {
        mutableStateOf(dadosUser[0].ufEstado)
    }

    var emailState by remember {
        mutableStateOf(dadosUser[0].email)
    }

    var cepState by remember {
        mutableStateOf(dadosUser[0].cep)
    }

    var bairroState by remember {
        mutableStateOf(dadosUser[0].bairro)
    }

    val id_endereco = dadosUser[0].idEndereco

    var selectedDate by remember { mutableStateOf(date) }

    cepState = cepState.replace("-", "")





    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 16.dp)
            .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        HeaderProfile {
            navController.navigate("profile")
        }
        PhotoEdit(dadosUser[0].foto)
        Form(
            context = context,
            nome = nomeState,
            onNomeChange = {
                nomeState = it
            },
            data = selectedDate,
            onDateChange = {
                selectedDate = it
            },
            cep = cepState,
            onCepChange = {
                if (it.length < 9) {
                    cepState = it

                    if(cepState.length == 8){

                        val call = RetrofitHelperViaCep.getLocal().getEndereco(cepState)

                        call.enqueue(object : Callback<ViaCep> {
                            override fun onResponse(
                                call: Call<ViaCep>,
                                response: Response<ViaCep>
                            ) {

                                val response = response.body()

                                if (response != null) {

                                    if(response.cep != null && response.uf != null ){
                                        ruaState = response.logradouro
                                        cidadeState = response.localidade
                                        ufEstadoState = response.uf
                                        bairroState = response.bairro
                                    }else{
                                        Toast.makeText(context, "CEP INVÁLIDO", Toast.LENGTH_LONG).show()
                                    }

                                    Log.e("VIACEP - SUCESS - 200", "cep: $response")
                                }
                            }

                            override fun onFailure(call: Call<ViaCep>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })

                    }
                }
            },
            logradouro = ruaState,
            ufEstado = ufEstadoState,
            cidade = cidadeState,
            email = emailState,
            onIsPersonOver18 = {
                isPersonOver18 = it
            }
        )
        MyCategoriesEditUser(user.generos, navController, userGenresViewModel)
        Spacer(modifier = Modifier.height(5.dp))
        ButtonsEditUser {
            updateUser(
                lifecycleScope = lifecycleScope,
                context = context,
                navController = navController,
                onChangeLoading = {

                },
            id_usuario = dadosUser[0].id.toInt(),
            id_endereco = id_endereco,
            logradouro = ruaState,
            bairro = bairroState,
            cidade = cidadeState,
            estado = ufEstadoState,
            nome = nomeState,
            data_nascimento = date,
            cep = cepState,
            isPersonOver18 = isPersonOver18
            )
        }
    }
}


fun updateUser(
    lifecycleScope: LifecycleCoroutineScope,
    context: Context,
    navController: NavController,
    onChangeLoading: (String) -> Unit,
    id_usuario: Int,
    id_endereco: Int,
    logradouro: String,
    bairro: String,
    cidade: String,
    estado: String,
    nome: String,
    data_nascimento: String,
    cep: String,
    isPersonOver18: Boolean
){

    if(isPersonOver18){
        val userUpdateRepository = UserUpdateRepository()

        lifecycleScope.launch {
            val response = userUpdateRepository.atualizarDadosUsuario(
                id_usuario = id_usuario,
                id_endereco = id_endereco,
                logradouro = logradouro,
                bairro = bairro,
                cidade = cidade,
                estado = estado,
                nome = nome,
                data_nascimento = data_nascimento,
                cep = cep
            )

            val code = response.code()

            if(response.isSuccessful){
                Toast.makeText(context, "Dados atualizado com sucesso", Toast.LENGTH_LONG).show()
                navController.navigate("profile")
            }else{
                when(code){
                    400 -> {
                        Toast.makeText(
                            context,
                            "Falta dados a serem enviados",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    500 -> {
                        Toast.makeText(
                            context,
                            "Servidor está fora do ar, tente mais tarde",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }else{
        Toast.makeText(context, "Tem que ser maior de 18 anos", Toast.LENGTH_LONG).show()
    }

}

