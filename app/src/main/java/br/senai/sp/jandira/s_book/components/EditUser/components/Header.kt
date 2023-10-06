package br.senai.sp.jandira.s_book.components.EditUser.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R


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
