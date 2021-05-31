package br.com.mattioli.cursoms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mattioli.cursoms.domain.Categoria;
import br.com.mattioli.cursoms.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {

		Categoria obj = repo.getById(id);

		return obj;

	}

}
