package br.senai.sp.jandira.s_book.components.donations.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.model.JsonAnuncioDoacao
import coil.compose.AsyncImage

@Composable
fun CardDonationsAnnounce(dados: JsonAnuncioDoacao, onClick: () -> Unit) {
    Log.e("Dados", "$dados")

    Column(
        modifier = Modifier
            .width(156.dp)
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0xFF000000),
                ambientColor = Color(0xFF000000)
            )
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 4.dp))
            .padding(horizontal = 12.dp, vertical = 12.dp)
            .clickable { onClick() },
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .width(130.dp)
                .height(150.dp),
            model = dados.foto[0].foto,
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            var shortDesc = dados.anuncio.descricao
            var titleList = dados.anuncio.nome.split("")
            var shortTitle = ""

            if (shortDesc.length > 30) {
                shortDesc = shortDesc.substring(0, 30).plus("...")
            }
            titleList.forEach { string ->
                if (titleList.indexOf(string) < 4) {
                    shortTitle += "$string "
                } else if (titleList.indexOf(string) == 4) {
                    shortTitle += "..."
                }
            }
            Text(
                text = shortDesc,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000)
                )
            )
            Text(
                text = "${dados.autores[0].nome} | ${dados.anuncio.ano_lancamento}",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = FontFamily(Font(R.font.intermedium)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF9F9898)
                )
            )
        }
    }
}