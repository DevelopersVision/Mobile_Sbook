package br.senai.sp.jandira.s_book.components.edit_user.components


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.R
import coil.compose.AsyncImage

@Composable
fun PhotoEdit(
    foto: String
) {
    val camera = R.drawable.camera

    var fotoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var status by remember {
        mutableStateOf(false)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri.let {
            status = true

            fotoUri = it
        }
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .clickable {
                launcher.launch("image/*")
            },
        contentAlignment = Alignment.BottomEnd,
    ) {
        Card(
            modifier = Modifier
                .size(100.dp),
            shape = CircleShape
        ) {
            AsyncImage(
                model = if(status){fotoUri}else{foto},
                contentDescription = "",
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Transparent)
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    ),
                contentScale = ContentScale.Crop
            )
        }
        Image(
            painterResource(id = camera),
            contentDescription = "",
            modifier = Modifier
                .size(28.dp),
        )
    }
}