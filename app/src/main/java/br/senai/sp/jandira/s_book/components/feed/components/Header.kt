package br.senai.sp.jandira.s_book.components.feed.components

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.sqlite_repository.UserRepository

@Composable
fun Header(
    navController: NavController,
    navRotasController: NavController,
    context: Context
) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.fundo
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(229.dp)
                )
                Column(
                    modifier = Modifier
                        .height(220.dp)
                        .padding(15.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.logo
                            ),
                            contentDescription = "",
                            modifier = Modifier
                                .size(52.dp)
                                .padding(4.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop

                        )

                        IconButton(
                            onClick = {
                                if(UserRepository(context).findUsers().isEmpty()){
                                    navRotasController.navigate("login")
                                }else{
                                    navRotasController.navigate("perfil")
                                }
                            }
                        ) {
                            Image(
                                painter = painterResource(
                                    id = R.drawable.susanna_profile
                                ),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(51.dp)
                                    .padding(4.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop

                            )
                        }
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 24.dp)
                        ) {
                            Text(
                                text = "Bem-Vindo, Usúario(a)!",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W700
                            )
                        }
                    }
                }
            }
        }

}


@Preview(showSystemUi = true)
@Composable
fun HeaderPreview() {
//    Header()
}