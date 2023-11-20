package med.voll.apiMedico.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.apiMedico.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
		@NotNull
		Long id,
		String nome,
		String telefone,
		DadosEndereco endereco
		) {

}
