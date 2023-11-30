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
import br.senai.sp.jandira.s_book.components.create_account.components.toAmericanDateFormat
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
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.GeneroProfileV2
import br.senai.sp.jandira.s_book.model.ResponseUsuario
import br.senai.sp.jandira.s_book.model.ResponseUsuarioV2
import br.senai.sp.jandira.s_book.model.Usuario
import br.senai.sp.jandira.s_book.model.UsuarioV2
import br.senai.sp.jandira.s_book.model.ViaCep
import br.senai.sp.jandira.s_book.models_private.User
import br.senai.sp.jandira.s_book.repository.UserCategoryRepository
import br.senai.sp.jandira.s_book.repository.UserUpdateRepository
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.service.RetrofitHelperViaCep
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import br.senai.sp.jandira.s_book.view_model.UserGenresViewModel
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

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


    var generosState by remember {
        mutableStateOf(user.generos)
    }

    var listGeneros by remember {
        mutableStateOf(listOf<Genero>())
    }

    var photoState by remember {
        mutableStateOf<Uri?>(null)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 16.dp)
            .verticalScroll(ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        HeaderProfile {
            navController.navigate("navigation_home_bar")
        }
        PhotoEdit(dadosUser[0].foto) {
            photoState = it
        }
        Form(
            context = context,
            nome = nomeState,
            onNomeChange = {
                nomeState = it
            },
            data = selectedDate,
            onDateChange = {
                Log.e("Date", "$selectedDate e $it")
                selectedDate = it
            },
            cep = cepState,
            onCepChange = {
                if (it.length < 9) {
                    cepState = it

                    if (cepState.length == 8) {

                        val call = RetrofitHelperViaCep.getLocal().getEndereco(cepState)

                        call.enqueue(object : Callback<ViaCep> {
                            override fun onResponse(
                                call: Call<ViaCep>,
                                response: Response<ViaCep>
                            ) {

                                val response = response.body()

                                if (response != null) {

                                    if (response.cep != null && response.state != null) {
                                        ruaState = response.street
                                        cidadeState = response.city
                                        ufEstadoState = response.state
                                        bairroState = response.neighborhood
                                    } else {
                                        Toast.makeText(context, "CEP INVÁLIDO", Toast.LENGTH_LONG)
                                            .show()
                                    }

                                    Log.e("VIACEP - SUCESS - 200", "cep: $response")
                                }
                            }

                            override fun onFailure(call: Call<ViaCep>, t: Throwable) {
                                Toast.makeText(
                                    context,
                                    "SERVIÇO FORA DO AR TENTE MAIS TARDE",
                                    Toast.LENGTH_LONG
                                ).show()
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
        MyCategoriesEditUser(user.generos, navController, userGenresViewModel) {
            generosState = it
        }
        Spacer(modifier = Modifier.height(5.dp))
        ButtonsEditUser {
            Log.d("Sizes", "${user.generos.size} -- ${generosState.size}")

            if (user.generos.size != generosState.size && generosState.isNotEmpty()) {

                for (genero in generosState) {
//                    val generoJson = JsonObject().apply {
//                        addProperty("id", genero.id_genero)
//                        addProperty("nome", genero.nome_genero)
//                    }

                    val generoJson = Genero(id = genero.id_genero, nome = genero.nome_genero)

                    listGeneros = listGeneros + generoJson
                }

                updateUserWithListGenero(
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
                    data_nascimento = selectedDate,
                    cep = cepState,
                    isPersonOver18 = isPersonOver18,
                    generos_preferidos = listGeneros,
                    photo = photoState
                )
            } else {
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
                    data_nascimento = selectedDate,
                    cep = cepState,
                    isPersonOver18 = isPersonOver18,
                    photo = photoState
                )
            }
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
    isPersonOver18: Boolean,
    photo: Uri?
) {

    val dataAmerican = data_nascimento.toAmericanDateFormat()

    if (isPersonOver18) {
        val userUpdateRepository = UserUpdateRepository()

        lifecycleScope.launch {
            if (photo != null) {
                Log.w("PhotoFirebase", "${photo}")
                val storageReference: StorageReference =
                    FirebaseStorage.getInstance().reference.child("images")

                val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

                var imagemUrl: String = ""

                val storageRef = storageReference.child(System.currentTimeMillis().toString())
                storageRef.putFile(photo).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                            //val map = hashMapOf("pic" to downloadUri.toString())

                            val map = HashMap<String, Any>()
                            map["pic"] = downloadUri.toString()
                            map["timestamp"] =
                                LocalDateTime.parse(LocalDateTime.now().toString()).toString()
                            Log.w("NOW", "${LocalDateTime.parse(LocalDateTime.now().toString())}")
                            Log.w(
                                "NOW", "${
                                    LocalDateTime.parse(
                                        LocalDateTime.now().format(
                                            DateTimeFormatter.ISO_DATE_TIME
                                        )
                                    )
                                }"
                            )

                            firebaseFirestore.collection("images").add(map)
                                .addOnCompleteListener { firestoreTask ->
                                    if (firestoreTask.isSuccessful) {
                                        Log.e("Firebase", "Foto adicionada")
                                        Toast.makeText(
                                            context,
                                            "FOTO ADICIONADA COM SUCESSO",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                        firebaseFirestore.collection("images") // Substitua "pic" pelo nome do campo correto
                                            .orderBy("timestamp", Query.Direction.DESCENDING)
                                            .limit(1)
                                            .get()
                                            .addOnSuccessListener { querySnapshot ->
                                                imagemUrl = querySnapshot.documents[0].data!!["pic"].toString()

                                                lifecycleScope.launch {
                                                   val respondeURL = userUpdateRepository.atualizarFotoUsuario(id_usuario, imagemUrl)

                                                    if(respondeURL.isSuccessful){
                                                         navController.navigate("navigation_home_bar")
                                                    }
                                                }
                                            }
                                            .addOnFailureListener { exception ->
                                                Log.e("Firebase", "Erro ao obter imagens: $exception")
                                                exception.printStackTrace()  // Adiciona esta linha para imprimir o stack trace
                                            }

                                    } else {
                                        Log.e("Firebase", "Erro, ${firestoreTask.result}")
                                        Toast.makeText(
                                            context,
                                            "ERRO AO TENTAR REALIZAR O UPLOAD",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "ERRO AO TENTAR REALIZAR O UPLOAD",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            val response = userUpdateRepository.atualizarDadosUsuario(
                id_usuario = id_usuario,
                id_endereco = id_endereco,
                logradouro = logradouro,
                bairro = bairro,
                cidade = cidade,
                estado = estado,
                nome = nome,
                data_nascimento = dataAmerican,
                cep = cep
            )

            val code = response.code()

            if (response.isSuccessful) {
                Toast.makeText(context, "Dados atualizado com sucesso", Toast.LENGTH_LONG).show()

                if(photo == null){
                     navController.navigate("navigation_home_bar")
                }
            } else {
                when (code) {
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
    } else {
        Toast.makeText(context, "Tem que ser maior de 18 anos", Toast.LENGTH_LONG).show()
    }
}

fun updateUserWithListGenero(
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
    isPersonOver18: Boolean,
    generos_preferidos: List<Genero>,
    photo: Uri?
) {

    val dataAmerican = data_nascimento.toAmericanDateFormat()

    if (isPersonOver18) {
        val userUpdateRepository = UserUpdateRepository()
        val userCategoryRepository = UserCategoryRepository()

        lifecycleScope.launch {

            Log.d("PhotoFirebase2", "${photo}")

            if (photo != null) {
                Log.w("PhotoFirebase", "${photo}")
                val storageReference: StorageReference =
                    FirebaseStorage.getInstance().reference.child("images")

                val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

                var imagemUrl: String = ""

                val storageRef = storageReference.child(System.currentTimeMillis().toString())
                storageRef.putFile(photo).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                            //val map = hashMapOf("pic" to downloadUri.toString())

                            val map = HashMap<String, Any>()
                            map["pic"] = downloadUri.toString()

                            firebaseFirestore.collection("images").add(map)
                                .addOnCompleteListener { firestoreTask ->
                                    if (firestoreTask.isSuccessful) {
                                        Log.e("Firebase", "Foto adicionada")
                                        Toast.makeText(
                                            context,
                                            "FOTO ADICIONADA COM SUCESSO",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Log.e("Firebase", "Erro, ${firestoreTask.result}")
                                        Toast.makeText(
                                            context,
                                            "ERRO AO TENTAR REALIZAR O UPLOAD",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "ERRO AO TENTAR REALIZAR O UPLOAD",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                firebaseFirestore.collection("images")
                    .get().addOnSuccessListener {
                        for (i in it) {
                            Log.e("IT", "${it}")
                            if (it.last() == i) {
                                imagemUrl = i.data["pic"].toString()

                                Log.e("FotoFirebase2", imagemUrl)

                                lifecycleScope.launch {
                                    userUpdateRepository.atualizarFotoUsuario(id_usuario, imagemUrl)
                                }
                            }
                        }
                    }
            }

            val responseGeneros =
                userCategoryRepository.newFavoriteGenres(id_usuario, generos_preferidos)
            val response = userUpdateRepository.atualizarDadosUsuario(
                id_usuario = id_usuario,
                id_endereco = id_endereco,
                logradouro = logradouro,
                bairro = bairro,
                cidade = cidade,
                estado = estado,
                nome = nome,
                data_nascimento = dataAmerican,
                cep = cep
            )

            val code = response.code()

            if (response.isSuccessful) {
                Toast.makeText(context, "Dados atualizado com sucesso", Toast.LENGTH_LONG).show()
                 navController.navigate("navigation_home_bar")
            } else {
                when (code) {
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
    } else {
        Toast.makeText(context, "Tem que ser maior de 18 anos", Toast.LENGTH_LONG).show()
    }
}

fun Long.toAmerican(
    pattern: String = "yyyy-MM-dd"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}