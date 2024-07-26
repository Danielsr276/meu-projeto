package br.daniel.ribeiro.meuprojeto.dto

data class ConferenciaDto(
    val concurso: Int,
    val jogo: String,
    val acertos: Int,
    val premiado: Boolean
)