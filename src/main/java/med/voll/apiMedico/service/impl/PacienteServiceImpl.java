package med.voll.apiMedico.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import med.voll.apiMedico.domain.medico.DadosDetalhamentoMedico;
import med.voll.apiMedico.domain.paciente.DadosAtualizacaoPaciente;
import med.voll.apiMedico.domain.paciente.DadosCadastroPaciente;
import med.voll.apiMedico.domain.paciente.DadosDetalhamentoPaciente;
import med.voll.apiMedico.domain.paciente.DadosListagemPaciente;
import med.voll.apiMedico.domain.paciente.Paciente;
import med.voll.apiMedico.domain.paciente.PacienteRepository;
import med.voll.apiMedico.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {
	
	private final PacienteRepository repository;
	
	@Autowired
	public PacienteServiceImpl(PacienteRepository repository) {
		this.repository = repository;
	}

	@Override
	public Paciente cadastroPaciente(DadosCadastroPaciente dados) {
		Paciente paciente = new Paciente(dados);
		return repository.save(paciente);
	}

	@Override
	public Page<DadosListagemPaciente> listarPacientesComPaginacao(Pageable paginacao) {
		Page<Paciente> pacientePage = repository.findAll(paginacao);
		Page<DadosListagemPaciente> dadosListagemPaciente = pacientePage.map(DadosListagemPaciente::new);
		return dadosListagemPaciente;
	}

	@Override
	public DadosDetalhamentoPaciente atualizarPaciente(Long pacienteId, DadosAtualizacaoPaciente dados) {
		Paciente paciente = repository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
		return new DadosDetalhamentoPaciente(paciente);
	}

	@Override
	public void deletarMedico(Long pacienteId) {
		Paciente paciente = repository.getReferenceById(pacienteId);
		repository.delete(paciente);
		
	}

	@Override
	public DadosDetalhamentoMedico listaUmMedico(Long pacienteId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
