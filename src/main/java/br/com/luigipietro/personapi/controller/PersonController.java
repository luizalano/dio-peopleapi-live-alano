package br.com.luigipietro.personapi.controller;

import br.com.luigipietro.personapi.dto.reponse.MessageResponseDTO;
import br.com.luigipietro.personapi.dto.request.PersonDTO;
import br.com.luigipietro.personapi.entity.Person;
import br.com.luigipietro.personapi.exception.PersonNotFoundException;
import br.com.luigipietro.personapi.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/people")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

    /*
    O construtor pode ser eliminado, pela anotação:
            @AllArgsConstructor(onConstructor = @__(@Autowired))
    @Autowired
    public PersonController(PersonService personService) {

        this.personService = personService;
    }
    */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO){
        // @RequestBody -> informa que o objeto vem da requisição POST
        // A anotação @Valid indica que o objeto deve ser validado conforme as regras da
        // classe PersonDTO
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll(){
        return personService.listAll();

    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {
        return personService.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<PersonDTO> findByName(@PathVariable String name) throws PersonNotFoundException {
        return personService.findByFirstName(name);
    }

    @GetMapping("/like/{name}")
    public List<PersonDTO> findByNameIgnoreCaseContanining(@PathVariable String name) throws PersonNotFoundException {
        return personService.findByFirstNameIgnoreCaseContaining(name);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id
            , @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {
        return personService.updateById(id, personDTO);
    }

}

/*

Usar o postman:

{
    "firstName": "Luiz",
    "lastName": "Alano",
    "birthDate": "01/01/1966",
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

select p.first_name, p.last_name, p.birth_date, ph.number, ph.type
from person as p
join person_phones as pp on p.id = pp.person_id
join phone as ph on pp.phones_id = ph.id
order by p.last_name, p.first_name;

 */