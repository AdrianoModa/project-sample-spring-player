package br.spring.exemplo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import br.spring.exemplo.model.Jogador;
import br.spring.exemplo.repository.JogadorRepository;

@RestController
@RequestMapping("/jogador")
@CrossOrigin
public class JogadorController {

	@Autowired
	private JogadorRepository jogadorRepository;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<Jogador>> listarTodos(){
		jogadorRepository.findAll();
		return new ResponseEntity<List<Jogador>>(jogadorRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Jogador> listarPorId(@PathVariable Long id){
		Jogador jogador = jogadorRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado"));
		return new ResponseEntity<Jogador>(jogador, HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Jogador> adicionarJogador(@RequestBody @Valid Jogador jogador){
		Jogador jogadorObjeto = jogadorRepository.save(jogador);
		return new ResponseEntity<Jogador>(jogadorObjeto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Jogador> atualizarJogador(@PathVariable Long id, @RequestBody Jogador jogador){
		Jogador jogadorExistente = jogadorRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id não encontrado"));
		BeanUtils.copyProperties(jogador, jogadorExistente, "id");
		return new ResponseEntity<Jogador>(jogadorRepository.save(jogadorExistente), HttpStatus.valueOf(200));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Jogador> deletarJogador(@PathVariable Long id){
		Jogador jogadorExistente = jogadorRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Id não encontrado"));
		jogadorRepository.delete(jogadorExistente);
		return new ResponseEntity<Jogador>(jogadorExistente, HttpStatus.valueOf(204));
	}

}