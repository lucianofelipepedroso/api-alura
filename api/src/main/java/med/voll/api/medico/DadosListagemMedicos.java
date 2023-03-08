package med.voll.api.medico;

public record DadosListagemMedicos(Long id, Boolean ativo,String nome, String email, String crm, Especialidade especialidade) {

	public DadosListagemMedicos(Medico medico) {
		this( medico.getId(), medico.getAtivo(),  medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}

}
