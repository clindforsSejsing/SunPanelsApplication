package com.example.sunpanels.service

import com.example.sunpanels.entity.Person
import com.example.sunpanels.repository.PersonRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService(var personRepository: PersonRepository) {


    fun savePerson(person: Person): Person {
        return personRepository.save(person)
    }

    fun getPerson(personId: Long): Optional<Person> {
        return personRepository.findById(personId)
    }

    fun getPersons(): MutableIterable<Person> {
        return personRepository.findAll()
    }
}