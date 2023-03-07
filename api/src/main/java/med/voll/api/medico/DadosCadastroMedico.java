package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
		@NotBlank
		String nome, 
		@NotBlank
		@Email
		String email,
		@NotBlank
		@Pattern (regexp = "^(\\+\\d{1,2}\\s?)?1?\\-?\\.?\\s?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")
		String telefone,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String crm,
		@NotNull
		Especialidade especialidade,
		@NotNull
		@Valid
		DadosEndereco endereco) {
	
	

}
