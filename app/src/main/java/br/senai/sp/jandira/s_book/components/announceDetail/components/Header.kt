package br.senai.sp.jandira.s_book.components.announceDetail.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.view_model.AnuncioViewMODEL
import coil.compose.AsyncImage


@Composable
fun Header(viewMODEL: AnuncioViewMODEL){

    Log.e("Log de HJJJJJJJJJJJJJ", "${viewMODEL.foto}")

    Column (
        modifier = Modifier
            .height(268.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = "${viewMODEL.foto}",
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(160.dp)
                .height(245.dp)
                .padding(top = 12.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Card (
                Modifier
                    .width(10.dp)
                    .height(10.dp),
                backgroundColor = Color(0xFF35225F)
            ){}
            Spacer(modifier = Modifier.width(8.dp))
            Card (
                Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .height(10.dp),
                backgroundColor = Color(0xFFBEA5F5)
            ){}
            Spacer(modifier = Modifier.width(8.dp))
            Card (
                Modifier
                    .width(10.dp)
                    .height(10.dp),
                backgroundColor = Color(0xFFBEA5F5)
            ){}
            Spacer(modifier = Modifier.width(8.dp))
            Card (
                Modifier
                    .width(10.dp)
                    .height(10.dp),
                backgroundColor = Color(0xFFBEA5F5)
            ){}

        }
    }
}