package com.jmp.desafio3.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jmp.desafio3.dtos.ClientDto;
import com.jmp.desafio3.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	private ClientService service;

	public ClientController(ClientService service) {
		this.service = service;
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}") 
	public ResponseEntity<ClientDto> update(@PathVariable Long id, @RequestBody @Valid ClientDto dto) {
		dto = service.update(id, dto);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping
	public ResponseEntity<ClientDto> insert(@RequestBody @Valid ClientDto dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);		
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
		ClientDto dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping
	public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable){
		return ResponseEntity.ok(service.findAll(pageable));		
	}
}
