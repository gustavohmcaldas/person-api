package com.gustavohmcaldas.personapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gustavohmcaldas.personapi.dto.request.PersonDTO;
import com.gustavohmcaldas.personapi.dto.response.MessageResponseDTO;
import com.gustavohmcaldas.personapi.exception.PersonNotFoundException;
import com.gustavohmcaldas.personapi.service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
public class PersonController {
	
	private final PersonService personService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO insertPerson(@RequestBody @Valid PersonDTO personDTO) {
		return personService.insertPerson(personDTO);
	}
	
	@GetMapping
	public List<PersonDTO> listAll() {
		return personService.listAll();
	}
	
	@GetMapping("/{id}")
	public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
		return personService.findById(id);
	}
	
	@PutMapping("/{id}")
	public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
		return personService.updateById(id, personDTO);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
		personService.delete(id);
	}
}
