package br.com.mattioli.cursoms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.mattioli.cursoms.domain.Categoria;
import br.com.mattioli.cursoms.dto.CategoriaDTO;
import br.com.mattioli.cursoms.repositories.CategoriaRepository;
import br.com.mattioli.cursoms.services.exceptions.DataIntegrityException;
import br.com.mattioli.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {

		Categoria obj = repo.getById(id);

		if (obj == null) {

			throw new ObjectNotFoundException(
					"objeto não encontrado! Id: " + id + ",Tipo: " + Categoria.class.getName());

		}
		return obj;

	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);

	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possível excluir uma categoria que possiu produtos");

		}

	}

	public List<Categoria> findAll() {

		return repo.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String direction,  String orderBy) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO (CategoriaDTO objDTO) {
		
		return new Categoria(objDTO.getId(), objDTO.getNome());
		
	}
	

}
