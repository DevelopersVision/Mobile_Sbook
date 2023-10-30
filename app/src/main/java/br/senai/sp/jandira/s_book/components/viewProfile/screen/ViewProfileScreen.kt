package br.senai.sp.jandira.s_book.components.viewProfile.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.universal.HeaderProfile
import br.senai.sp.jandira.s_book.components.viewProfile.components.HeaderViewProfile

@Preview
@Composable
fun SecondCreateAnnounceScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .height(340.dp)
                .fillMaxWidth(),
            backgroundColor = Color(94, 61, 39, 255),
            shape = RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp)
        ) {
            HeaderViewProfile {}
            Row {
                Image(
                    painter = painterResource(id = R.drawable.susanna_profile),
                    contentDescription = "",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
                Column {
                    Text(
                        text = "Maria Joaquina",
                        fontSize = 20.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF)
                    )

                }
            }
        }
    }
}