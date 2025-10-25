package com.jmp.desafio3.dtos;

import java.time.LocalDate;
import java.util.Objects;

import com.jmp.desafio3.entities.Client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

public class ClientDto {
	
	private Long id;

	@NotBlank(message="campo requerido")
	private String name;
	
	@NotBlank(message="campo requerido")
	private String cpf;
	
	@NotNull(message="campo requerido")
	@Positive(message = "o campo deve ser positivo")
	private Double income;

	@NotNull(message="campo requerido")
    @PastOrPresent(message = "A data de nascimento não pode ser no futuro")
	private LocalDate birthDate;
	
	@Positive(message = "o campo deve ser positivo")
	private Integer children;
	
	public ClientDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.income = income;
		this.birthDate = birthDate;
		this.children = children;
	}
	
	public ClientDto(Client entity) {
		id = entity.getId();
		name = entity.getName();
		cpf = entity.getCpf();
		income = entity.getIncome();
		birthDate = entity.getBirthDate();
		children = entity.getChildren();
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public Double getIncome() {
		return income;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Integer getChildren() {
		return children;
	}
	


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientDto other = (ClientDto) obj;
		return Objects.equals(id, other.id);
	}	
	

	
}
