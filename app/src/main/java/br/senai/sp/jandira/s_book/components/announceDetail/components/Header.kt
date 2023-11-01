package br.senai.sp.jandira.s_book.components.announceDetail.components

import android.util.Log
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Header(viewMODEL: AnuncioViewModel) {
    val TAG = "Teste"

    val imagens = remember { mutableStateListOf<String>() }

    LaunchedEffect(viewMODEL) {
        imagens.clear()
        val fotos = viewMODEL.foto
        for (foto in fotos) {
            val imagemUrl = foto.foto
            if (imagemUrl.isNotEmpty()) {
                imagens.add(imagemUrl)
            }
        }
    }

    val pagerState = rememberPagerState(
        pageCount ={ imagens.size}
    )

    val transition = rememberInfiniteTransition()
    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000),
            repeatMode = RepeatMode.Restart
        )
    )

    autoScrollCarrossel(pagerState) // Chame a função para iniciar o carrossel automático

    Card(
        modifier = Modifier
            .height(268.dp)
            .width(160.dp)
    ) {
        Log.e(TAG, "Header: $imagens")

        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            pageSpacing = 0.dp,
            pageContent = { pageIndex ->
                val imagem = imagens.getOrNull(pageIndex)

                imagem?.let {
                    val currentPage = pagerState.currentPage
                    val nextPage = (currentPage + 1) % imagens.size
                    val prevPage = (currentPage - 1 + imagens.size) % imagens.size

                    val alpha = when (pageIndex) {
                        currentPage, nextPage, prevPage -> 1f
                        else -> 0f
                    }

                    val offsetX = when (pageIndex) {
                        currentPage -> 0.dp
//                        nextPage -> offset * 168.dp
//                        prevPage -> -offset * 168.dp
                        else -> 0.dp
                    }

                    val modifier = Modifier
                        .width(168.dp)
                        .height(245.dp)
                        .padding(top = 12.dp)
                        .offset(x = offsetX)
                        .alpha(alpha)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        AsyncImage(
                            model = it,
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = modifier
                        )
                    }
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun autoScrollCarrossel(pagerState: PagerState) {
    LaunchedEffect(Unit) {
        while (true) {
            delay(5000) // Tempo de espera em milissegundos (5 segundos)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }
}






