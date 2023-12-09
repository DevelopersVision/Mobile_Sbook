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
import br.senai.sp.jandira.s_book.view_model.AnnouncePhotosViewModel
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import br.senai.sp.jandira.s_book.view_model.ViewModelDoPostAnuncio
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

@Composable
fun UpdateAnnounceThirdScreen(
    navController: NavController,
    localStorage: Storage,
    viewModelDoPostAnuncio: ViewModelDoPostAnuncio,
    viewModelImagens: AnnouncePhotosViewModel,
    viewModelV2: AnuncioViewModelV2
    ) {

    val context = LocalContext.current

    val storageRef: StorageReference = FirebaseStorage.getInstance().reference.child("images")

    val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    var fotoUri by rememberSaveable {
        mutableStateOf<Uri?>(null)
    }

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(context).data(fotoUri).build()
    )

    var isImageSelected by remember { mutableStateOf(false) }

    var selectedMedia by rememberSaveable {
        mutableStateOf<List<Uri>>(emptyList())
    }

    var fotoSelecionado by rememberSaveable {
        mutableStateOf<List<Foto>>(viewModelV2.dadosAnuncio.foto)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            fotoUri = it
            selectedMedia = selectedMedia + listOf(it)
            viewModelDoPostAnuncio.imagensJaselecionadas = selectedMedia
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
                    text = "Edite sua imagem agora, onde para adicionar é só clicar na imagem e trocar é apenas  clicar ensima da imagem que deseja trocar",
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
                            if (selectedMedia.size + fotoSelecionado.size < maxImageCount) {
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
                            Log.e("Imagens", "$selectedMedia")
                            Column(
                                modifier = Modifier
                                    .height(260.dp)
                                    .width(160.dp)
                                    .background(Color.Transparent),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AsyncImage(
                                    model = it.foto,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            fotoSelecionado = fotoSelecionado.filter { foto ->
                                                foto.id != it.id
                                            }
                                        }
                                )
                            }
                        }
                        items(1){
                            for (imagem in selectedMedia){
                                Column(
                                    modifier = Modifier
                                        .height(260.dp)
                                        .width(160.dp)
                                        .background(Color.Transparent),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AsyncImage(
                                        model = imagem,
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clickable {
                                                selectedMedia = selectedMedia.filter { foto ->
                                                    foto != imagem
                                                }
                                            }
                                    )
                                }
                            }
                        }
                    }
                }
                if (selectedMedia.size + fotoSelecionado.size >= maxImageCount) {
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
                            if (selectedMedia.size >= 3) {
                                val uploadedImageUrls =
                                    mutableListOf<String>() // Para armazenar as URLs das imagens

                                for (uri in selectedMedia) {
                                    val storageRef =
                                        storageRef.child("${uri.lastPathSegment}-${System.currentTimeMillis()}.jpg")
                                    storageRef
                                        .putFile(uri)
                                        .addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                                                    val imageUrl = downloadUri.toString()
                                                    uploadedImageUrls.add(imageUrl)

                                                    if (uploadedImageUrls.size == selectedMedia.size) {
                                                        // Todas as imagens foram carregadas, agora atualize a ViewModel
                                                        viewModelImagens.fotos = uploadedImageUrls
                                                        navController.navigate("quarto_anunciar")
                                                        localStorage.salvarValorString(
                                                            context = context,
                                                            uploadedImageUrls.toString(),
                                                            "foto_livro"
                                                        )
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
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Selecione ao menos 3 imagens para prosseguir",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                            }
                                        }
                                }
                            }else if(fotoSelecionado.size == 3){
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
