package br.senai.sp.jandira.s_book.model.chat

class MesagensResponse (
    var status: Int,
    var message: String,
    var id_chat: String,
    var usuarios: List<UserChat>,
    var data_criacao: String,
    var hora_criacao: String,
    var mensagens: List<Mensagem>
)