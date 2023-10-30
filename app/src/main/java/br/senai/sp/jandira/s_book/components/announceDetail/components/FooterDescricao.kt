package br.senai.sp.jandira.s_book.components.announceDetail.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel


@Composable
fun FooterDescricao(viewMODEL: AnuncioViewModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.Top),
    ) {
        Text(
            text = "Descrição",
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.intermedium)),
            fontWeight = FontWeight(600),
            color = Color(0xFF000000),
        )
        Text(
            text = "${viewMODEL.descricao}",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.intermedium)),
            fontWeight = FontWeight(400),
            color = Color(0xFF9F9898),
        )
        Column(
            modifier = Modifier
                .width(350.dp)
                .height(200.dp),
        ) {

            Text(
                text = "Especificações",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
            )
            Spacer(modifier = Modifier.height(24.dp))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth() ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(145.dp),
                ) {
                    Text(
                        text = "Ano",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000)
                    )
                    Text(
                        text = "${viewMODEL.ano_edicao}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(126, 125, 122, 255)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.8.dp),
                    color = Color(0xFFE0E0E0)
                )
            }

            // segundo componente
            Spacer(modifier = Modifier.height(6.dp))
            Column{
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(137.dp),
                ) {
                    Text(
                        text = "Autor",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000)
                    )
                    LazyRow() {
                        items(viewMODEL.autor) {
                            Text(
                                text = "${it.nome},",
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.intermedium)),
                                fontWeight = FontWeight(400),
                                color = Color(0xFF7E7D7A)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.8.dp),
                    color = Color(0xFFE0E0E0)
                )
            }

            //terceiro componente
            Spacer(modifier = Modifier.height(6.dp))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(127.dp),
                ) {
                    Text(
                        text = "Editora",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000)
                    )
                    Text(
                        text = "${viewMODEL.editora?.nome}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(126, 125, 122, 255)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.8.dp),
                    color = Color(0xFFE0E0E0)
                )
            }

            //ultimo componente
            Spacer(modifier = Modifier.height(6.dp))
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(128.dp),
                ) {
                    Text(
                        text = "Idioma",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000)
                    )
                    Text(
                        text = "${viewMODEL.idioma?.nome}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(126, 125, 122, 255)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.8.dp),
                    color = Color(0xFFE0E0E0)
                )
            }
        }
    }
}

//Column {
//                Row(
//                    modifier = Modifier.fillMaxWidth() ,
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.spacedBy(128.dp),
//                ) {
//                    Text(
//                        text = "Idioma",
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight(600),
//                        color = Color(0xFF000000)
//                    )
//                    Text(
//                        text = "${viewMODEL.idioma?.nome}",
//                        fontSize = 14.sp,
//                        fontWeight = FontWeight(400),
//                        color = Color(126, 125, 122, 255)
//                    )
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//                Divider(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(0.8.dp),
//                    color = Color(0xFFE0E0E0)
//                )
//            }

//Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(20.dp),
//                horizontalArrangement = Arrangement.spacedBy(80.dp),
//                verticalAlignment = Alignment.Top,
//            ) {
//                Text(
//                    text = "Ano da edição",
//                    fontSize = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.intermedium)),
//                    fontWeight = FontWeight(600),
//                    color = Color(0xFF000000),
//                )
//                Text(
//                    text = "${viewMODEL.ano_edicao}",
//                    fontSize = 14.sp,
//                    fontFamily = FontFamily(Font(R.font.intermedium)),
//                    fontWeight = FontWeight(400),
//                    color = Color(0xFF7E7D7A)
//                )
//            }
//            Column(
//                modifier = Modifier
//                    .padding(0.dp)
//                    .width(320.dp)
//                    .height(0.8.dp)
//                    .background(color = Color(0xFFE0E0E0))
//            ) {}