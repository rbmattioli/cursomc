package br.com.mattioli.cursoms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mattioli.cursoms.domain.Categoria;
import br.com.mattioli.cursoms.repositories.CategoriaRepository;
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

}
