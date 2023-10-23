package br.senai.sp.jandira.s_book.components.edit_user.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.R

@Preview
@Composable
fun PhotoEdit() {
    val camera = R.drawable.camera

    Box(
        modifier = Modifier
            .size(100.dp),
        contentAlignment = Alignment.BottomEnd,
    ) {
        Card(
            modifier = Modifier
                .size(100.dp),
            shape = CircleShape
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Transparent)
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x40000000),
                        ambientColor = Color(0x40000000)
                    )
            )
        }
        Image(
            painterResource(id = camera),
            contentDescription = "",
            modifier = Modifier
                .size(28.dp),
        )
    }
}