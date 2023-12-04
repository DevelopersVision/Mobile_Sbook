package br.senai.sp.jandira.s_book.components.update_announce.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.update_announce.components.HeaderUpdateAnnounce
import br.senai.sp.jandira.s_book.model.AutorBaseResponse
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.EstadoLivroBaseResponse
import br.senai.sp.jandira.s_book.model.TipoAnuncio
import br.senai.sp.jandira.s_book.model.TipoAnuncioBaseResponse
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import br.senai.sp.jandira.s_book.view_model.ViewModelDosIds
import br.senai.sp.jandira.s_book.view_model.ViewModelDosTipoDeLivros
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateAnnounceSecondScreen(
    navController: NavController,
    viewModelV2: AnuncioViewModelV2,
    viewModelDosIds: ViewModelDosIds,
    viewModelDosTipoDeLivros: ViewModelDosTipoDeLivros,
) {
    val context = LocalContext.current

    var array = listOf<Int>()
    for (tipo in viewModelV2.dadosAnuncio.tipo_anuncio) {
        array = array + tipo.id
    }

    var listTipoAnuncio by remember {
        mutableStateOf(listOf<TipoAnuncio>())
    }
    var tiposSelecionados by rememberSaveable {
        mutableStateOf(viewModelDosTipoDeLivros.tiposSelecionados)
    }
    var arrayDosTiposDeAnuncio by remember {
        mutableStateOf(array)
    }

    var isVendaChecked by remember {
        mutableStateOf(false)
    }
    var vendaPriceState by remember {
        mutableStateOf("")
    }

    var listEstadosLivro by remember {
        mutableStateOf(listOf<EstadoLivro>())
    }
//    var estadosSelecionados by rememberSaveable {
//        mutableStateOf(viewModelDosIds.estadosSelecionados)
//    }


    viewModelDosIds.estadosSelecionados = setOf(viewModelV2.dadosAnuncio.estado_livro.estado)

    var estadosSelecionados by rememberSaveable {
        mutableStateOf(viewModelDosIds.estadosSelecionados)
    }


    val call = RetrofitHelper.getTipoAnuncioService().getTipoAnuncio()

    // Executar a chamada
    call.enqueue(object : Callback<TipoAnuncioBaseResponse> {
        override fun onResponse(
            call: Call<TipoAnuncioBaseResponse>,
            response: Response<TipoAnuncioBaseResponse>
        ) {
            listTipoAnuncio = response.body()!!.tipos
        }

        override fun onFailure(call: Call<TipoAnuncioBaseResponse>, t: Throwable) {
            Toast.makeText(context, "SERVIDOR FORA DO AR, TENTE MAIS TARDE", Toast.LENGTH_LONG)
                .show()
        }
    })

    val callEstado = RetrofitHelper.getEstadoLivroService().getEstadoLivro()


    // Executar a chamada
    callEstado.enqueue(object : Callback<EstadoLivroBaseResponse> {
        override fun onResponse(
            call: Call<EstadoLivroBaseResponse>,
            response: Response<EstadoLivroBaseResponse>
        ) {
            listEstadosLivro = response.body()!!.estados
        }

        override fun onFailure(call: Call<EstadoLivroBaseResponse>, t: Throwable) {
            Toast.makeText(context, "SERVIDOR FORA DO AR, TENTE MAIS TARDE", Toast.LENGTH_LONG)
                .show()
        }
    })

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(1) {
            HeaderUpdateAnnounce {
                navController.popBackStack()
            }
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text(
                    modifier = Modifier
                        .width(350.dp)
                        .padding(horizontal = 30.dp, vertical = 4.dp),
                    text = "Selecione a condição do livro ",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF2A2929)
                    )
                )
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    viewModelDosIds.id_estadoLivro = viewModelV2.dadosAnuncio.estado_livro.id
//                    viewModelDosIds.estadosSelecionados = setOf(viewModelV2.dadosAnuncio.estado_livro.estado)
//                    estadosSelecionados = setOf(viewModelV2.dadosAnuncio.estado_livro.estado)
                    listEstadosLivro.forEach { estadoLivro ->
                        val isChecked = estadosSelecionados.contains(estadoLivro.estado)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            androidx.compose.material3.Text(
                                text = estadoLivro.estado,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.intermedium)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF808080),
                            )
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { isChecked ->

//                                    Log.e("Check", "$estadosSelecionados")
//
//                                    if(isChecked){
//                                        if(estadosSelecionados.size == 1){
//                                            estadosSelecionados.dropLast(1)
//                                            estadosSelecionados = estadosSelecionados + estadoLivro.id
//                                        }else{
//                                            estadosSelecionados = estadosSelecionados + estadoLivro.id
//                                        }
//                                    }else{
//                                        estadosSelecionados.dropLast(1)
//                                    }
                                    estadosSelecionados =
                                        setOf(estadoLivro.estado).takeIf { isChecked } ?: emptySet()
                                    viewModelDosIds.id_estadoLivro = estadoLivro.id
                                    viewModelDosIds.estadosSelecionados =
                                        setOf(estadoLivro.estado).takeIf { isChecked } ?: emptySet()
                                }
                            )
                        }
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(0.8.dp),
                            color = Color(0xFFE0E0E0)
                        )
                    }
                }
            }
