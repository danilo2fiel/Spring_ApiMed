package med.voll.apiMedico.controller;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

import jakarta.validation.Valid;
import med.voll.apiMedico.domain.medico.DadosAtualizacaoMedico;
import med.voll.apiMedico.domain.medico.DadosCadastroMedico;
import med.voll.apiMedico.domain.medico.DadosDetalhamentoMedico;
import med.voll.apiMedico.domain.medico.DadosListagemMedico;
import med.voll.apiMedico.domain.medico.Medico;
import med.voll.apiMedico.domain.medico.MedicoRepository;
import med.voll.apiMedico.service.MedicoService;

@RestController
@CrossOrigin("*")
@RequestMapping("medicos")
public class MedicoController {

	private final MedicoService service;

	@Autowired
	public MedicoController(MedicoService service) {
		this.service = service;
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedico> cadastroMedico(@RequestBody @Valid DadosCadastroMedico dados,
			UriComponentsBuilder uriBuilder) {
		Medico medico = service.cadastroMedico(dados);
		URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}
		
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listarMedico(
			@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		Page<DadosListagemMedico> medicosPage = service.listarMedicosComPaginacao(paginacao);
		return ResponseEntity.ok(medicosPage);
	}
			
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedico> atualisarMedico(@PathVariable Long id,
			@RequestBody DadosAtualizacaoMedico dados) {
		DadosDetalhamentoMedico dadosDetalhamentoMedico = service.atualizarMedico(id, dados);
		return ResponseEntity.ok(dadosDetalhamentoMedico);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deletarMedico (@PathVariable Long id) {
		service.deletarMedico(id);  
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoMedico> detalharMedico (@PathVariable Long id) {
		DadosDetalhamentoMedico medico = service.listaUmMedico(id);
		return ResponseEntity.ok(medico);
	}
	
}
