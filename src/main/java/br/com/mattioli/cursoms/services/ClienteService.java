package br.com.mattioli.cursoms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mattioli.cursoms.domain.Cidade;
import br.com.mattioli.cursoms.domain.Cliente;
import br.com.mattioli.cursoms.domain.Endereco;
import br.com.mattioli.cursoms.domain.enums.TipoCliente;
import br.com.mattioli.cursoms.dto.ClienteDTO;
import br.com.mattioli.cursoms.dto.ClienteNewDTO;
import br.com.mattioli.cursoms.repositories.CidadeRepository;
import br.com.mattioli.cursoms.repositories.ClienteRepository;
import br.com.mattioli.cursoms.repositories.EnderecoRepository;
import br.com.mattioli.cursoms.services.exceptions.DataIntegrityException;
import br.com.mattioli.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;

	public Cliente find(Integer id) {

		Cliente obj = repo.getById(id);

		if (obj == null) {

			throw new ObjectNotFoundException("objeto não encontrado! Id: " + id + ",Tipo: " + Cliente.class.getName());

		}
		return obj;

	}

	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepo.saveAll(obj.getEnderecos());
		return obj;
		 
	}

	@Transactional
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

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {

		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);

	}

	@SuppressWarnings("deprecation")
	public Cliente fromDTO(ClienteNewDTO objDTO) {

		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(),
				TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = cidadeRepo.getOne(objDTO.getCidadeId());
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(),
				objDTO.getBairro(), objDTO.getCep(), cli, cid);

		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());

		if (objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());

		}
		if (objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());

		}

		return cli;
	}

	private void updateData(Cliente newobj, Cliente obj) {
		newobj.setNome(obj.getNome());
		newobj.setEmail(obj.getEmail());
	}

}
