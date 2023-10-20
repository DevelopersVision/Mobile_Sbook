package br.senai.sp.jandira.s_book.components.chats.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.R

@Composable
fun ChatScreen(
    navController: NavController
){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
            backgroundColor = Color(120, 79, 52, 255)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Chat",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF)
                )
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .shadow(
                    elevation = 6.dp,
                    spotColor = Color(0xFF000000),
                    ambientColor = Color(0xFF000000)
                )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = "Conversas",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        color = Color(120, 79, 52, 255)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider(
                        modifier = Modifier
                            .height(2.8.dp)
                            .width(120.dp),
                        color = Color(120, 79, 52, 255)
                    )
                }
                Text(
                    text = "Grupos",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF808080)
                )
            }
        }
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("conversa_chat") }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.susanna_profile),
                        contentDescription = "",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(56.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Max Kellerman",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Bom trabalho, fico feliz em v...",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF3B4A54)
                        )
                    }
                }
                Text(
                    text = "13:10",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF3B4A54)
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {  }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.susanna_profile),
                        contentDescription = "",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(56.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Max Kellerman",
                            fontSize = 16.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Bom trabalho, fico feliz em v...",
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF3B4A54)
                        )
                    }
                }
                Text(
                    text = "13:10",
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF3B4A54)
                )
            }
        }
        Divider(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth(),
            color = Color(0xFFCECECE)
        )
    }
}