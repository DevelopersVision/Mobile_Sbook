package br.senai.sp.jandira.s_book.components.first_create_announce.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.Storage
import br.senai.sp.jandira.s_book.components.first_create_announce.components.DropDownAutor
import br.senai.sp.jandira.s_book.components.universal.HeaderCreateAnnounce

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstCreateAnnounceScreen(
    navController: NavController,
    localStorage: Storage
){

    var nomeState by remember {
        mutableStateOf(value = "")
    }

    var sinopseState by remember {
        mutableStateOf(value = "")
    }

    val context = LocalContext.current

    Column() {
        HeaderCreateAnnounce()
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
                    text = "Bem-vindo ao anúncio do livro! Comece inserindo as informações básicas do livro.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF2A2929)
                )
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    value = nomeState,
                    onValueChange = {
                        nomeState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    label = {
                        Text(
                            text = "Digite o nome:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF2A2929)
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.cinza ),
                        unfocusedBorderColor = colorResource(id = R.color.cinza )
                    )
                )
                Spacer(modifier = Modifier.height(32.dp))
                DropDownAutor(localStorage)
                Spacer(modifier = Modifier.height(32.dp))
                OutlinedTextField(
                    value = sinopseState,
                    onValueChange = {
                        sinopseState = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    label = {
                        Text(
                            text = "Digite a sinopse:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF2A2929)
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.cinza ),
                        unfocusedBorderColor = colorResource(id = R.color.cinza )
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(170, 98, 49, 255)
                    ) {}
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(193, 188, 204, 255)
                    ) {}
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(193, 188, 204, 255)
                    ) {}
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(193, 188, 204, 255)
                    ) {}
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(193, 188, 204, 255)
                    ) {}
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(193, 188, 204, 255)
                    ) {}
                }
                Image(
                    painter = painterResource(id = R.drawable.seta_prosseguir),
                    contentDescription = "",
                    modifier = Modifier
                        .size(72.dp)
                        .clickable {
                            if (nomeState.isNotEmpty() && sinopseState.isNotEmpty()) {
                                navController.navigate("segundo_anunciar")
                                localStorage.salvarValorString(context = context, nomeState, "nome_livro")
                                localStorage.salvarValorString(context = context, sinopseState, "sinopse_livro")
                            } else {
                                Toast.makeText(context, "Preencha todos os campos antes de prosseguir", Toast.LENGTH_SHORT).show()
                            }
                        }
                )
            }
        }
    }
}