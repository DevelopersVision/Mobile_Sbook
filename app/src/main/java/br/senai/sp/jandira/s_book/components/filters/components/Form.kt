package br.senai.sp.jandira.s_book.components.filters.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R


@Composable
fun Form(
    navController: NavController
){
    var isChecked by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
    ) {
        ComponentsFiltro(
            text = "Gênero",
            icon = painterResource(
                id = R.drawable.book
            ),
            onclick = {
                navController.navigate("filterGenero")
            }
        )
        ComponentsFiltro(
            text = "Localização",
            icon = painterResource(
                id = R.drawable.localizar
            ),
            onclick = {
                navController.navigate("filter_localizacao")
            }
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.book),
                contentDescription = "",
                modifier = Modifier
                    .width(24.dp)
                    .height(19.dp),
                tint = Color(0xFF000000)
            )
            Text(
                text = "Novo",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                horizontalArrangement = Arrangement.End
            ){
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                    }
                )
                val selectedOptions = mutableListOf<String>()

                if(isChecked == true){


                    if (selectedOptions.contains("Novo")) {
                        selectedOptions.remove("Novo")
                    } else {
                        selectedOptions.add("Novo")
                    }

                    Log.e("AaBatatatatatatatatatatatatatata", "Array: ${selectedOptions}")

                }
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.9.dp)
                .background(color = Color(0xFFE0E0E0))
        ) {}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.seminovo),
                contentDescription = "",
                modifier = Modifier
                    .width(24.dp)
                    .height(19.dp),
                tint = Color(0xFF000000)
            )
            Text(
                text = "Seminovo",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                horizontalArrangement = Arrangement.End
            ){
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                    }
                )
                val selectedOptions = mutableListOf<String>()

                if(isChecked == true){


                    if (selectedOptions.contains("Seminovo")) {
                        selectedOptions.remove("Seminovo")
                    } else {
                        selectedOptions.add("Seminovo")
                    }

                    Log.e("AaBatatatatatatatatatatatatatata", "Array: ${selectedOptions}")

                }
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.9.dp)
                .background(color = Color(0xFFE0E0E0))
        ) {}
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .padding(start = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.usado),
                contentDescription = "",
                modifier = Modifier
                    .width(24.dp)
                    .height(19.dp),
                tint = Color(0xFF000000)
            )
            Text(
                text = "Usado",
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(500),
                color = Color(0xFF000000),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp),
                horizontalArrangement = Arrangement.End
            ){
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        isChecked = it
                    }
                )
                val selectedOptions = mutableListOf<String>()

                if(isChecked == true){


                    if (selectedOptions.contains("Usado")) {
                        selectedOptions.remove("Usado")
                    } else {
                        selectedOptions.add("Usado")
                    }

                    Log.e("AaBatatatatatatatatatatatatatata", "Array: ${selectedOptions}")

                }
            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.9.dp)
                .background(color = Color(0xFFE0E0E0))
        ) {}
        ComponentsFiltro(
            text = "Ano de publicação",
            icon = painterResource(
                id = R.drawable.calendario
            ),
            onclick = {
                navController.navigate("filter_ano")
            }
        )
        ComponentsFiltro(
            text = "Idioma",
            icon = painterResource(
                id = R.drawable.idioma
            ),
            onclick = {
                navController.navigate("filter_idioma")
            }
        )
    }
}