package br.senai.sp.jandira.s_book.components.meu_anuncio.screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation", "CoroutineCreationDuringComposition")
@OptIn(
    ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun AdOptionsScreen(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    onClick: () -> Unit
) {
    var context = LocalContext.current

    var isReplyMode by remember { mutableStateOf(false) }

    val keyboardController = LocalSoftwareKeyboardController.current

    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(425.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.bar_icon),
                contentDescription = "",
                Modifier.size(75.dp)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 45.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick()
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Excluir", color = Color.Red, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick()
                    }
                    .border(1.dp, Color.Black),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Encerrar Anuncio", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Normal, modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick()
                    }
                    .border(1.dp, Color.Black),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Editar", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Normal, modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 65.dp)
                    .clickable {
                        onClick()
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Cancelar", color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Normal, modifier = Modifier.padding(top = 15.dp, bottom = 15.dp))
            }
        }
    }
}
