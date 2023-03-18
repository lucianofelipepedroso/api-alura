package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.exception.ValidacaoException;
import med.voll.api.domain.paciente.PacienteRepository;

@Component
public class ValidarPacienteAtivo implements ValidadorAgendamentoDeConsulta{
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	
	public void validar(DadosAgendamentoConsulta dados) {
		
		var pacienteEstaAitivo =  pacienteRepository.findAtivoById(dados.idPaciente());
		if(!pacienteEstaAitivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo");
		}
	}

}
