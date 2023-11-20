package br.senai.sp.jandira.s_book.components.edit_user.screen

import android.util.Log
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
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.service.RetrofitHelperViaCep
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//@Preview(showSystemUi = true)
@Composable
fun EditUser(
    navController: NavController
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

    date = converterData(dadosUser[0].dataNascimento)

    var nomeState by remember {
        mutableStateOf(dadosUser[0].nome)
    }

    var ruaState by remember {
        mutableStateOf(dadosUser[0].logradouro)
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
                                    ruaState = response.logradouro
                                    cidadeState = response.localidade
                                    ufEstadoState = response.uf

                                    Log.e("VIACEP - SUCESS - 200", "cep: $response")
                                }
                            }

                            override fun onFailure(call: Call<ViaCep>, t: Throwable) {
                                TODO("Not yet implemented")
                            }
                        })

                    }

//                    if(cepState.length == 8 && cepState[5] == '-'){
//
//
//                        Log.e("Teste 1", "Entrou nesse + $cepState", )
//                        cepState = it
//
//                        cepState = cepState.substring(0, 5) + cepState.substring(5 + 1)
//
//                        val call = RetrofitHelperViaCep.getLocal().getEndereco(cepState)
//
//                        call.enqueue(object : Callback<ViaCep> {
//                            override fun onResponse(
//                                call: Call<ViaCep>,
//                                response: Response<ViaCep>
//                            ) {
//
//                                val response = response.body()
//
//                                if (response != null) {
//                                    ruaState = response.logradouro
//                                    cidadeState = response.localidade
//                                    ufEstadoState = response.uf
//
//                                    Log.e("VIACEP - SUCESS - 200", "cep: $response")
//                                }
//                            }
//
//                            override fun onFailure(call: Call<ViaCep>, t: Throwable) {
//                                TODO("Not yet implemented")
//                            }
//                        })
//
//
//                    }else{
//                        if (cepState.length == 8) {
//                            Log.e("Teste 2", "Entrou aqui + $cepState", )
//
//                            val call = RetrofitHelperViaCep.getLocal().getEndereco(cepState)
//
//                            cepState = it
//
//                            call.enqueue(object : Callback<ViaCep> {
//                                override fun onResponse(
//                                    call: Call<ViaCep>,
//                                    response: Response<ViaCep>
//                                ) {
//
//                                    val response = response.body()
//
//                                    if (response != null) {
//                                        ruaState = response.logradouro
//                                        cidadeState = response.localidade
//                                        ufEstadoState = response.uf
//
//                                        Log.e("VIACEP - SUCESS - 200", "cep: $response")
//                                    }
//                                }
//
//                                override fun onFailure(call: Call<ViaCep>, t: Throwable) {
//                                    TODO("Not yet implemented")
//                                }
//                            })
//
//
//                        }else{
//                            cepState = it
//
//                            Log.e("Teste 3", "Entrou aqui + $cepState", )
//                        }
//                    }
                }
            },
            logradouro = ruaState,
            ufEstado = ufEstadoState,
            cidade = cidadeState,
            email = emailState
        )
        MyCategoriesEditUser(user.generos)
        Spacer(modifier = Modifier.height(5.dp))
        ButtonsEditUser {

        }
    }
}