//            Column(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    modifier = Modifier
//                        .width(350.dp)
//                        .padding(horizontal = 30.dp, vertical = 4.dp),
//                    text = "Selecione o tipo de negociação",
//                    style = TextStyle(
//                        fontSize = 16.sp,
//                        fontFamily = FontFamily(Font(R.font.intermedium)),
//                        fontWeight = FontWeight(700),
//                        color = Color(0xFF2A2929)
//                    )
//                )
//                Column(
//                    Modifier.fillMaxWidth(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    viewModelDosIds.id_estadoLivro = viewModelV2.dadosAnuncio.estado_livro.id
//
//                    listTipoAnuncio.forEach { tipo ->
//                        val isChecked = tiposSelecionados.contains(tipo.tipo)
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(52.dp)
//                                .padding(horizontal = 16.dp),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            androidx.compose.material3.Text(
//                                text = tipo.tipo,
//                                fontSize = 14.sp,
//                                fontFamily = FontFamily(Font(R.font.intermedium)),
//                                fontWeight = FontWeight(500),
//                                color = Color(0xFF808080),
//                            )
//                            Checkbox(
//                                checked = isChecked,
//                                onCheckedChange = { isChecked ->
//                                    if (isChecked) {
//                                        tiposSelecionados = tiposSelecionados + tipo.tipo
//                                        viewModelDosTipoDeLivros.tiposDoAnuncio =
//                                            arrayDosTiposDeAnuncio.plus(tipo.id)
//                                    } else {
//                                        tiposSelecionados = tiposSelecionados - tipo.tipo
//                                        viewModelDosTipoDeLivros.tiposDoAnuncio =
//                                            arrayDosTiposDeAnuncio.minus(tipo.id)
//                                    }
//                                    isVendaChecked = tiposSelecionados.contains("Venda")
//                                }
//                            )
//                        }
//                        Divider(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(0.8.dp),
//                            color = Color(0xFFE0E0E0)
//                        )
//                    }
//                }
//                if (isVendaChecked) {
//                    OutlinedTextField(
//                        value = vendaPriceState,
//                        onValueChange = {
//                            vendaPriceState = it
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(60.dp)
//                            .padding(horizontal = 24.dp),
//                        label = {
//                            androidx.compose.material3.Text(
//                                text = "Gostaria de vender por qual preço?",
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight(500),
//                                color = Color(0xFF2A2929)
//                            )
//                        },
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            focusedBorderColor = colorResource(id = R.color.cinza),
//                            unfocusedBorderColor = colorResource(id = R.color.cinza)
//                        ),
//                        keyboardOptions = KeyboardOptions(
//                            keyboardType = KeyboardType.Number
//                        )
//                    )
//                }
//                Divider(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(0.8.dp),
//                    color = Color(0xFFE0E0E0)
//                )
//            }
        }
    }
}
