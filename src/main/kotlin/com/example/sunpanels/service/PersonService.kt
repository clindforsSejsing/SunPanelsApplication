package com.example.sunpanels.service

import com.example.sunpanels.entity.Person
import com.example.sunpanels.repository.PersonRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonService(var personRepository: PersonRepository) {

    /**
     * @param person gets input from user, unic email.
     * @return RCF standard (5233) allows all characters in email but not pipe character and single
     * quote, as these are potential for SQL injection.
     * */
    fun savePerson(person: Person): Person? {
       val pattern  = "[a-zA-Z0-9_!#\$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\$".toRegex()
       val found = pattern.find(person.email)

    if(pattern.containsMatchIn(found.toString())){
         return personRepository.save(person)
    }else println("wrong input email")//error handling
    return null
    }

    fun getPerson(personId: Long): Optional<Person> {
        return personRepository.findById(personId)
    }

    fun getPersons(): MutableIterable<Person> {
        return personRepository.findAll()
    }
}