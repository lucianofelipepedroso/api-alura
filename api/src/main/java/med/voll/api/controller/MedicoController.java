package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
import med.voll.api.domain.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.DadosListagemMedicos;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
@EnableMethodSecurity(securedEnabled = true)
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity  cadastrar(@RequestBody @Valid DadosCadastroMedico dados,UriComponentsBuilder uriComponentsBuilder) {
		var medico = new Medico(dados);
		repository.save(medico);
		var uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
}

	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedicos>> listar(@PageableDefault(size = 10, sort = { "nome" }) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicos::new);
		return ResponseEntity.ok(page);
		
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar (@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
		
	}
	

	@DeleteMapping("/{id}")
	@Transactional
	@Secured("ROLE_ADMIN")
	public ResponseEntity  excluir(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		medico.excluir();
		return ResponseEntity.noContent().build();
	}

	// @GetMapping
	// public Page<DadosListagemMedicos> listar(@PageableDefault(size = 10, sort = {
	// "nome" }) Pageable paginacao) {
	// return repository.findAll(paginacao).map(DadosListagemMedicos::new);
	// }

	// @DeleteMapping("/{id}")
	// @Transactional
	// public void excluir(@PathVariable Long id) {
	// repository.deleteById(id);
	// }

	// public List<DadosListagemMedicos> listar(){
	// return repository.findAll().stream().map(DadosListagemMedicos::new).toList();
	// }

}
