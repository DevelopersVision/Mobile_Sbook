package br.senai.sp.jandira.s_book.components.EditUser.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.components.perfil.components.RatingBar


//@Preview(showSystemUi = true)
@Composable
fun Header(
    navRotasController: NavController
) {

    var icons = Icons.Default.ArrowBack

    Row(
        modifier = Modifier
            .width(300.dp)
            .height(100.dp)
            .padding(top = 32.dp),
        horizontalArrangement = Arrangement.SpaceBetween,

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top,
        ) {
            IconButton(
                onClick = {
                    navRotasController.navigate("perfil")
                },
                modifier = Modifier
                    .height(64.dp)
                    .width(64.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFCECECE),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
            ) {
                Icon(
                    imageVector = icons,
                    contentDescription = ""
                )
            }
        }
        Image(
            modifier = Modifier
                .size(55.dp),
            painter = painterResource(
                id = R.drawable.logo
            ),
            contentDescription = ""
        )
    }
}
