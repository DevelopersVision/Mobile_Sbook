package br.senai.sp.jandira.s_book.components.conversation_chat.screen

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.conversation_chat.components.HeaderPicture
import br.senai.sp.jandira.s_book.functions.FirebaseMessage
import br.senai.sp.jandira.s_book.model.chat.ChatClient
import br.senai.sp.jandira.s_book.model.chat.view_model.ChatViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import io.socket.client.Socket
import org.json.JSONObject


//@Preview(showSystemUi = true)
@Composable
fun PictureScreen(
    navController: NavController,
    chatViewModel: ChatViewModel,
    client: ChatClient,
    socket: Socket,
    idUsuario: Int,
) {

    val context = LocalContext.current

    var nome = chatViewModel.nome
    val idChat = chatViewModel.idChat
    val idUser2 = chatViewModel.idUser2

    var fotoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(context).data(fotoUri).build()
    )

    var imagem by remember {
        mutableStateOf("https://th.bing.com/th/id/OIP.94DLYHt0KpXQv1n4z-U6tgAAAA?rs=1&pid=ImgDetMain")
    }

    val storageRef: StorageReference = FirebaseStorage.getInstance().reference.child("chat")


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            fotoUri = it
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(235, 23, 23, 255))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderPicture()
            Spacer(modifier = Modifier.height(13.dp))
            Column(
                modifier = Modifier
                    .width(329.dp)
                    .background(Color(190, 183, 183, 255))
                    .clickable {
                        launcher.launch("image/*")
                        Log.e("foto", "$fotoUri",)
                        fotoUri?.let {
                           imagem = fotoUri.toString()
                        }
                    }
            ) {
                AsyncImage(
                    model = if(fotoUri == null){
                        imagem
                    }else{
                        fotoUri
                    },
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(329.dp)
                        .height(510.dp)
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(11.dp)
            ) {
                Text(
                    text = "Enviar para",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                    )
                Text(
                    text = nome,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp),
                horizontalAlignment =  Alignment.End,

            ) {
                Button(
                    onClick = {
                        val storageRefChild = storageRef.child("${System.currentTimeMillis()}_${fotoUri!!.lastPathSegment}")
                        val uploadTask = storageRefChild.putFile(fotoUri!!)

                        uploadTask.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                storageRefChild.downloadUrl.addOnSuccessListener { downloadUri ->
                                    // Agora você pode usar downloadUri para obter a URL da imagem no Firebase Storage
                                    val imageUrl = downloadUri.toString()

                                    val json = JSONObject().apply {
                                        put("messageBy", idUsuario)
                                        put("messageTo", idUser2)
                                        put("message", "")
                                        put("image", imageUrl)
                                        put("chatId", idChat)
                                    }

                                    client.sendMessage(json)
                                }
                            } else {
                                Log.e("PictureScreen", "Error uploading image to Firebase Storage: ${task.exception}")
                            }
                        }
                        navController.navigate("conversa_chat")
                    },
                    modifier = Modifier.size(60.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(Color(221, 163, 93, 255))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_send_24),
                        contentDescription = ""
                    )
                }
            }
        }

    }
}