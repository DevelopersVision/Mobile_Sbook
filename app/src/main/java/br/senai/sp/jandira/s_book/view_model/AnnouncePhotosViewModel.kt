package br.senai.sp.jandira.s_book.view_model

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AnnouncePhotosViewModel: ViewModel() {
    var fotos: List<Uri>? = emptyList()
}