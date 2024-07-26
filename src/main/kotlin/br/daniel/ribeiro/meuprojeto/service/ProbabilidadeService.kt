package br.daniel.ribeiro.meuprojeto.service

import br.daniel.ribeiro.meuprojeto.dto.ConteudoProbabilidadeDto
import br.daniel.ribeiro.meuprojeto.dto.ProbabilidadeDto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProbabilidadeService(val loteriaService: LoteriaService) {
    private val logger: Logger = LoggerFactory.getLogger(ProbabilidadeService::class.java)

    fun getProbabilidades(quantidadeSorteios: Int, ultimoSorteioConsiderado: Int): ConteudoProbabilidadeDto {
        val resultados = loteriaService.getResultados()

        val frequenciaDezenas = mutableMapOf<String, Int>()

        val ultimosSorteios = resultados
            .filter { it.concurso <= ultimoSorteioConsiderado }
            .take(quantidadeSorteios)

        ultimosSorteios.forEach { resultado ->
            resultado.dezenas.forEach { dezena ->
                frequenciaDezenas[dezena] = frequenciaDezenas.getOrDefault(dezena, 0) + 1
            }
        }

        // Ordenar por frequência
//        val frequenciaDezenasOrdenadas = frequenciaDezenas.entries.sortedByDescending { it.value }

//        logger.info("Frequência das dezenas:")
//        frequenciaDezenasOrdenadas.forEach { (dezena, frequencia) ->
//            logger.info("Dezena: $dezena, Frequência: $frequencia")
//        }

        // Filtrar dezenas com frequência maior que 10
        val frequencia = quantidadeSorteios / 1.7
        logger.info("Frequência quantidade dividido por 2: $frequencia")

        val dezenasFrequentes = frequenciaDezenas.filter { it.value > frequencia }.keys.toList()

        val conteudo = ConteudoProbabilidadeDto(ultimoSorteioConsiderado = ultimosSorteios.first().concurso)

        logger.info("DEZENAS PARA GERAR NÚMEROS: ${dezenasFrequentes.size} ")

        if (dezenasFrequentes.size < 15) {
            logger.info("Não há dezenas suficientes com frequência maior que $frequencia para gerar um jogo.")
            logger.info("$dezenasFrequentes")
        } else {
            while (conteudo.probabilidades.size < 5) {
                // Gerar jogos com 15 dezenas
                val dezenas = dezenasFrequentes.shuffled().take(15).sorted()
                conteudo.probabilidades.add(
                    ProbabilidadeDto(
                        jogo = "Jogo ${conteudo.probabilidades.size + 1}",
                        dezenas = dezenas
                    )
                )
            }

            logger.info("Jogo 1: $conteudo.probabilidades")
        }

        return conteudo
    }
}
