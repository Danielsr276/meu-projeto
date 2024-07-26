package br.daniel.ribeiro.meuprojeto.service

import br.daniel.ribeiro.meuprojeto.client.LotofacilClient
import br.daniel.ribeiro.meuprojeto.dto.LoteriaDto
import org.springframework.stereotype.Service

@Service
class LoteriaService(val lotofacilClient: LotofacilClient) {
    fun getResultados(): List<LoteriaDto> {
        return lotofacilClient.getResultados()
    }

    fun getSorteio(numero: Int): LoteriaDto? {
        return lotofacilClient.getSorteio(numero)
    }
}
