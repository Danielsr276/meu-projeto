package br.daniel.ribeiro.meuprojeto.service

import br.daniel.ribeiro.meuprojeto.dto.ConferenciaDto
import br.daniel.ribeiro.meuprojeto.dto.ConteudoProbabilidadeDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ConferenciaService(val loteriaService: LoteriaService) {
    private val logger: Logger = LoggerFactory.getLogger(ConferenciaService::class.java)

    fun conferirNumeros(conteudoProbabilidade: ConteudoProbabilidadeDto): List<ConferenciaDto>? {
        val conferencias = mutableListOf<ConferenciaDto>()
        val sorteio = loteriaService.getSorteio(conteudoProbabilidade.ultimoSorteioConsiderado + 1)

        if (sorteio == null) {
            logger.info("SORTEIO NÃO EXISTENTE!")
            return conferencias
        }

        conteudoProbabilidade.probabilidades.forEach { probabilidade ->
            val acertos = probabilidade.dezenas.intersect(sorteio.dezenas.toSet()).size

            conferencias.add(
                ConferenciaDto(
                    concurso = sorteio.concurso,
                    jogo = probabilidade.jogo,
                    acertos = acertos,
                    premiado = acertos >= 11
                )
            )
        }

        return conferencias
    }
}
