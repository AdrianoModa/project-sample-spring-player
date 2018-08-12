package br.spring.exemplo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.spring.exemplo.model.Jogador;

@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Long>, CrudRepository<Jogador, Long> {
	
	public Jogador findByNome(String nome);

}
