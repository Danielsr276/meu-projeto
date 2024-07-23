package br.daniel.ribeiro.meuprojeto.client

import br.daniel.ribeiro.meuprojeto.dto.LoteriaDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "lotofacilClient", url = "\${lotofacil.url}")
interface LotofacilClient {
    @GetMapping("/lotofacil")
    fun getResultados(): List<LoteriaDto>
}
