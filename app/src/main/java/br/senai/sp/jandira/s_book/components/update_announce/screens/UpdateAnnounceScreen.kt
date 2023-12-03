package br.senai.sp.jandira.s_book.components.update_announce.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.update_announce.components.DropDownAutor
import br.senai.sp.jandira.s_book.components.update_announce.components.DropDownIdioma
import br.senai.sp.jandira.s_book.components.update_announce.components.HeaderUpdateAnnounce
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2

@Composable
fun UpdateAnnounceScreen(
    viewModelV2: AnuncioViewModelV2
) {

    var nomeState by remember {
        mutableStateOf("")
    }

    var sinopseState by remember {
        mutableStateOf("")
    }

    var autorState by remember {
        mutableStateOf("")
    }

    var numeroPaginaState by remember {
        mutableStateOf("")
    }

    var newAutorState by remember {
        mutableStateOf("")
    }

    var anoState by remember {
        mutableStateOf("")
    }

    var edicaoState by remember {
        mutableStateOf("")
    }

    var isbnState by remember {
        mutableStateOf("")
    }

    var idiomaState by remember {
        mutableStateOf("")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(1) {
            HeaderUpdateAnnounce {}
            Text(
                modifier = Modifier.width(300.dp),
                text = "Bem-vindo ao atualizar anúncio do livro! Comece a mudar as informações do livro.",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(700),
                    color = Color(0xFF2A2929)
                )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 16.dp)
            ) {
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
                            text = "Nome:",
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
                DropDownAutor(viewModelDadosLivros = viewModelV2, autorState){
                    autorState = it
                }
                if(autorState == "Outro"){
                    OutlinedTextField(
                        value = newAutorState,
                        onValueChange = {
                            newAutorState = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp),
                        label = {
                            Text(
                                text = "Novo autor:",
                                fontSize = 16.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF2A2929)
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = colorResource(id = R.color.cinza),
                            unfocusedBorderColor = colorResource(id = R.color.cinza)
                        )
                    )
                }
                OutlinedTextField(
                    value = sinopseState,
                    onValueChange = {
                        sinopseState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),
                    label = {
                        Text(
                            text = "Sinopse:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF2A2929)
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.cinza),
                        unfocusedBorderColor = colorResource(id = R.color.cinza)
                    )
                )
                OutlinedTextField(
                    value = numeroPaginaState,
                    onValueChange = {
                        numeroPaginaState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    label = {
                        Text(
                            text = "Número de páginas:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF2A2929)
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.cinza ),
                        unfocusedBorderColor = colorResource(id = R.color.cinza )
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                OutlinedTextField(
                    value = anoState,
                    onValueChange = { anoState = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    label = {
                        Text(
                            text = "Ano de lançamento:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF2A2929)
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.cinza),
                        unfocusedBorderColor = colorResource(id = R.color.cinza)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                OutlinedTextField(
                    value = edicaoState,
                    onValueChange = {
                        edicaoState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    label = {
                        Text(
                            text = "Edição:",
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
                OutlinedTextField(
                    value = isbnState,
                    onValueChange = {
                        isbnState = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    label = {
                        Text(
                            text = "ISBN:",
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
                DropDownIdioma(viewModelDadosLivros = viewModelV2, idiomaState = idiomaState){
                    idiomaState = it
                }
            }
        }
    }
}