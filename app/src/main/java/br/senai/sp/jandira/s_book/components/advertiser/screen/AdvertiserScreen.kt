package br.senai.sp.jandira.s_book.components.advertiser.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.advertiser.components.Annunces
import br.senai.sp.jandira.s_book.components.advertiser.components.HeaderBoxAdvertiser
import br.senai.sp.jandira.s_book.components.advertiser.components.ListCategory
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
@Composable
fun AdvertiserScreen(
    navController: NavController,
    viewModelV2: AnuncioViewModelV2
) {



    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        HeaderBoxAdvertiser(navController = navController)
        ListCategory()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 19.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Anúncios",
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600),
                    color = Color(170, 98, 49, 255),
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement. spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Annunces()
                Annunces()
            }
        }
        
        Spacer(modifier = Modifier.height(38.dp))
    }
}