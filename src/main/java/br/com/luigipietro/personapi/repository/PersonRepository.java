package br.com.luigipietro.personapi.repository;

import br.com.luigipietro.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/*
JpaRepository precisa saber qual o tipo da entidade (Person) e o tipo do id (Long)
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByFirstName (String firstName);
    List<Person> findByFirstNameIgnoreCaseContaining (String firstName);
}
