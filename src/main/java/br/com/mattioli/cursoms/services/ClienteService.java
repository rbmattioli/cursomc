package br.com.mattioli.cursoms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.mattioli.cursoms.domain.Cliente;
import br.com.mattioli.cursoms.dto.ClienteDTO;
import br.com.mattioli.cursoms.repositories.ClienteRepository;
import br.com.mattioli.cursoms.services.exceptions.DataIntegrityException;
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
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);

	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possível excluir um cliente que possiu pedidos");

		}

	}

	public List<Cliente> findAll() {

		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction,  String orderBy) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO (ClienteDTO objDTO) {
		
		return new Cliente(objDTO.getId(),objDTO.getNome(),objDTO.getEmail(),null,null);
		
	}
	
	private void updateData (Cliente newobj, Cliente obj) {
		newobj.setNome(obj.getNome());
		newobj.setEmail(obj.getEmail());
	}

}
