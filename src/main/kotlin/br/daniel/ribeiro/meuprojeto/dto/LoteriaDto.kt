package br.daniel.ribeiro.meuprojeto.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class LoteriaDto(
    val loteria: String,
    val concurso: Int,
    val data: String,
    val local: String,
    val dezenasOrdemSorteio: List<String>,
    val dezenas: List<String>,
    val trevos: List<String>?,
    val timeCoracao: String?,
    val mesSorte: String?,
    val premiacoes: List<Premiacao>,
    val estadosPremiados: List<String>?,
    val observacao: String,
    val acumulou: Boolean,
    val proximoConcurso: Int,
    val dataProximoConcurso: String,
    val localGanhadores: List<LocalGanhador>,
    val valorArrecadado: Double,
    val valorAcumuladoConcurso_0_5: Double,
    val valorAcumuladoConcursoEspecial: Double,
    val valorAcumuladoProximoConcurso: Double,
    val valorEstimadoProximoConcurso: Double
)

data class Premiacao(
    val descricao: String,
    val faixa: Int,
    val ganhadores: Int,
    val valorPremio: Double
)

data class LocalGanhador(
    val ganhadores: Int,
    val municipio: String,
    val nomeFatansiaUL: String,
    val serie: String,
    val posicao: Int,
    val uf: String
)
