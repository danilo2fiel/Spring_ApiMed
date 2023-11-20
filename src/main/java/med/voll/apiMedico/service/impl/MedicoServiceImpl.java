package med.voll.apiMedico.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import med.voll.apiMedico.domain.medico.DadosAtualizacaoMedico;
import med.voll.apiMedico.domain.medico.DadosCadastroMedico;
import med.voll.apiMedico.domain.medico.DadosDetalhamentoMedico;
import med.voll.apiMedico.domain.medico.DadosListagemMedico;
import med.voll.apiMedico.domain.medico.Medico;
import med.voll.apiMedico.domain.medico.MedicoRepository;
import med.voll.apiMedico.service.MedicoService;

@Service
public class MedicoServiceImpl implements MedicoService {

	@Autowired
	MedicoRepository repository;
	
	@Override
	public Medico cadastroMedico(DadosCadastroMedico dados) {
		var medico = new Medico(dados);
		return repository.save(medico);
	}

	@Override
	public Page<DadosListagemMedico> listarMedicosComPaginacao(Pageable paginacao) {
		Page<Medico> medicosPage = repository.findAll(paginacao);
		Page<DadosListagemMedico> dadosListagemMedicoPage = medicosPage.map(DadosListagemMedico::new);
		return dadosListagemMedicoPage;
	}

	@Override
	public DadosDetalhamentoMedico atualizarMedico(Long medicoId, DadosAtualizacaoMedico dados) {
		Medico medico = repository.getReferenceById(medicoId);
        medico.atualizarInformacoes(dados);
        return new DadosDetalhamentoMedico(medico);
	}

	@Override
	public void deletarMedico(Long medicoId) {
		repository.deleteById(medicoId);
	}

	@Override
	public DadosDetalhamentoMedico listaUmMedico(Long medicoId) {
		Medico medico = repository.getById(medicoId);
		return new DadosDetalhamentoMedico(medico);
	}


}
