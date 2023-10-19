package br.senai.sp.jandira.s_book.components.edit_user.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                Button(
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
    TextField(
        value = selectedDate,
        onValueChange = { },
        modifier = Modifier
            .width(200.dp)
            .onFocusEvent {
                if (it.isFocused) {
                    showDatePickerDialog = true
                }
            }
            .background(Color.Transparent),
        label = {
            Text(
                text = "Data de Nascimento",
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF808080)
            )
        },
        textStyle = TextStyle(
            color = Color(69, 90, 100, 255),
            fontSize = 16.sp,
            fontWeight = FontWeight(500)
        ),
        readOnly = true,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color(206, 206, 206, 255),
            unfocusedIndicatorColor = Color(206, 206, 206, 255),
            disabledIndicatorColor = Color(206, 206, 206, 255),
            errorIndicatorColor = Color(206, 206, 206, 255)
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
