package med.voll.api.domain.paciente;

public record DadosListagemPacientes(Long id, Boolean ativo,String nome,String email,String cpf,String telefone) {
	
	
	public DadosListagemPacientes(Paciente paciente) {
		this(paciente.getId(), paciente.getAtivo(),  paciente.getNome(),paciente.getEmail(),paciente.getCpf(),paciente.getTelefone());
	}

}
