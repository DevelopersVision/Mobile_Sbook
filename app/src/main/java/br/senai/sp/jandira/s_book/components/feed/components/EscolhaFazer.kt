package br.senai.sp.jandira.s_book.components.feed.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PriceChange
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.filled.LocalOffer
import br.senai.sp.jandira.s_book.navigation_home_bar.BottomBarScreen

@Preview(showSystemUi = true)
@Composable
fun EscolhaFazer() {

    var doacoes = Icons.Default.LocalOffer

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Top),
    ) {
        Text(
            modifier = Modifier
                .padding(start = 21.dp),
            text = "O que você quer fazer?",
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.poppinsmedium)),
            fontWeight = FontWeight(400),
            color = Color(0xFF565454),
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
            ) {
            items(3) {

                Column(
                    modifier = Modifier
                        .height(90.dp)
                        .width(100.dp)
                        .shadow(
                            elevation = 2.dp,
                            spotColor = Color(0x40000000),
                            ambientColor = Color(0x40000000)
                        ),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        modifier = Modifier
                            .height(30.dp)
                            .width(100.dp),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = doacoes,
                            contentDescription = ""
                        )
                    }
                    Text(
                        text = "Doações",
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                        fontWeight = FontWeight(400),
                        color = Color(92,44,12),
                    )
                }
            }
        }

    }

}

