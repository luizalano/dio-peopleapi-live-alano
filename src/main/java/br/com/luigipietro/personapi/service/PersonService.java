package br.com.luigipietro.personapi.service;

import br.com.luigipietro.personapi.dto.reponse.MessageResponseDTO;
import br.com.luigipietro.personapi.dto.request.PersonDTO;
import br.com.luigipietro.personapi.entity.Person;
import br.com.luigipietro.personapi.exception.PersonNotFoundException;
import br.com.luigipietro.personapi.mapper.PersonMapper;
import br.com.luigipietro.personapi.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor (onConstructor = @__(@Autowired))
public class PersonService {
    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    public List<PersonDTO> listAll() {
        List<Person> allPerson = personRepository.findAll();
        return allPerson.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);
        Person savedPerson = personRepository.save(personToSave);

        return createMessageResponse(savedPerson.getId(), "Criada pessoa com o ID: ");
    }

    public void delete(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    public List<PersonDTO> findByFirstName(String name) {
        List<Person> allPerson = personRepository.findByFirstName(name);
        return allPerson.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<PersonDTO> findByFirstNameIgnoreCaseContaining(String name) {
        List<Person> allPerson = personRepository.findByFirstNameIgnoreCaseContaining(name);
        return allPerson.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
       Person person = verifyIfExists(id);
       return personMapper.toDTO(person);
/*        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new PersonNotFoundException(id);
        }
        return personMapper.toDTO(optionalPerson.get());*/
    }

    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
        verifyIfExists(id);
        Person personToSave = personMapper.toModel(personDTO);
        Person updatedPerson = personRepository.save(personToSave);

        return createMessageResponse(updatedPerson.getId(), "Alterada a pessoa com o ID: ");
    }

    private MessageResponseDTO createMessageResponse(Long id, String s) {
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

}
