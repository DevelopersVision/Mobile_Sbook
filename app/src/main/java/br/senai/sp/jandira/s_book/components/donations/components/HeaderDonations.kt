package br.senai.sp.jandira.s_book.components.donations.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R

@Composable
fun HeaderDonations(
    onclick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(73.dp)
                .padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow_left_gray),
                contentDescription = "",
                modifier = Modifier
                    .size(35.dp)
                    .clickable {
                        onclick()
                    }
            )
            Text(
                text = "Doação",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight(700),
                fontFamily = FontFamily(Font(R.font.intermedium))
            )
            Image(
                painter = painterResource(id = R.drawable.book_opaque),
                contentDescription = "",
                modifier = Modifier.size(35.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(0.8.dp)
                .background(color = Color(0xFF000000))
        ) {}
    }

}