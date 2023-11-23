package med.voll.apiMedico.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.apiMedico.domain.paciente.DadosAtualizacaoPaciente;
import med.voll.apiMedico.domain.paciente.DadosCadastroPaciente;
import med.voll.apiMedico.domain.paciente.DadosDetalhamentoPaciente;
import med.voll.apiMedico.domain.paciente.DadosListagemPaciente;
import med.voll.apiMedico.domain.paciente.Paciente;
import med.voll.apiMedico.domain.paciente.PacienteRepository;
import med.voll.apiMedico.service.PacienteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/pacientes")
public class PacienteController {
	
	private final PacienteService service;
	private final PacienteRepository repository;
	
	@Autowired
	public PacienteController (PacienteService service, PacienteRepository repository) {
		this.service = service;
		this.repository = repository;
	}
	

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoPaciente> cadastrar (@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
		Paciente paciente = service.cadastroPaciente(dados);
		URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente (paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPaciente>>listar(
			@PageableDefault(size=10,sort={"nome"})Pageable paginacao){
		Page<DadosListagemPaciente> page = service.listarPacientesComPaginacao(paginacao);
		return ResponseEntity.ok(page);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoPaciente> atualizar (@PathVariable Long id,
			@RequestBody @Valid DadosAtualizacaoPaciente dados){
		DadosDetalhamentoPaciente paciente = service.atualizarPaciente(id, dados);
		return ResponseEntity.ok(paciente);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
		service.deletarMedico(id);
		//var paciente = repository.getReferenceById(id);
		//paciente.excluir();
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoPaciente> DetalharPaciente (@PathVariable Long id) {
		var paciente = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
	}
}
