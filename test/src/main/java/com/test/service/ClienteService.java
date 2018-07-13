package com.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Cliente;
import com.test.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public void atualizar(Long id, Cliente c) {
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		Cliente cliente = clienteOptional.get();
		if(!c.getNome().isEmpty())
			cliente.setNome(c.getNome());
		if(c.getIdade() != null)
			cliente.setIdade(c.getIdade());
		clienteRepository.flush();
	}
	
	public Optional<Cliente> buscarPorId(Long id) {		
		return clienteRepository.findById(id);
	}
}
