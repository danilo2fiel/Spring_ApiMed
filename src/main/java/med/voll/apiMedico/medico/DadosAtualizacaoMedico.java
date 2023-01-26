package med.voll.apiMedico.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.apiMedico.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
		@NotNull
		Long id,
		String nome,
		String telefone,
		DadosEndereco endereco) {
	
}

