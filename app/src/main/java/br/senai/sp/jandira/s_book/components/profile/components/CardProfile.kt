package br.senai.sp.jandira.s_book.components.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.universal.ButtonProfile

@Preview
@Composable
fun CardProfile() {

    var userRating by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0xFF000000),
                ambientColor = Color(0xFF000000)
            )
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(size = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
//            AsyncImage(
//                model = "https://photografos.com.br/wp-content/uploads/2020/09/fotografia-para-perfil.jpg",
//                contentDescription = "Foto de perfil",
//                modifier = Modifier.size(90.dp)
//            )
                Image(
                    painter = painterResource(id = R.drawable.susanna_profile),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillBounds
                )
                Column()  {
                    Text(
                        text = "Maria Joaquina",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFFDDA35D)
                    )
                    Text(
                        text = "mariajo@gmail.com",
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF808080)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    br.senai.sp.jandira.s_book.components.perfil.components.RatingBar(
                        maxRating = 5,
                        initialRating = userRating
                    )
                }
            }
        ButtonProfile("Editar Conta")
        }
    }
}

@Composable
fun RatingBar(maxRating: Int = 5, initialRating: Int = 0) {
    var currentRating by remember { mutableStateOf(initialRating) }

    Row {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= currentRating) Icons.Default.Star else Icons.Default.Star,
                contentDescription = null, // Pode ser null neste caso
                tint = if (i <= currentRating) Color.Yellow else Color.Gray,
                modifier = Modifier
                    .clickable {
                        currentRating = i
                    }
                    .width(25.dp)
                    .height(25.dp)
            )
        }
    }
}