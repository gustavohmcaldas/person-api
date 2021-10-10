package com.gustavohmcaldas.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gustavohmcaldas.personapi.domain.Phone;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
