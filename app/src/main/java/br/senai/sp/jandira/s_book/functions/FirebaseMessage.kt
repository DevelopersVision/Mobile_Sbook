package br.senai.sp.jandira.s_book.functions

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

fun FirebaseMessage(imagem: Uri?, context: Context) {

    Log.e("entrou", "entrou", )

    val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child("images")

    val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    val fotoUri = imagem

    var imagemUrl: String = ""

    Log.e("Imagem", "$imagem", )

    val storageRef = storageReference.child("${fotoUri!!.lastPathSegment}-${System.currentTimeMillis()}.jpg")
    storageRef.putFile(fotoUri).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            storageRef.downloadUrl.addOnSuccessListener { downloadUri ->
                val map = hashMapOf("pic" to downloadUri.toString())
                firebaseFirestore.collection("images").add(map).addOnCompleteListener { firestoreTask ->
                    if (firestoreTask.isSuccessful) {
                        Log.e("Firebase", "Foto adicionada", )
                        Toast.makeText(context, "FOTO ADICIONADA COM SUCESSO", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.e("Firebase", "Erro, ${firestoreTask.result}", )
                        Toast.makeText(context, "ERRO AO TENTAR REALIZAR O UPLOAD", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else {
            Toast.makeText(context, "ERRO AO TENTAR REALIZAR O UPLOAD", Toast.LENGTH_SHORT).show()
        }
    }

    firebaseFirestore.collection("images")
        .get().addOnSuccessListener {
            for(i in it){
                if(it.last() == i){
                    imagemUrl = i.data["pic"].toString()

                    Log.e("URL", "$imagemUrl", )
                }
            }
        }

}