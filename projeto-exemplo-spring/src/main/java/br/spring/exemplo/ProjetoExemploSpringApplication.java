package br.spring.exemplo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.spring.exemplo.model.Jogador;
import br.spring.exemplo.repository.JogadorRepository;

@SpringBootApplication
public class ProjetoExemploSpringApplication implements CommandLineRunner {
	
	@Autowired
	JogadorRepository jogadorRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetoExemploSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Jogador jogador = new Jogador();
		jogador.setId(1L);
		jogador.setNome("Nickolas Fire");
		jogador.setNacionalidade("Austrália");
		jogador.setIdade(22);
		jogador.setGolpes("Ataque de Fogo, Sopro de Fogo, Poder de Fogo, Furacão de Fogo");
		jogador.setObjetivo("Vingar a morte de um amigo da família");
		
		Jogador jogador2 = new Jogador();
		jogador2.setNome("Davi Flash");
		jogador2.setNacionalidade("Alemanha");
		jogador2.setIdade(22);
		jogador2.setGolpes("Teleporte, Trovao de Raios, Super Velocidade, Circulo de Átomos");
		jogador2.setObjetivo("Vingar a morte dos pais");
		
		Jogador jogador3 = new Jogador();
		jogador3.setNome("Bruna Arqueira Pink");
		jogador3.setNacionalidade("Canadá");
		jogador3.setIdade(25);
		jogador3.setGolpes("Flecha de Fogo, Flecha de Gelo, Flecha de Explosão, Super Soco");
		jogador3.setObjetivo("Salva a cidade de Manaus");
		
		jogadorRepository.saveAll(Arrays.asList(jogador, jogador2, jogador3));
		
		DateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy", new Locale("pt", "BR"));
		Calendar calendar = Calendar.getInstance();
		
		
		System.out.println("O Projeto está rodando... às " + sdf.format(calendar.getTime()));
		
	}
}