package med.voll.apiMedico.domain.medico;

public record DadosListagemMedico(
		Long id,
		String nome,
		String email,
		String telefone,
		String crm,
		Especialidade especialidade,
		String bairro) 
{
	public DadosListagemMedico(Medico medico ) {
		this(medico.getId(),
				medico.getNome(),
				medico.getEmail(), 
				medico.getTelefone(),
				medico.getCrm(), 
				medico.getEspecialidade(),
				medico.getEndereco().getBairro());
	}
}
