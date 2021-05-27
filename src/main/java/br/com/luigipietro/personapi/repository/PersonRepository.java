package br.com.luigipietro.personapi.repository;

import br.com.luigipietro.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/*
JpaRepository precisa saber qual o tipo da entidade (Person) e o tipo do id (Long)
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
}
