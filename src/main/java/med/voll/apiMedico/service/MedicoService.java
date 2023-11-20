package med.voll.apiMedico.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import med.voll.apiMedico.domain.medico.DadosAtualizacaoMedico;
import med.voll.apiMedico.domain.medico.DadosCadastroMedico;
import med.voll.apiMedico.domain.medico.DadosDetalhamentoMedico;
import med.voll.apiMedico.domain.medico.DadosListagemMedico;
import med.voll.apiMedico.domain.medico.Medico;


public interface MedicoService {

	Medico cadastroMedico (DadosCadastroMedico dados);
	
	public Page<DadosListagemMedico> listarMedicosComPaginacao(Pageable paginacao);
	
	public DadosDetalhamentoMedico atualizarMedico(Long medicoId, DadosAtualizacaoMedico dados);
	
	public void deletarMedico(Long medicoId);
	
	public DadosDetalhamentoMedico listaUmMedico(Long medicoId);
	
}
