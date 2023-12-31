package br.senai.sp.jandira.s_book.components.fifth_create_announce.screen

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
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.Storage
import br.senai.sp.jandira.s_book.components.universal.HeaderCreateAnnounce
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.EstadoLivro
import br.senai.sp.jandira.s_book.model.EstadoLivroBaseResponse
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.model.TipoAnuncio
import br.senai.sp.jandira.s_book.repository.CategoryList
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.ViewModelDosIds
import br.senai.sp.jandira.s_book.view_model.ViewModelDosTipoDeLivros
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun FifthCreateAnnounceScreen(
    navController: NavController,
    localStorage: Storage,
    viewModelDosIds: ViewModelDosIds
){

    var listEstadosLivro by remember{
        mutableStateOf(listOf<EstadoLivro>())
    }

    var isChecked by remember {
        mutableStateOf(value = false)
    }



    var estadosSelecionados by rememberSaveable {
        mutableStateOf(viewModelDosIds.estadosSelecionados)
    }

    val context = LocalContext.current

    val call = RetrofitHelper.getEstadoLivroService().getEstadoLivro()


    // Executar a chamada
    call.enqueue(object : Callback<EstadoLivroBaseResponse> {
        override fun onResponse(
            call: Call<EstadoLivroBaseResponse>,
            response: Response<EstadoLivroBaseResponse>
        ) {
            listEstadosLivro = response.body()!!.estados
        }


        override fun onFailure(call: Call<EstadoLivroBaseResponse>, t: Throwable) {

        }
    })

    Column() {
        HeaderCreateAnnounce(){ navController.popBackStack() }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Só mais um pouquinho... Informe as condições do livro.",
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
                LazyColumn {
                    items(listEstadosLivro){
                        val isChecked = estadosSelecionados.contains(it.estado)
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp)
                                .padding(horizontal = 16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = it.estado,
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.intermedium)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF808080),
                            )
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = {isChecked ->
                                    estadosSelecionados = setOf(it.estado).takeIf { isChecked } ?: emptySet()
                                    viewModelDosIds.id_estadoLivro = it.id
                                    viewModelDosIds.estadosSelecionados = setOf(it.estado).takeIf { isChecked } ?: emptySet()
                                    Log.e("thiago", "${estadosSelecionados}")
                                    val estadosSelecionadosString = estadosSelecionados.joinToString(", ")
                                    localStorage.salvarValorString(context = context, estadosSelecionadosString, "estado_livro")


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
                        backgroundColor = Color(170, 98, 49, 255)
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
                            if (estadosSelecionados.isNotEmpty()) {
                                navController.navigate("sexto_anunciar")
                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        "Selecione pelo menos um estado.",
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