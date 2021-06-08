package br.com.mattioli.cursoms.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.mattioli.cursoms.domain.Categoria;
import br.com.mattioli.cursoms.domain.Produto;
import br.com.mattioli.cursoms.repositories.CategoriaRepository;
import br.com.mattioli.cursoms.repositories.ProdutoRepository;
import br.com.mattioli.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	

	@Autowired
	private CategoriaRepository categoriaRepo;

	public Produto find(Integer id) {

		Produto obj = repo.getById(id);

		if (obj == null) {

			throw new ObjectNotFoundException(
					"objeto não encontrado! Id: " + id + ",Tipo: " + Categoria.class.getName());

		}
		return obj;

	}
	
	public Page<Produto> search (String nome, List<Integer> ids, Integer page, Integer linesPerPage, String direction,  String orderBy){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias,pageRequest);
	}
	

}
