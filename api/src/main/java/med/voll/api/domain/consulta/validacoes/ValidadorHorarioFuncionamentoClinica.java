package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.exception.ValidacaoException;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta{
	
	public void validar(DadosAgendamentoConsulta dados) {
		var dataConsulta = dados.data();
		
		var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
		var depoisDoFechamentoClinica = dataConsulta.getHour() > 18;
		
		if(domingo || antesDaAberturaDaClinica || depoisDoFechamentoClinica) {
			throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
		}
	}

}
