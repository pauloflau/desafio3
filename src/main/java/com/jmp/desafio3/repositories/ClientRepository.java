package com.jmp.desafio3.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jmp.desafio3.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
	List<Client> findAll();
}
