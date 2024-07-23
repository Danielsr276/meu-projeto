package br.daniel.ribeiro.meuprojeto

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class MeuProjetoApplication

fun main(args: Array<String>) {
    runApplication<MeuProjetoApplication>(*args)
}
