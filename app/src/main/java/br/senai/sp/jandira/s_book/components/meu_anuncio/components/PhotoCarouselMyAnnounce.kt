package br.senai.sp.jandira.s_book.components.meu_anuncio.components

import android.util.Log
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.model.Foto
import br.senai.sp.jandira.s_book.model.JsonAnuncios
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PhotoCarouselMyAnnounce(
    jsonAnuncios: JsonAnuncios,
    onClicK: () -> Unit
) {
    val imagens = remember { mutableStateListOf<String>() }
    var listaFotos by remember {
        mutableStateOf(listOf<Foto>())
    }
    listaFotos = jsonAnuncios.foto

    LaunchedEffect(listaFotos) {
        imagens.clear()
        Log.w("Fotos", "$listaFotos")
        val fotos = listaFotos
        for (foto in fotos) {
            val imagemUrl = foto.foto
            if (imagemUrl.isNotEmpty()) {
                imagens.add(imagemUrl)
            }
        }

        Log.e("Images", "${imagens.size}")
    }

    val pagerState = rememberPagerState(
        pageCount = { imagens.size }
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

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        onClicK()
                    },
                painter = painterResource(id = R.drawable.config),
                contentDescription = "",
            )
        }

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
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                listaFotos.forEachIndexed { index, item ->
                    Log.e("Indexs", "$index + ${pagerState.currentPage}")

                    if (index == pagerState.currentPage) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(width = 1.dp, color = Color(0xFF000000))
                                .padding(1.dp)
                                .width(10.dp)
                                .height(10.dp)
                                .background(color = Color(0xFF5C2C0C))
                        )
//                        Card(
//                            modifier = Modifier
//                                .width(10.dp)
//                                .height(10.dp),
//                            backgroundColor = Color(0xFF5C2C0C),
//                            shape = CircleShape,
//                            border = BorderStroke(width = 1.dp, color = Color(0xFF000000))
//                        ) {}
                    } else {
                        Card(
                            modifier = Modifier
                                .width(10.dp)
                                .height(10.dp),
                            backgroundColor = Color.White,
                            shape = CircleShape,
                            border = BorderStroke(width = 1.dp, color = Color(0xFF000000))
                        ) {}
                    }
                }
            }
        }
    }
}