package br.senai.sp.jandira.s_book.components.announceDetail.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModel
import coil.compose.AsyncImage
import androidx.compose.foundation.pager.rememberPagerState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Header(viewMODEL: AnuncioViewModel) {
    val TAG = "Teste"

    Column(
        modifier = Modifier
            .height(288.dp)
            .width(160.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Column(
            modifier = Modifier
                .height(288.dp)
                .width(160.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val imagens = remember { mutableStateListOf<String>() }
            imagens.clear()

            val fotos = viewMODEL.foto

            for (foto in fotos) {
                val imagemUrl = foto.foto

                if (imagemUrl.isNotEmpty()) {
                    imagens.add(imagemUrl)
                }
            }
            val pagerState = rememberPagerState(
                pageCount = {
                    imagens.size
                }
            )

            Card(
                modifier = Modifier
                    .height(268.dp)
                    .width(160.dp)
            ) {
                Log.e(TAG, "Header: $imagens")

                HorizontalPager(
                    modifier = Modifier,
                    state = pagerState,
                    pageSpacing = 0.dp,
                    pageContent = { pageIndex ->
                        val imagem = imagens.getOrNull(pageIndex)

                        imagem?.let {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                AsyncImage(
                                    model = it,
                                    contentDescription = "",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(168.dp)
                                        .height(245.dp)
                                        .padding(top = 12.dp)
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

//