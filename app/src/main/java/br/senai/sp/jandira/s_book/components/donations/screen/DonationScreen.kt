package br.senai.sp.jandira.s_book.components.donations.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.donations.components.BannerDonations
import br.senai.sp.jandira.s_book.components.donations.components.CardDonationsAnnounce
import br.senai.sp.jandira.s_book.components.donations.components.HeaderDonations
import br.senai.sp.jandira.s_book.components.donations.components.SubTitlesDonations
import br.senai.sp.jandira.s_book.components.feed.components.ButtonCarregar
import br.senai.sp.jandira.s_book.components.universal.ProgressBar
import br.senai.sp.jandira.s_book.model.AnuncioDonationsResponse
import br.senai.sp.jandira.s_book.model.AnunciosBaseResponse
import br.senai.sp.jandira.s_book.model.JsonAnuncioDoacao
import br.senai.sp.jandira.s_book.service.RetrofitHelper
import br.senai.sp.jandira.s_book.view_model.AnuncioViewModelV2
import br.senai.sp.jandira.s_book.view_model.SharedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun DonationsScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    viewModel: AnuncioViewModelV2
) {
    val context = LocalContext.current

    var listaAnuncioDoacao by remember {
        mutableStateOf(listOf<JsonAnuncioDoacao>())
    }

    var listaAnuncioDoacaoOfc by remember {
        mutableStateOf(listOf<JsonAnuncioDoacao>())
    }

    var cont by remember {
        mutableStateOf(true)
    }

    var isLoading by remember { mutableStateOf(false) } // Variável para controlar a visibilidade da ProgressBar

    var page by remember {
        mutableIntStateOf(1)
    }

    val call = RetrofitHelper.getAnunciosService().getAnuncioDoacao(page)

    // Executar a chamada
    call.enqueue(object : Callback<AnuncioDonationsResponse> {
        override fun onResponse(
            call: Call<AnuncioDonationsResponse>, response: Response<AnuncioDonationsResponse>
        ) {
            Log.e("Page", "$page")

            if (response.code() == 200) {
                listaAnuncioDoacao = response.body()!!.anuncios

                if (cont && listaAnuncioDoacao.isNotEmpty() && response.body()!!.page == page) {
                    listaAnuncioDoacaoOfc += listaAnuncioDoacao

                    cont = false
                }
            } else {
                cont = false
                Toast.makeText(context, "Não tem mais anuncios a ser mostrado", Toast.LENGTH_SHORT).show()
            }

        }

        override fun onFailure(call: Call<AnuncioDonationsResponse>, t: Throwable) {
            cont = false
            Toast.makeText(context, "SERVIÇO ESTÁ FORA DO AR TENTE MAIS TARDE", Toast.LENGTH_LONG)
                .show()
            Log.d("ERROR_FEED-tmessage", "${t.message}")
            Log.d("ERROR_FEED-tstacktrace", "${t.stackTrace}")
        }
    })


    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        HeaderDonations {
            navController.popBackStack()
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            items(1) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    SubTitlesDonations(listaAnuncioDoacaoOfc.size)
                    Spacer(modifier = Modifier.height(30.dp))
                    BannerDonations()
                    Spacer(modifier = Modifier.height(30.dp))
                    val pairs = listaAnuncioDoacaoOfc.chunked(2)

                    for (pair in pairs) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(3.dp, 0.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            for (item in pair) {
                                CardDonationsAnnounce(dados = item){
                                    viewModel.idAnuncio = item.anuncio.id
                                    navController.navigate("anuncio")
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(25.dp))
                    }
                    if (cont == true) {
                        isLoading = true
                        ProgressBar(isDisplayed = isLoading)
                        Spacer(modifier = Modifier.height(48.dp))
                    } else {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(0.dp, 5.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            ButtonCarregar {
                                page++
                                cont = true
                                Log.e("favela morreu", "FeedScreen: ${isLoading}")
                            }
                        }
                        Spacer(modifier = Modifier.height(64.dp))
                    }
                }
            }
        }
    }
}