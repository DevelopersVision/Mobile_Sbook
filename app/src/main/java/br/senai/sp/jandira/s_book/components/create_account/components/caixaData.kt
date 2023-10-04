package br.senai.sp.jandira.s_book.components.create_account.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.s_book.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun caixa(
    context: Context,
    selectedDate: String,
    onDateChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    var showDatePickerDialog by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    LaunchedEffect(showDatePickerDialog) {
        if (!showDatePickerDialog) {
            focusManager.clearFocus(force = true)
        }
    }

    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                androidx.compose.material3.Button(
                    onClick = {
                        datePickerState
                            .selectedDateMillis?.let { millis ->
                                onDateChange(millis.toBrazilianDateFormat())
                            }
                        showDatePickerDialog = false
                    }) {
                    Text(text = "Escolher data")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }
    OutlinedTextField(
        value = selectedDate,
        onValueChange = { },
        modifier = Modifier
            .width(300.dp)
            .onFocusEvent {
                if (it.isFocused) {
                    showDatePickerDialog = true
                }
            },
        label = {
            Text(
                "Data de Nascimento",
                fontSize = 12.sp,
                color = Color(159, 152, 152, 255)
                )

        },
        readOnly = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = colorResource(id = R.color.cinza ),
            unfocusedBorderColor = colorResource(id = R.color.cinza )
        )
    )
}

fun Long.toBrazilianDateFormat(
    pattern: String = "dd/MM/yyyy" //"yyyy-MM-dd"
): String {
    val date = Date(this)
    val formatter = SimpleDateFormat(
        pattern, Locale("pt-br")
    ).apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    return formatter.format(date)
}
