package com.gustavohmcaldas.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavohmcaldas.personapi.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
