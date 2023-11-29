package br.senai.sp.jandira.s_book.model.chat

data class SocketResponse(
    var users: List<ChatRoom>,
    val id_user: Int
)
