package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DadosDetalhamentoConsulta(
		Long idConsulta, 
		Long idMedico, 
		Long idPessoa, 
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
		LocalDateTime dataConsulta) {

}
