package med.voll.api.paciente;

public record DadosListagemPacientes(String nome,String email,String cpf,String telefone) {
	
	
	public DadosListagemPacientes(Paciente paciente) {
		this(paciente.getNome(),paciente.getEmail(),paciente.getCpf(),paciente.getTelefone());
	}

}
