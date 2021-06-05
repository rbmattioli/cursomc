package br.com.mattioli.cursoms;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mattioli.cursoms.domain.Categoria;
import br.com.mattioli.cursoms.repositories.CategoriaRepository;

@SpringBootApplication
public class CursomsApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	}
	
	
	

}
