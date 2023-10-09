package br.senai.sp.jandira.s_book.components.create_account_endereco.components


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import br.senai.sp.jandira.s_book.components.universal.DefaultButtonScreen
import br.senai.sp.jandira.s_book.functions.createAccountApp
import br.senai.sp.jandira.s_book.view_model.CreateAccountView
import br.senai.sp.jandira.s_book.view_model.UserCategoryViewModel


@Composable
fun Form(
    navController: NavController,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: CreateAccountView,
    viewModelUserCategory: UserCategoryViewModel
){
    val context = LocalContext.current

    val nome = viewModel.nome
    val cpf = viewModel.cpf
    val dataNascimento = viewModel.dataNascimento
    val cep = viewModel.cep
    val email = viewModel.email
    val senha = viewModel.senha
    val ufEstado = viewModel.ufEstado
    val cidade = viewModel.cidade
    val logradouro = viewModel.logradouro
    val bairro = viewModel.bairro

    var isChecked by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(15.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TextBoxScreenEndereco(
            label = "Estado",
            valor = ufEstado
        )
        TextBoxScreenEndereco(
            label = "Cidade",
            valor = cidade
        )
        TextBoxScreenEndereco(
            label = "bairro",
            valor = bairro
        )
        TextBoxScreenEndereco(
            label = "Logradouro",
            valor = logradouro
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(start = 32.dp)
        ){
            Checkbox(
                checked = isChecked,
                onCheckedChange = {
                    isChecked = it
                }
            )

            Text(
                text = "Concordo com os termos & politicas",
                style = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF9F9898),
                ),
                modifier = Modifier
                    .padding(top = 7.dp)
            )
        }
        DefaultButtonScreen(text = "Entrar") {
            if(isChecked){
                createAccountApp(
                    nome!!, cpf!!, dataNascimento!!, email!!, senha!!, cep!!, ufEstado!!, cidade, bairro, logradouro, navController, lifecycleScope, "category", context, viewModelUserCategory
                )
            }else{
                Toast.makeText(context, "Marque que concorda com os termos e politicas", Toast.LENGTH_LONG).show()
            }
        }
    }
}
//nome: String,
//cpf: String,
//dataNascimento: String,
//email: String,
//senha: String,
//cep: String,
//ufEstado: String,
//cidade: String,
//bairro: String,
//logradouro: String,


