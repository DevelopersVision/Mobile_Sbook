package br.senai.sp.jandira.s_book.components.favorite.components


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.model.Genero
import br.senai.sp.jandira.s_book.navigation_home_bar.BottomBarScreen.Anuncio.icon

@Composable
fun Card() {

    val coracao = Icons.Default.FavoriteBorder

    var isChecked by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .width(300.dp)
            .height(200.dp)
            .background(
                color = Color(255, 255, 255, 1),
                shape = RoundedCornerShape(size = 8.dp)
            )

    ) {
        Row(
            modifier = Modifier
                .height(200.dp)
                .width(300.dp)
                .background(
                    color = Color(255, 255, 255, 1)
                )
        ) {

            Image(
                painter =
                painterResource(id = R.drawable.diario),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(130.dp)
                    .height(200.dp)
            )

            Column(
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Diário de um \nBanana",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(
                        Font(R.font.poppinsmedium)
                    ),
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
                Text(
                    text = "J.K Rowling | Abril 2009",
                    fontSize = 12.sp,
                    fontFamily = FontFamily(
                        Font(
                            R.font.intermedium
                        )
                    ),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF9F9898),
                )
                Row(
                    modifier = Modifier
                        .padding(top = 40.dp),
                    horizontalArrangement = Arrangement.spacedBy(13.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,

                    ) {
                    Text(
                        text = "R$ 34,00",
                        fontSize = 24.sp,
                        fontFamily = FontFamily(
                            Font(
                                R.font.poppinsmedium
                            )
                        ),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                    IconButton(
                        modifier = Modifier
                            .width(100.dp)
                            .height(42.dp),
                        onClick = {
                            if(isChecked == false){
                                isChecked = true
                                var cor = 0xFFFFFF
                            } else {
                                isChecked = false
                                var cor = 0xF60E1C
                            }
                        }
                    ) {
                        Icon(
                            imageVector = coracao ,
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun CardPreview() {
    Card()
}