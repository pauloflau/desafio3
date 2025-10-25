package com.jmp.desafio3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jmp.desafio3.dtos.ClientDto;
import com.jmp.desafio3.entities.Client;
import com.jmp.desafio3.exceptions.EntityNotFoundException;
import com.jmp.desafio3.exceptions.ResourceNotFoundException;
import com.jmp.desafio3.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional
	public void delete(Long id) {
		//comparo com um método utilitário para validar a existência do cliente
		if (!repository.existsById(id)) {
	        throw new EntityNotFoundException("Cliente nao encontrado: " + id);
	    }

		repository.deleteById(id);
	}
	
	@Transactional
	public ClientDto update(Long id, ClientDto dto) {
	    //comparo com um método utilitário para validar a existência do cliente
		if (!repository.existsById(id)) {
	        throw new EntityNotFoundException("Cliente nao encontrado: " + id);
	    }
	    
		Client entity = repository.getReferenceById(id);
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDto(entity);
	}
	
	@Transactional
	public ClientDto insert(ClientDto dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);

		entity = repository.save(entity);
		return new ClientDto(entity);
	}

	@Transactional(readOnly = true)
	public ClientDto findById(Long id) {
		Client entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso nao encontrado"));
		return new ClientDto(entity);
	}

	@Transactional(readOnly = true)
	public Page<ClientDto> findAll(Pageable pageable) {
		Page<Client> clients = repository.findAll(pageable);
		List<ClientDto> result = new ArrayList<>();

		for (Client entity : clients) {
			ClientDto dto = new ClientDto(entity);
			result.add(dto);
		}
		return new PageImpl<>(result, pageable, clients.getTotalElements());
	}

	private void copyDtoToEntity(ClientDto dto, Client entity) {
		//entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
	

}
