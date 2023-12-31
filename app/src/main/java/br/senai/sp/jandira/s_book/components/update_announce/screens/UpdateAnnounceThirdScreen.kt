package br.senai.sp.jandira.s_book.components.update_announce.screens

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.Storage
import br.senai.sp.jandira.s_book.components.universal.HeaderCreateAnnounce
import br.senai.sp.jandira.s_book.components.update_announce.components.HeaderUpdateAnnounce
import br.senai.sp.jandira.s_book.model.Foto
import br.senai.sp.jandira.s_book.model.chat.view_model.viewModelId
import br.senai.sp.jandira.s_book.view_model.AnnouncePhotosViewModel
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import br.senai.sp.jandira.s_book.view_model.ViewModelDoPostAnuncio
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun UpdateAnnounceThirdScreen(
    navController: NavController,
    localStorage: Storage,
    viewModelDoPostAnuncio: ViewModelDoPostAnuncio,
    viewModelImagens: AnnouncePhotosViewModel,
    viewModelV2: AnuncioViewModelV2
    ) {

    val context = LocalContext.current

//    val storageRef: StorageReference = FirebaseStorage.getInstance().reference.child("images")
//
//    val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
//
//    var fotoUri by rememberSaveable {
//        mutableStateOf<Uri?>(null)
//    }
//
//    val painter = rememberAsyncImagePainter(
//        ImageRequest.Builder(context).data(fotoUri).build()
//    )
//
//    var isImageSelected by remember { mutableStateOf(false) }
//
//    var selectedMedia by rememberSaveable {
//        mutableStateOf<List<Uri>>(emptyList())
//    }
//
    var arrayFoto = listOf<String>()
    viewModelV2.dadosAnuncio.foto.forEach {
        arrayFoto = arrayFoto + it.foto
    }


    var fotoSelecionado by rememberSaveable {
        mutableStateOf<List<String>>(arrayFoto)
    }
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.GetContent()
//    ) { uri ->
//        uri?.let {
//            fotoUri = it
//            selectedMedia = selectedMedia + listOf(it)
//            viewModelDoPostAnuncio.imagensJaselecionadas = selectedMedia
//        }
//    }


    //REFERENCIA PARA ACESSO E MANiPULACAO DO CLOUD STORAGE
    val storageRef: StorageReference = FirebaseStorage.getInstance().reference.child("images")

    //REFERENCIA PARA ACESSO E MANIPULACAO DO CLOUD FIRESTORE
    val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    var fotoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(context).data(fotoUri).build()
    )

    var isImageSelected by remember { mutableStateOf(false) }

    var anexo by remember {
        mutableStateOf(viewModelDoPostAnuncio.imagensCertas)
    }

    /*fun urlDownload(it: String) {
        val storageRefs: StorageReference =
            FirebaseStorage.getInstance().reference.child("teste/${Uri.parse(it)}")


        val uploadTask = storageRefs.putFile(Uri.parse(it))

        uploadTask
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {
                    Log.i(
                        "urlDown",
                        "it: ${it}"
                    )
                    storageRefs.downloadUrl.addOnSuccessListener { uri ->
                        val map = HashMap<String, Any>()
                        map["pic"] = uri.toString()
                        Log.i(
                            "urlDown",
                            "uri: ${uri}"
                        )
                        Log.i(
                            "urlDown",
                            "it: ${it}"
                        )

                        firebaseFirestore
                            .collection("images")
                            .add(map)
                            .addOnCompleteListener { firestoreTask ->
                                if (firestoreTask.isSuccessful) {

                                    val anexo = uri.toString()
                                    selectedMediaUri += uri
                                    Log.i(
                                        "urlDown",
                                        "selectedMediaUrl: ${selectedMediaUri}"
                                    )

                                } else {
                                    Toast
                                        .makeText(
                                            context,
                                            "ERRO AO TENTAR REALIZAR O UPLOAD",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            }
                    }

                } else {
                    Toast
                        .makeText(
                            context,
                            "ERRO AO TENTAR REALIZAR O UPLOAD",
                            Toast.LENGTH_SHORT
                        )
                        .show()

                }
            }

    }*/

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
           fotoUri = uri

            if(fotoSelecionado.size < 3){
                val storageReference: StorageReference =
                    FirebaseStorage.getInstance().reference.child("images")

                val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

                var imagemUrl: String = ""

                val storageRef = storageReference.child(System.currentTimeMillis().toString())
                storageRef.putFile(fotoUri!!).addOnCompleteListener { task ->
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
                                                imagemUrl =
                                                    querySnapshot.documents[0].data!!["pic"].toString()

                                                fotoSelecionado = fotoSelecionado + imagemUrl
                                            }
                                            .addOnFailureListener { exception ->
                                                Log.e(
                                                    "Firebase",
                                                    "Erro ao obter imagens: $exception"
                                                )
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
        }
    }

    val maxImageCount = 3

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderUpdateAnnounce {
            navController.popBackStack()
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Edite sua imagem agora, onde para adicionar é só clicar na imagem e trocar é apenas clicar en cima da imagem que deseja trocar",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF2A2929)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Insira imagens do livro:",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF2A2929)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.photo),
                    contentDescription = "",
                    modifier = Modifier
                        .size(128.dp)
                        .clickable {
                            if (fotoSelecionado.size < maxImageCount) {
                                launcher.launch("image/*")
                            }
                        }
                )
                Spacer(modifier = Modifier.height(48.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        items(fotoSelecionado) {
                            Column(
                                modifier = Modifier
                                    .height(260.dp)
                                    .width(160.dp)
                                    .background(Color.Transparent),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    model = it,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            fotoSelecionado = fotoSelecionado.filter { foto ->
                                                foto != it
                                            }
                                        }
                                )
                            }
                        }
                    }
                }
                if (fotoSelecionado.size >= maxImageCount) {
                    Toast.makeText(
                        context,
                        "Limite de $maxImageCount imagens atingido",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val pages = listOf<Int>(1, 2, 3,4)

                    for (page in pages) {
                        if (page == 3) {
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .width(4.dp)
                                    .height(4.dp)
                                    .background(color = Color(0xFFAA6231))
                            )
                        } else {
                            Card(
                                modifier = Modifier
                                    .width(4.dp)
                                    .height(4.dp),
                                backgroundColor = Color(0xFFC1BCCC),
                                shape = CircleShape,
                            ) {}
                        }
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.seta_prosseguir),
                    contentDescription = "",
                    modifier = Modifier
                        .size(72.dp)
                        .clickable {
                            if(fotoSelecionado.size == 3){
                                navController.navigate("editAnnounceFourth")
                            }else {
                                Toast
                                    .makeText(
                                        context,
                                        "Selecione ao menos 3 imagens para prosseguir",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }

                        }
                )
            }
        }
    }
}
