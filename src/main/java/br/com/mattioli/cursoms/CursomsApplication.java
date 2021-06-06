package br.com.mattioli.cursoms;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mattioli.cursoms.domain.Categoria;
import br.com.mattioli.cursoms.domain.Cidade;
import br.com.mattioli.cursoms.domain.Estado;
import br.com.mattioli.cursoms.domain.Produto;
import br.com.mattioli.cursoms.repositories.CategoriaRepository;
import br.com.mattioli.cursoms.repositories.CidadeRepository;
import br.com.mattioli.cursoms.repositories.EstadoRepository;
import br.com.mattioli.cursoms.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomsApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		Produto p1 = new Produto(null,"computador",2000.00);
		Produto p2 = new Produto(null,"impressora",800.00);
		Produto p3 = new Produto(null,"teclado",100.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"minas gerais");
		Estado est2 = new Estado(null,"sao paulo");
		
		Cidade c1 = new Cidade(null,"uberlandia",est1);
		Cidade c2 = new Cidade(null,"sao paulo",est2);
		Cidade c3 = new Cidade(null,"campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
	}
	
	
	

}
