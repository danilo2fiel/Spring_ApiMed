package med.voll.apiMedico.controller;


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

import jakarta.validation.Valid;
import med.voll.apiMedico.medico.DadosAtualizacaoMedico;
import med.voll.apiMedico.medico.DadosCadastroMedico;
import med.voll.apiMedico.medico.DadosListagemMedico;
import med.voll.apiMedico.medico.Medico;
import med.voll.apiMedico.medico.MedicoRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("medicos")
public class MedicoController {

	@Autowired
	MedicoRepository repository ;	
	
	@PostMapping
	@Transactional
	public ResponseEntity<Medico> cadastroMedico (@RequestBody @Valid DadosCadastroMedico dados) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(new Medico (dados))) ;
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listarMedico (@PageableDefault(size=10,sort= {"nome"})Pageable paginacao){
		return ResponseEntity.status(HttpStatus.OK).body(repository.findAll(paginacao).map(DadosListagemMedico::new));
	}
			
	@PutMapping
	@Transactional
	public ResponseEntity<DadosAtualizacaoMedico> atualisarMedico (@RequestBody DadosAtualizacaoMedico dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		return ResponseEntity.status(HttpStatus.OK).body(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<String> deletarMedico (@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("deletado com sucesso");
	}
	
}
