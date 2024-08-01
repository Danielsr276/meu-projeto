package br.daniel.ribeiro.meuprojeto

import br.daniel.ribeiro.meuprojeto.rest.probabilidades.RecuperarProbabilidadesController
import br.daniel.ribeiro.meuprojeto.service.ConferenciaService
import br.daniel.ribeiro.meuprojeto.service.ProbabilidadeService
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MeuProjetoApplicationTests(
    val probabilidadeService: ProbabilidadeService,
//    val conferenciaService: ConferenciaService
) {

    private val logger: Logger = LoggerFactory.getLogger(MeuProjetoApplicationTests::class.java)

    @Test
    fun contextLoads() {
    }

    @Test
    fun deveTestarSeGanhouOuNao() {
        val qt = 10
        val divisor = "1.9"
        val ultimoSorteio = 3167

        val probabilidades = probabilidadeService.getProbabilidades(qt, divisor, ultimoSorteio)

        probabilidades.probabilidades.forEach {
            logger.info(it.jogo)
        }

    }
}
