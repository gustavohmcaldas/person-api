package com.gustavohmcaldas.personapi.dto.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.gustavohmcaldas.personapi.domain.Phone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

	private Long id;
	
	@NotEmpty
	@Size(min = 2, max = 50, message = "First name must be between 2 and 50 caracters.")
	private String firstName;
	
	@NotEmpty
	@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 caracters.")
	private String lastName;

	@NotEmpty
	@Email
	private String email;

	private String birthDate;

	@Valid
	@NotEmpty
	private List<Phone> phones;
}
