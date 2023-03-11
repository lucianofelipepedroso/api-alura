package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoMedico(Long id, String nome, 
		String email, String crm, String telefone, Especialidade especialidade,Endereco endereco) {
	
	public DadosDetalhamentoMedico(Medico dados) {
		this(dados.getId(),dados.getNome(),dados.getEmail(),dados.getCrm(),dados.getTelefone() ,dados.getEspecialidade(),dados.getEndereco());
	}

}
