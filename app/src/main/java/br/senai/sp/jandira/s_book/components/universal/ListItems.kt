package br.senai.sp.jandira.s_book.components.universal

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import br.senai.sp.jandira.s_book.model.ListItem

@Composable
fun ListItems(
    text: String
) {
    var items by remember {
        mutableStateOf(
            (1..20).map {
                ListItem(
                    title = text,
                    isSelect = false
                )
            }
        )
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(items.size) { i ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp)
                        .padding(start = 12.dp)
                        .clickable {
                            items = items.mapIndexed { j, item ->
                                if (i == j) {
                                    item.copy(isSelect = !item.isSelect)
                                } else item
                            }
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = text,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.intermedium)),
                        fontWeight = FontWeight(500),
                        color = Color(0xFF808080),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 12.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        if (items[i].isSelect) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "",
                                tint = Color(0xFFAA6231),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.9.dp)
                        .background(color = Color(0xFFE0E0E0))
                ) {}
            }
        }
    }
}