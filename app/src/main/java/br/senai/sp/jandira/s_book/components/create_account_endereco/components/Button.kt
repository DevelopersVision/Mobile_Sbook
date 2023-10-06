package br.senai.sp.jandira.s_book.components.create_account_endereco.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import androidx.compose.ui.res.painterResource

@Composable
fun Button(
    text: String,
    onClick: () -> Unit,

) {
    androidx.compose.material3.Button(
        onClick = {
                  onClick()
        },
        modifier = Modifier
            .width(300.dp)
            .height(48.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(Color(218, 108, 39, 255)),
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF)
                )

            )
            Image(
                painter = painterResource(id = R.drawable.vector),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Button(
        text = "Continuar",
        onClick = {}
    )
}