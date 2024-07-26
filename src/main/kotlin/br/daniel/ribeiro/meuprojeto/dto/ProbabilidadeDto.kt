package br.daniel.ribeiro.meuprojeto.dto

data class ProbabilidadeDto(
    val jogo: String,
    val dezenas: List<String>,
)

data class ConteudoProbabilidadeDto(
    var ultimoSorteioConsiderado: Int,
    val probabilidades: MutableSet<ProbabilidadeDto> = mutableSetOf()
)