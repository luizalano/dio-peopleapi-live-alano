package br.com.luigipietro.personapi.service;

import br.com.luigipietro.personapi.dto.MessageResponseDTO;
import br.com.luigipietro.personapi.entity.Person;
import br.com.luigipietro.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        // @RequestBody -> informa que o objeto vem da requisição POST
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Criada pessoa com o ID: " + savedPerson.getId())
                .build();
    }

}
