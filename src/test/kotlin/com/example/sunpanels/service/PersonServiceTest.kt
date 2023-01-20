package com.example.sunpanels.service

import com.example.sunpanels.entity.Person
import com.example.sunpanels.repository.PersonRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class PersonServiceTest {

    private val personRepository: PersonRepository = mockk()
    private val personService = PersonService(personRepository)
    private val person = Person()
    private val list = ArrayList<Person>()


    @Test
    fun getPerson_andReturnPerson() {
        //given
        every { personRepository.findById(1L) } returns Optional.of(person)
        //when
        val result = personService.getPerson(1L)
        //then
        verify(exactly = 1) { personRepository.findById(1L) }
        Assertions.assertEquals(Optional.of(person), result)
    }

    @Test
    fun savePerson_andReturnPerson() {
        //given
        person.email = "Kalle_anka@mail.com"
        every { personRepository.save(person) } returns person
        //when
        val result = personService.savePerson(person)
        //then
        verify(exactly = 1) { personRepository.save(person) }
        Assertions.assertEquals(person, result)
    }

    @Test
    fun getPersons_andReturnListOfAllPersons() {
        //given
        every { personRepository.findAll() } returns list
        //when
        val result = personService.getPersons()
        //then
        verify(exactly = 1) { personRepository.findAll() }
        Assertions.assertEquals(list, result)
    }
}
