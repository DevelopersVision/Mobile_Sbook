package br.senai.sp.jandira.s_book.Components.CreateContScreen.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun BottonScreen(
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .width(300.dp)
            .height(48.dp)
            .background(Color(170, 98, 49), shape = RoundedCornerShape(size = 4.dp))
            .padding(start = 40.dp, top = 13.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(150.dp)
        ){
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                )
            )
        }
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = null,
                modifier = Modifier.size(21.dp)
            )
        }

    }


}


@Preview(showBackground = true)
@Composable
fun BottonScreenPreview() {
    BottonScreen(
        text = "Continuar",
        onClick = {}
    )
}