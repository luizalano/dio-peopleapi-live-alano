package br.com.luigipietro.personapi.controller;

import br.com.luigipietro.personapi.dto.MessageResponseDTO;
import br.com.luigipietro.personapi.entity.Person;
import br.com.luigipietro.personapi.repository.PersonRepository;
import br.com.luigipietro.personapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody Person person){
        return personService.createPerson(person);
    }
}

/*

Usar o postman:

{
    "firstName": "Luiz",
    "lastName": "Alano",
    "cpf": "123.456.789-22",
    "phones": [
        {
            "type": "MOBILE",
            "number": "41 99997-0486"
        },
        {
            "type": "HOME",
            "number": "41 3357-*78-16"
        }
    ]
}

 */