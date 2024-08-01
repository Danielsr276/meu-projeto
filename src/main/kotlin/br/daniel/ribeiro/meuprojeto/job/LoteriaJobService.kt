package br.daniel.ribeiro.meuprojeto.job

import br.daniel.ribeiro.meuprojeto.service.ConferenciaService
import br.daniel.ribeiro.meuprojeto.service.ProbabilidadeService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class LoteriaJobService(
    val probabilidadeService: ProbabilidadeService,
    val conferenciaService: ConferenciaService
) {
    private val logger: Logger = LoggerFactory.getLogger(LoteriaJobService::class.java)

    @EventListener(ContextRefreshedEvent::class)
    fun processar() {
        logger.info("------------------------------------------------------------")
        logger.info("     INICIO DO PROCESSAMENTO DO JOB                     ")
        logger.info("------------------------------------------------------------")

        val qt = 48
        val divisor = "1.75"
        var ultimoSorteio = 3167

        repeat(250) {
            logger.info("------------------- ÚLTIMO SORTEIO CONSIDERADO: $ultimoSorteio -------------------")
            val probabilidades = probabilidadeService.getProbabilidades(qt, divisor, ultimoSorteio)
            val conferirNumeros = conferenciaService.conferirNumeros(probabilidades)!!

            conferirNumeros.filter { it.acertos >= 11 }.forEach {
                logger.info("CONCURSO: ${it.concurso}")
                logger.info("PREMIADO: ${it.premiado}")
                logger.info("ACERTOS: ${it.acertos}")
            }

            ultimoSorteio -= 1
        }

        logger.info("------------------------------------------------------------")
        logger.info("     FIM DO PROCESSAMENTO DO JOB                        ")
        logger.info("------------------------------------------------------------")
    }
}