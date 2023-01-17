package com.example.sunpanels.controller

import com.example.sunpanels.entity.Person
import com.example.sunpanels.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/person")
class PersonController(var personService: PersonService) {

    @PostMapping("/add")
    fun addUser(@RequestBody person: Person): Any {
        if(personService.savePerson(person) != null){
            return ResponseEntity(personService.savePerson(person), HttpStatus.CREATED)
        }else return ResponseEntity("Failed to save email, try again",HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/{personId}")
    fun getUser(@PathVariable personId: Long): ResponseEntity<Optional<Person>> {
        val person: Optional<Person> = personService.getPerson(personId)
        return ResponseEntity(person, HttpStatus.OK)
    }

    @GetMapping("/all")
    fun getUser(): ResponseEntity<MutableIterable<Person>> {
        return ResponseEntity(personService.getPersons(), HttpStatus.OK)
    }

}