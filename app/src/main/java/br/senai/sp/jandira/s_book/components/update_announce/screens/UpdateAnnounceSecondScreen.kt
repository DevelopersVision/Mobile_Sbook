package br.senai.sp.jandira.s_book.components.update_announce.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
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
import br.senai.sp.jandira.s_book.components.update_announce.components.HeaderUpdateAnnounce
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.EstadoLivroBaseResponse
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.TipoAnuncio
import br.senai.sp.jandira.s_book.model.TipoAnuncioBaseResponse
import br.senai.sp.jandira.s_book.repository.CategoryList
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import br.senai.sp.jandira.s_book.view_model.ViewModelDosGenerosSelecionados
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
    viewlModel: ViewModelDosGenerosSelecionados
) {
    val context = LocalContext.current


    var arrayGenero = listOf<Int>()
    for (genero in viewModelV2.dadosAnuncio.generos) {
        arrayGenero = arrayGenero + genero.id
    }

    viewlModel.selectedGeneros = arrayGenero
    var generosSelecionados by rememberSaveable {
        mutableStateOf(viewlModel.selectedGeneros)
    }

    var newGeneros by rememberSaveable {
        mutableStateOf(viewModelV2.dadosAnuncio.generos)
    }

    var arrayDeGeneros by remember {
        mutableStateOf(listOf<Int>())
    }

    var listGeneros by remember {
        mutableStateOf(listOf<Genero>())
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

    viewModelDosIds.id_estadoLivro = viewModelV2.dadosAnuncio.estado_livro.id
    viewModelDosIds.estadosSelecionados = setOf(viewModelV2.dadosAnuncio.estado_livro.estado)
    var estadosSelecionados by rememberSaveable {
        mutableStateOf(viewModelDosIds.estadosSelecionados)
    }

    Log.e("morriiii felipe", "${estadosSelecionados}", )



    var listTipoAnuncio by remember {
        mutableStateOf(listOf<TipoAnuncio>())
    }

    var array = listOf<Int>()
    for (tipo in viewModelV2.dadosAnuncio.tipo_anuncio) {
        array = array + tipo.id
    }

    viewModelDosTipoDeLivros.tiposDoAnuncio = array
    var tiposSelecionados by rememberSaveable {
        mutableStateOf(viewModelDosTipoDeLivros.tiposDoAnuncio)
    }

    var arrayDosTiposDeAnuncio by remember {
        mutableStateOf(listOf<Int>())
    }

    var newTipoAnuncio by remember {
        mutableStateOf(viewModelV2.dadosAnuncio.tipo_anuncio)
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

    val callGeneros = RetrofitHelper.getCategoryService().getGeneros()


    // Executar a chamada
    callGeneros.enqueue(object : Callback<CategoryList> {
        override fun onResponse(
            call: Call<CategoryList>,
            response: Response<CategoryList>
        ) {
            listGeneros = response.body()!!.dados
        }


        override fun onFailure(call: Call<CategoryList>, t: Throwable) {

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())

    ) {
        HeaderUpdateAnnounce {
            navController.popBackStack()
        }
        Column(
            Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .width(350.dp)
                    .padding(24.dp),
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
                listEstadosLivro.forEach { estadoLivro ->
                    val isChecked = estadosSelecionados.contains(estadoLivro.estado)
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFFE0E0E0)
                    )

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
                Spacer(modifier = Modifier.height(32.dp))
                androidx.compose.material3.Text(
                    text = "Selecione os gêneros para alterar no livro que foi criado",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF2A2929),
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(36.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.8.dp),
                    color = Color(0xFFE0E0E0)
                )
                val pairs = listGeneros
                for (it in pairs) {
                    val isChecked = generosSelecionados!!.contains(it.id)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        androidx.compose.material3.Text(
                            text = it.nome,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.intermedium)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF808080),
                        )
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { isChecked ->

                                if (isChecked) {

                                    newGeneros = newGeneros + it

                                    generosSelecionados = generosSelecionados!! + it.id

                                    arrayDeGeneros = arrayDeGeneros.plus(it.id)


                                } else {
                                    newGeneros = newGeneros - it

                                    generosSelecionados = generosSelecionados!! - it.id

                                    arrayDeGeneros = arrayDeGeneros.minus(it.id)

                                }

                                viewlModel.selectedGeneros = arrayDeGeneros
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

                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.8.dp),
                    color = Color(0xFFE0E0E0)
                )
                Spacer(modifier = Modifier.height(32.dp))
                androidx.compose.material3.Text(
                    text = "Perfeito! Agora você pode alterar o tipo de negociação você gostaria para o seu livro.",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF2A2929),
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
                Spacer(modifier = Modifier.height(36.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.8.dp),
                    color = Color(0xFFE0E0E0)
                )

                var pair = listTipoAnuncio
                for (it in pair) {
                    val isChecked3 = tiposSelecionados!!.contains(it.id)
                    //val isChecked4 = tiposSelecionados!!.contains(it.tipo)

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        androidx.compose.material3.Text(
                            text = it.tipo,
                            fontSize = 14.sp,
                            fontFamily = FontFamily(Font(R.font.intermedium)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF808080),
                        )
                        Checkbox(
                            checked = isChecked3,
                            onCheckedChange = { isChecked ->
                                if (isChecked) {
                                    when (it.id) {
                                        1 -> {
                                            // Se Doação está marcado, desmarca os outros
                                            tiposSelecionados = listOf(it.id)
                                            isVendaChecked = false
                                            vendaPriceState = ""
                                            newTipoAnuncio = listOf(it)
                                        }
                                        3,2 -> {
                                            // Se Troca ou Venda estão marcados, desmarca Doação
                                            tiposSelecionados = tiposSelecionados!!.filter { it != 1 }
                                            newTipoAnuncio = newTipoAnuncio.filter { type ->
                                                type.id != 1
                                            }
                                        }
                                    }
                                    newTipoAnuncio = newTipoAnuncio + it
                                    tiposSelecionados = tiposSelecionados!! + it.id
                                    viewModelDosTipoDeLivros.tiposDoAnuncio =
                                        arrayDosTiposDeAnuncio.plus(it.id)
                                } else {
                                    // Se Desmarcar, remove o tipo do conjunto
                                    tiposSelecionados = tiposSelecionados!! - it.id
                                    newTipoAnuncio = newTipoAnuncio - it
                                }
                                isVendaChecked = tiposSelecionados!!.contains(3)
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
                if (isVendaChecked) {
                    OutlinedTextField(
                        value = vendaPriceState,
                        onValueChange = {
                            vendaPriceState = it
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .padding(horizontal = 24.dp),
                        label = {
                            androidx.compose.material3.Text(
                                text = "Gostaria de vender por qual preço?",
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
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val pages = listOf<Int>(1, 2, 3,4)

                for (page in pages) {
                    if (page == 2) {
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

                        val estadoLivroNew = EstadoLivro(
                            id = viewModelDosIds.id_estadoLivro!!,
                            estado = estadosSelecionados.toString()
                        )
                        viewModelV2.dadosAnuncio.generos = newGeneros
                        viewModelV2.dadosAnuncio.tipo_anuncio = newTipoAnuncio
                        viewModelV2.dadosAnuncio.estado_livro = estadoLivroNew

                        navController.navigate("editAnnounceThird")
                    }
            )
        }

    }
}