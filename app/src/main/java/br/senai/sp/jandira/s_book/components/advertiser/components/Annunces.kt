package br.senai.sp.jandira.s_book.components.advertiser.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
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
import br.senai.sp.jandira.s_book.components.my_announces.components.favoritarAnuncio
import br.senai.sp.jandira.s_book.components.my_announces.components.removerDosFavoritos
import br.senai.sp.jandira.s_book.model.VerificarFavoritoBaseResponse
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Preview(showSystemUi = true)
@Composable
fun Annunces(

) {

    var isChecked by remember { mutableStateOf(false) }


    Card(
        modifier = Modifier
            .width(156.dp)
            .height(260.dp)
            .clickable {
//                onClick()
//                navController.navigate("annouceDetail")
            }
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0xFF000000),
                ambientColor = Color(0xFF000000)
            )
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 4.dp))
            .padding(start = 12.dp,top = 12.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.susanna_profile),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(96.dp)
                        .height(148.dp)
                )
//                AsyncImage(
//                    model = "${foto}",
//                    contentDescription = "",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .width(96.dp)
//                        .height(148.dp)
//                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Morreu",
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.poppinsmedium)),
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
            )
            Text(
                text = "Lucas",
                fontSize = 10.sp,
                fontFamily = FontFamily(Font(R.font.intermedium)),
                fontWeight = FontWeight(600),
                color = Color(0xFF9F9898),
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Troca-se",
                    fontSize = 16.sp,
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
                        .size(40.dp),
                    onClick = {}
                ) {
                    if (isChecked) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.coracao_certo
                            ),
                            contentDescription = ""
                        )
                    } else {
                        Image(
                            painter = painterResource(
                                id = R.drawable.desfavoritar
                            ),
                            contentDescription = ""
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .height(10.dp)
            ) {}
        }
    }
}