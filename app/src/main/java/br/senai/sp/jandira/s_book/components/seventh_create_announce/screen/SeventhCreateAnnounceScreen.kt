package br.senai.sp.jandira.s_book.components.seventh_create_announce.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.Storage
import br.senai.sp.jandira.s_book.components.universal.HeaderCreateAnnounce

@Composable
fun SeventhCreateAnnounceScreen(
    localStorage: Storage
){

    val context = LocalContext.current

    val nomeLivro = localStorage.lerValorString(context = context, "nome_livro")
    val sinopseLivro = localStorage.lerValorString(context = context, "sinopse_livro")
    val numeroLivro = localStorage.lerValorString(context = context, "numero_livro")
    val anoLivro = localStorage.lerValorString(context = context, "ano_livro")
    val edicaoLivro = localStorage.lerValorString(context = context, "edicao_livro")
    val isbnLivro = localStorage.lerValorString(context = context, "isbn_livro")

    Column(modifier = Modifier.verticalScroll(ScrollState(0))) {
        HeaderCreateAnnounce()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Pronto! Agora só revisar e criar!",
                fontSize = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF2A2929)
            )
            Spacer(modifier = Modifier.height(64.dp))
            Image(
                painter = painterResource(id = R.drawable.diario),
                contentDescription = "",
                modifier = Modifier.size(245.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Card(
                    modifier = Modifier
                        .size(8.dp),
                    shape = CircleShape,
                    backgroundColor = Color(92, 44, 12, 255)
                ) {}
                Card(
                    modifier = Modifier
                        .size(8.dp)
                        .border(width = 1.dp, color = Color(0xFF000000), shape = CircleShape),
                    shape = CircleShape,
                    backgroundColor = Color.Transparent
                ) {}
                Card(
                    modifier = Modifier
                        .size(8.dp)
                        .border(width = 1.dp, color = Color(0xFF000000), shape = CircleShape),
                    shape = CircleShape,
                    backgroundColor = Color.Transparent
                ) {}
                Card(
                    modifier = Modifier
                        .size(8.dp)
                        .border(width = 1.dp, color = Color(0xFF000000), shape = CircleShape),
                    shape = CircleShape,
                    backgroundColor = Color.Transparent
                ) {}
            }
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 6.dp,
                        spotColor = Color(0xFF000000),
                        ambientColor = Color(0xFF000000),
                        shape = RoundedCornerShape(8.dp)
                    ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)) {
                    Text(
                        text = "$nomeLivro",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF404040)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(0.8.dp),
                        color = Color(0xFF808080)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Ficção Científica, Romance, Comédia, Suspense",
                        fontSize = 14.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF808080)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "R$35,00",
                        fontSize = 24.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF404040),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.susanna_profile),
                            contentDescription = "",
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Max Kellerman",
                                fontSize = 20.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF000000)
                            )
                            Text(
                                text = "Carapícuiba, São Paulo",
                                fontSize = 12.sp,
                                fontWeight = FontWeight(600),
                                color = Color(0xFF9F9898)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Descrição",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "$sinopseLivro",
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF9F9898),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = "Especificações",
                fontSize = 20.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF000000),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth() ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Ano da edição",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000)
                )
                Text(
                    text = "2022",
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
            Spacer(modifier = Modifier.height(48.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(218, 108, 39, 255)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
            ) {
                Text(
                    text = "Anunciar",
                    fontSize = 14.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}