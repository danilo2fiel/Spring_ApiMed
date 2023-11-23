package med.voll.apiMedico.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import med.voll.apiMedico.domain.medico.DadosDetalhamentoMedico;
import med.voll.apiMedico.domain.paciente.DadosAtualizacaoPaciente;
import med.voll.apiMedico.domain.paciente.DadosCadastroPaciente;
import med.voll.apiMedico.domain.paciente.DadosDetalhamentoPaciente;
import med.voll.apiMedico.domain.paciente.DadosListagemPaciente;
import med.voll.apiMedico.domain.paciente.Paciente;

public interface PacienteService {

	Paciente cadastroPaciente (DadosCadastroPaciente dados);
	
	public Page<DadosListagemPaciente> listarPacientesComPaginacao(Pageable paginacao);
	
	public DadosDetalhamentoPaciente atualizarPaciente(Long pacienteId, DadosAtualizacaoPaciente dados);
	
	public void deletarMedico(Long pacienteId);
	
	public DadosDetalhamentoMedico listaUmMedico(Long pacienteId);
}
