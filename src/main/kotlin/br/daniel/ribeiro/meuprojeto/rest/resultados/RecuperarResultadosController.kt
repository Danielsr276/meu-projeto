package br.daniel.ribeiro.meuprojeto.rest.resultados

import br.daniel.ribeiro.meuprojeto.service.LoteriaService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class RecuperarResultadosController(val loteriaService: LoteriaService) {

    private val logger: Logger = LoggerFactory.getLogger(RecuperarResultadosController::class.java)

    @GetMapping("/resultados")
    fun resultados(): ResponseEntity<Any> {
        logger.info("Recuperando resultados")

        val resultados = loteriaService.getResultados()

        return ResponseEntity(resultados, HttpStatus.OK)
    }
}