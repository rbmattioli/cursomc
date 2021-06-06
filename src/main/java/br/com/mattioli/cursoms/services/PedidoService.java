package br.com.mattioli.cursoms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mattioli.cursoms.domain.Pedido;
import br.com.mattioli.cursoms.repositories.PedidoRepository;
import br.com.mattioli.cursoms.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Pedido find(Integer id) {

		Pedido obj = repo.getById(id);

		if (obj == null) {

			throw new ObjectNotFoundException(
					"objeto não encontrado! Id: " + id + ",Tipo: " + Pedido.class.getName());

		}
		return obj;

	}

}
