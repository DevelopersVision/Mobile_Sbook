package br.senai.sp.jandira.s_book.components.announceDetail.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import coil.compose.AsyncImage


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Header(viewMODEL: AnuncioViewModel) {
    val TAG = "Teste"

    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        val pagerState = rememberPagerState(
            pageCount = {
                4
            }
        )

        Card(
            modifier = Modifier
                .height(200.dp)
                .width(350.dp)
        ) {
            Log.e(TAG, "Header: ${viewMODEL.foto}", )
            val imagens = remember{
                mutableStateListOf(
                    viewMODEL.foto[0].foto,
                    viewMODEL.foto[1].foto,
                    viewMODEL.foto[2].foto,
                    viewMODEL.foto[3].foto
                )
            }
            Log.e(TAG, "Header: $imagens", )
            HorizontalPager(
                modifier = Modifier,
                state = pagerState,
                pageSpacing = 0.dp,
                pageContent = {

                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()

                    ){

                        AsyncImage(
                            model = imagens[it],
                            contentDescription = "",
                            modifier = Modifier
                                .size(120.dp)
                        )
                    }
                }
            )
        }

        Spacer(
            modifier = Modifier
                .height(15.dp)
        )

        Row (
            modifier = Modifier
                .width(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Card(
                modifier = Modifier
                    .width(7.dp)
                    .height(7.dp),
            ) {}
            Card (
                modifier = Modifier
                    .width(7.dp)
                    .height(7.dp),
            ){}
            Card (
                modifier = Modifier
                    .width(7.dp)
                    .height(7.dp),
            ){}
        }

    }
}

//