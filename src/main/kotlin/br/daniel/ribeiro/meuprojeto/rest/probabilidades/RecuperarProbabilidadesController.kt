package br.daniel.ribeiro.meuprojeto.rest.probabilidades

import br.daniel.ribeiro.meuprojeto.dto.ConteudoProbabilidadeDto
import br.daniel.ribeiro.meuprojeto.service.ConferenciaService
import br.daniel.ribeiro.meuprojeto.service.ProbabilidadeService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class RecuperarProbabilidadesController(
    val probabilidadeService: ProbabilidadeService,
    val conferenciaService: ConferenciaService
) {

    private val logger: Logger = LoggerFactory.getLogger(RecuperarProbabilidadesController::class.java)

    @GetMapping("/probabilidades")
    fun probabilidades(
        @RequestParam quantidadeSorteios: Int,
        @RequestParam dividirPor: String,
        @RequestParam ultimoSorteioConsiderado: Int
    ): ResponseEntity<Any> {
        logger.info("Recuperando probabilidades dos últimos sorteios!")

        val probabilidades = probabilidadeService.getProbabilidades(quantidadeSorteios, dividirPor, ultimoSorteioConsiderado)

        return ResponseEntity(probabilidades, HttpStatus.OK)
    }

    @PostMapping("/probabilidades/conferencia")
    fun conferirProbabilidades(@RequestBody conteudoProbabilidade: ConteudoProbabilidadeDto): ResponseEntity<Any> {
        logger.info("Conferindo probabilidades geradas para o sorteio:${conteudoProbabilidade.ultimoSorteioConsiderado + 1}")

        val probabilidadesConferidas = conferenciaService.conferirNumeros(conteudoProbabilidade)

        return ResponseEntity(probabilidadesConferidas, HttpStatus.OK)
    }

}