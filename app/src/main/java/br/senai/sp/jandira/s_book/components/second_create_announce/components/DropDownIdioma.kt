package br.senai.sp.jandira.s_book.components.second_create_announce.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import br.senai.sp.jandira.s_book.model.Idioma
import br.senai.sp.jandira.s_book.model.IdiomaBaseResponse
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownIdioma(
    localStorage: Storage
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var idiomaState by remember {
        mutableStateOf(value = "")
    }

    var listIdioma by remember {
        mutableStateOf(listOf<Idioma>())
    }

    val context = LocalContext.current

    val call = RetrofitHelper.getIdiomasService().getIdiomas()

    // Executar a chamada
    call.enqueue(object : Callback<IdiomaBaseResponse> {
        override fun onResponse(
            call: Call<IdiomaBaseResponse>,
            response: Response<IdiomaBaseResponse>
        ) {
            listIdioma = response.body()!!.idiomas
        }


        override fun onFailure(call: Call<IdiomaBaseResponse>, t: Throwable) {
            // Log.d("API Call", "Depois da chamada da API: ${listAnuncios}")
        }
    })

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                value = idiomaState,
                onValueChange = {
                    idiomaState = it
                    isExpanded = true},
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
                        text = "Qual o idioma do livro?",
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
                listIdioma.forEach {
                    DropdownMenuItem(
                        text = { Text(text = it.nome, color = Color.Black) },
                        onClick = {
                            idiomaState = it.nome
                            isExpanded = false
                        }
                    )
                }
            }
        }
        localStorage.salvarValorString(context = context, idiomaState, "idioma_livro")
    }
}