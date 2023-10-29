package br.senai.sp.jandira.s_book.components.sixth_create_announce.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.Storage
import br.senai.sp.jandira.s_book.components.universal.HeaderCreateAnnounce
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.EstadoLivroBaseResponse
import br.senai.sp.jandira.s_book.model.TipoAnuncio
import br.senai.sp.jandira.s_book.model.TipoAnuncioBaseResponse
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SixthCreateAnnounceScreen(
    navController: NavController,
    localStorage: Storage
){

    var listTipoAnuncio by remember{
        mutableStateOf(listOf<TipoAnuncio>())
    }

    var isChecked by remember {
        mutableStateOf(value = false)
    }

    var tiposSelecionados by remember {
        mutableStateOf<Set<String>>(emptySet())
    }

    var isVendaChecked by remember {
        mutableStateOf(false)
    }

    var vendaPriceState by remember {
        mutableStateOf("")
    }


    val context = LocalContext.current

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

        }
    })

    Column() {
        HeaderCreateAnnounce()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Perfeito! Agora informe que tipo de negociação você gostaria de fazer com o livro.",
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
                LazyColumn() {
                    items(listTipoAnuncio){
                        val isChecked = tiposSelecionados.contains(it.tipo)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = it.tipo,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.intermedium)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF808080),
                            )
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = {isChecked ->
                                    if (isChecked) {
                                        tiposSelecionados = tiposSelecionados + it.tipo
                                    } else {
                                        tiposSelecionados = tiposSelecionados - it.tipo
                                    }
                                    isVendaChecked = tiposSelecionados.contains("Venda")
                                    Log.e("thiago", "${tiposSelecionados}")
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
                            Text(
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
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
                    Card(
                        modifier = Modifier
                            .size(8.dp),
                        shape = CircleShape,
                        backgroundColor = Color(170, 98, 49, 255)
                    ) {}
                }
                Image(
                    painter = painterResource(id = R.drawable.seta_prosseguir),
                    contentDescription = "",
                    modifier = Modifier
                        .size(72.dp)
                        .clickable {
                            if (tiposSelecionados.isNotEmpty()) {
                                if (!isVendaChecked || (isVendaChecked && vendaPriceState.isNotBlank())) {
                                    navController.navigate("setimo_anunciar")

                                    localStorage.salvarValorString(context, vendaPriceState, "venda_price")

                                    val tiposSelecionadosString = tiposSelecionados.joinToString(", ")
                                    localStorage.salvarValorString(context = context, tiposSelecionadosString, "tipo_livro")
                                } else {
                                    Toast.makeText(context, "Gostaria de vender por qual preço é obrigatório quando 'Venda' está selecionado.", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(context, "Selecione pelo menos um tipo de anúncio.", Toast.LENGTH_SHORT).show()
                            }
                        }
                )
            }
        }
    }
}