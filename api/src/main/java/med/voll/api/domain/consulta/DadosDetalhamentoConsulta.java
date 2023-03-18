package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record DadosDetalhamentoConsulta(Long idConsulta, Long idMedico, Long idPessoa,
		LocalDateTime dataConsulta) {

	public DadosDetalhamentoConsulta(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
	}
	
	
	//@JsonFormat(pattern = "dd/MM/yyyy HH:mm") 

}
