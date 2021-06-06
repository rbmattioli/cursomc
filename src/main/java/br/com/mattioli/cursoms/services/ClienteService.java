package br.com.mattioli.cursoms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mattioli.cursoms.domain.Cliente;
import br.com.mattioli.cursoms.repositories.ClienteRepository;
import br.com.mattioli.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {

		Cliente obj = repo.getById(id);

		if (obj == null) {

			throw new ObjectNotFoundException(
					"objeto não encontrado! Id: " + id + ",Tipo: " + Cliente.class.getName());

		}
		return obj;

	}

}
