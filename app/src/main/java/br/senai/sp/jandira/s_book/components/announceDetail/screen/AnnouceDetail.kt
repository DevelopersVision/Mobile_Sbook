package br.senai.sp.jandira.s_book.components.announceDetail.screen

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.announceDetail.components.CardInformacao
import br.senai.sp.jandira.s_book.components.announceDetail.components.FooterDescricao
import br.senai.sp.jandira.s_book.components.announceDetail.components.Header
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel


@Composable
fun AnnouceDetail(
    navController: NavController,
    viewMODEL: AnuncioViewModel
){
    //Log.e("viewLuiz", "${viewMODEL.autor}")
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ){
            items(1){
                Header(viewMODEL)
                CardInformacao(viewMODEL)
                FooterDescricao(viewMODEL)
            }

        }
    }
}
