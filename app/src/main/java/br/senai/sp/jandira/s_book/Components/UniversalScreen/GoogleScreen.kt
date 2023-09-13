package br.senai.sp.jandira.s_book.Components.UniversalScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun  GoogleScreen(){
    Row(
        modifier = Modifier
            . fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround

    ) {
        Surface(
            modifier = Modifier
                .border(width = 1.dp, color = Color(0xFFCECECE))
                .width(120.dp)
                .height(42.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(22.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    . fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(32.dp)
                )

                Text(
                    text = "Google",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF9F9898),
                    )
                )
            }

        }
        Surface(
            modifier = Modifier
                .border(width = 1.dp, color = Color(0xFFCECECE))
                .width(120.dp)
                .height(42.dp)
                .background(color = Color(0xFFFFFFFF))
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    . fillMaxWidth(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "image description",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(32.dp)
                )

                Text(
                    text = "Facebook",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF9F9898),
                    )
                )
            }

        }

    }
}