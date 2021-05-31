# dio-peopleapi-live-alano
<H1>Bootcamp Java Developer</H1>
<H2>Projeto de cadastro de pessoas e telefones do curso Desenvolvendo um sistema de gerenciamento de pessoas em API REST com Spring Boot </H2>

Neste projeto foram usados os recursos:

* Maven
* JPA
* Hibernate
* Spring
* H2 - database em memória para testes

Além das funcionalidades explicadas no cursos, foram adicionados os métodos de busca de pessoas por nome exato - findByFirstName - e por like - findByFirstNameIgnoreCaseContaining.

Para isso a interface PerosRepository ganhou dois novos métodos abstratos:

    
   
    public interface PersonRepository extends JpaRepository<Person, Long> {
        List<Person> findByFirstName (String firstName);
        List<Person> findByFirstNameIgnoreCaseContaining (String firstName);
    }





    
    

Os métodos foram implementados na classe PersonService e chamados no PersonController

    @GetMapping("/name/{name}")
    public List<PersonDTO> findByName(@PathVariable String name) throws PersonNotFoundException {
        return personService.findByFirstName(name);
    }

    @GetMapping("/like/{name}")
    public List<PersonDTO> findByNameIgnoreCaseContanining(@PathVariable String name) throws PersonNotFoundException {
        return personService.findByFirstNameIgnoreCaseContaining(name);
    }


