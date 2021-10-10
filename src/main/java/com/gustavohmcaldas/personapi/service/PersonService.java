package com.gustavohmcaldas.personapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gustavohmcaldas.personapi.domain.Person;
import com.gustavohmcaldas.personapi.dto.request.PersonDTO;
import com.gustavohmcaldas.personapi.dto.response.MessageResponseDTO;
import com.gustavohmcaldas.personapi.exception.PersonNotFoundException;
import com.gustavohmcaldas.personapi.mapper.PersonMapper;
import com.gustavohmcaldas.personapi.repository.PersonRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

	private final PersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	public MessageResponseDTO insertPerson(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);
		
		Person savedPerson = personRepository.save(personToSave);
		return createMessageResponse(savedPerson.getId(), "Saved person with ID: ");
	}

	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {		
		Person person = verifyIfExists(id);
		return personMapper.toDTO(person);
	}
	
	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);
		
		Person personToUpdate = personMapper.toModel(personDTO);
		
		Person updatedPerson = personRepository.save(personToUpdate);
		return createMessageResponse(updatedPerson.getId(), "Updated person with ID: ");
	}	

	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		
		personRepository.deleteById(id);
	}

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
	}
	
	private MessageResponseDTO createMessageResponse(Long id, String msg) {
		return MessageResponseDTO
				.builder()
				.message(msg + id)
				.build();
	}
}
