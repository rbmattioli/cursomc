package br.com.mattioli.cursoms;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mattioli.cursoms.domain.Categoria;
import br.com.mattioli.cursoms.domain.Cidade;
import br.com.mattioli.cursoms.domain.Cliente;
import br.com.mattioli.cursoms.domain.Endereco;
import br.com.mattioli.cursoms.domain.Estado;
import br.com.mattioli.cursoms.domain.Produto;
import br.com.mattioli.cursoms.domain.enums.TipoCliente;
import br.com.mattioli.cursoms.repositories.CategoriaRepository;
import br.com.mattioli.cursoms.repositories.CidadeRepository;
import br.com.mattioli.cursoms.repositories.ClienteRepository;
import br.com.mattioli.cursoms.repositories.EnderecoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	
	
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
		
		Cliente cli1 = new Cliente(null,"maria silva","maria@gmail.com","09596142739",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27110430","984580322"));
		
		Endereco e1 = new Endereco(null,"rua das floes","300","apto 302","jardim","31127783",cli1,c1);
		Endereco e2 = new Endereco(null,"rua das palmeiras","200","apto 102","centro","994458832",cli1,c2);
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));

	}
	
	
	

}
