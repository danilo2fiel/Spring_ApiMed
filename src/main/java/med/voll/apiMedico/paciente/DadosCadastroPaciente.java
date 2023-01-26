package med.voll.apiMedico.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.apiMedico.endereco.DadosEndereco;

public record DadosCadastroPaciente(

	@NotBlank	
	String nome,
	@NotBlank @Email
	String email,
	@NotBlank 
	String telefone,
	@NotBlank 
	@Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
	String cpf,
	DadosEndereco endereco
) {
}