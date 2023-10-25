package br.senai.sp.jandira.s_book.components.second_create_announce.components

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownEditora() {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var editoraState by remember {
        mutableStateOf(value = "")
    }

    var listAutor = listOf("thiago", "luis", "felipe")

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            OutlinedTextField(
                value = editoraState,
                onValueChange = {
                    editoraState = it
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
                        text = "Qual a editora do livro?",
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
                modifier = Modifier.fillMaxWidth(),
            ) {
                if (listAutor.isNotEmpty()) {
                    listAutor.forEach {
                        DropdownMenuItem(
                            text = { Text(text = "Thiago Freitas", color = Color.Black) },
                            onClick = {
                                isExpanded = false
                            }
                        )
                    }
                } else {
                    listAutor.forEach {
                        DropdownMenuItem(
                            text = { Text(text = "Thiago Freitas", color = Color.Black) },
                            onClick = {
                                isExpanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}