package br.senai.sp.jandira.s_book.components.update_announce.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.update_announce.components.DropDownAutor
import br.senai.sp.jandira.s_book.components.update_announce.components.DropDownEditora
import br.senai.sp.jandira.s_book.components.update_announce.components.DropDownIdioma
import br.senai.sp.jandira.s_book.components.update_announce.components.HeaderUpdateAnnounce
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2

@Composable
fun UpdateAnnounceFirstScreen(
    viewModelV2: AnuncioViewModelV2,
    navController: NavController
) {

    val context = LocalContext.current

    var nomeState by remember {
        mutableStateOf(viewModelV2.dadosAnuncio.anuncio.nome)
    }

    var sinopseState by remember {
        mutableStateOf(viewModelV2.dadosAnuncio.anuncio.descricao)
    }

    var autorState by remember {
        mutableStateOf(viewModelV2.dadosAnuncio.autores[0])
    }

    var numeroPaginaState by remember {
        mutableStateOf(viewModelV2.dadosAnuncio.anuncio.numero_paginas)
    }

    var newAutorState by remember {
        mutableStateOf("")
    }

    var anoState by remember {
        mutableStateOf(viewModelV2.dadosAnuncio.anuncio.ano_lancamento)
    }

    var edicaoState by remember {
        mutableStateOf(viewModelV2.dadosAnuncio.anuncio.edicao)
    }

    var isbnState by remember {
        mutableStateOf(viewModelV2.dadosAnuncio.anuncio.isbn)
    }

    var idiomaState by remember {
        mutableStateOf(viewModelV2.dadosAnuncio.idioma)
    }

    var editoraState by remember {
        mutableStateOf(viewModelV2.dadosAnuncio.editora)
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(1) {
            HeaderUpdateAnnounce {
                navController.popBackStack()
            }
            Text(
                modifier = Modifier
                    .width(350.dp)
                    .padding(horizontal = 30.dp, vertical = 4.dp),
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
                    .padding(horizontal = 30.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
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
                        focusedBorderColor = colorResource(id = R.color.cinza),
                        unfocusedBorderColor = colorResource(id = R.color.cinza)
                    )
                )

                DropDownAutor(viewModelDadosLivros = viewModelV2, autorState) {
                    autorState = it
                }

                if (autorState.nome == "Outro") {
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
                    value = numeroPaginaState.toString(),
                    onValueChange = {
                        numeroPaginaState = it.toInt()
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
                        focusedBorderColor = colorResource(id = R.color.cinza),
                        unfocusedBorderColor = colorResource(id = R.color.cinza)
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )

                OutlinedTextField(
                    value = anoState.toString(),
                    onValueChange = { anoState = it.toInt() },
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
                        focusedBorderColor = colorResource(id = R.color.cinza),
                        unfocusedBorderColor = colorResource(id = R.color.cinza)
                    )
                )

                DropDownEditora(viewModelV2 = viewModelV2, editoraState = editoraState) {
                    editoraState = it
                }

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
                        focusedBorderColor = colorResource(id = R.color.cinza),
                        unfocusedBorderColor = colorResource(id = R.color.cinza)
                    )
                )

                DropDownIdioma(viewModelDadosLivros = viewModelV2, idiomaState = idiomaState) {
                    idiomaState = it
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val pages = listOf<Int>(1, 2, 3,4)

                        for (page in pages) {
                            if (page == 1) {
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
                                if (
                                    nomeState.isNotEmpty() && sinopseState.isNotEmpty() &&
                                    autorState.nome.isNotEmpty() && numeroPaginaState
                                        .toString()
                                        .isNotEmpty() &&
                                    anoState
                                        .toString()
                                        .isNotEmpty() && edicaoState.isNotEmpty() &&
                                    isbnState.isNotEmpty() && idiomaState.nome.isNotEmpty() && editoraState.nome.isNotEmpty()
                                ) {
                                    viewModelV2.dadosAnuncio.anuncio.nome = nomeState
                                    viewModelV2.dadosAnuncio.anuncio.descricao = sinopseState
                                    viewModelV2.dadosAnuncio.autores[0].id = autorState.id
                                    viewModelV2.dadosAnuncio.autores[0].nome = autorState.nome
                                    viewModelV2.dadosAnuncio.anuncio.numero_paginas =
                                        numeroPaginaState
                                    viewModelV2.dadosAnuncio.anuncio.ano_lancamento = anoState
                                    viewModelV2.dadosAnuncio.anuncio.edicao = edicaoState
                                    viewModelV2.dadosAnuncio.anuncio.isbn = isbnState
                                    viewModelV2.dadosAnuncio.idioma = idiomaState
                                    viewModelV2.dadosAnuncio.editora = editoraState
                                    navController.navigate("editAnnounceSecond")
                                } else {
                                    Toast
                                        .makeText(
                                            context,
                                            "Preencha todos os campos antes de prosseguir",
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
}