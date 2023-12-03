package br.senai.sp.jandira.s_book.components.update_announce.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.Storage
import br.senai.sp.jandira.s_book.model.AutorBaseResponse
import br.senai.sp.jandira.s_book.model.Autores
import br.senai.sp.jandira.s_book.model.AutoresParaPostAnuncio
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import br.senai.sp.jandira.s_book.view_model.ViewModelDosAutores
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownAutor(
    viewModelDadosLivros: AnuncioViewModelV2,
    autorState: Autores,
    onAutorChange: (Autores) -> Unit
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var listAutor by remember {
        mutableStateOf(listOf<Autores>())
    }

    var arrayDeAutores  by remember{
        mutableStateOf(listOf<AutoresParaPostAnuncio>())
    }

    val call = RetrofitHelper.getAutoresService().getAutores()

    // Executar a chamada
    call.enqueue(object : Callback<AutorBaseResponse> {
        override fun onResponse(
            call: Call<AutorBaseResponse>,
            response: Response<AutorBaseResponse>
        ) {
            listAutor = response.body()!!.autores
            listAutor = listAutor + Autores(
                id = 1000,
                nome = "Outro"
            )
        }


        override fun onFailure(call: Call<AutorBaseResponse>, t: Throwable) {
            // Log.d("API Call", "Depois da chamada da API: ${listAnuncios}")
        }
    })

    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                value = autorState.nome,
                onValueChange = {
                    isExpanded = true
                                },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .height(60.dp),
                label = {
                    Text(
                        text = "Qual o autor do livro?",
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

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.background(Color.White),
            ) {
                listAutor.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it.nome, color = Color.Black) },
                        onClick = {
                            onAutorChange(it)
                            isExpanded = false
                        }
                    )
                }
            }
        }

    }
}