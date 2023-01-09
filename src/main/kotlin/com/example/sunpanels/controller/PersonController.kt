package com.example.sunpanels.controller

import com.example.sunpanels.entity.Person
import com.example.sunpanels.service.PersonServiceImp
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/persons")
class PersonController (var personService : PersonServiceImp){

    @PostMapping("/add")
    fun addUser(@RequestBody person: Person): ResponseEntity<Person>{
        return ResponseEntity.ok(personService.savePerson(person))
    }

    @GetMapping("/{personId}")
    fun getUser(@PathVariable personId: Long) : ResponseEntity<Optional<Person>> {
        val person : Optional<Person> = personService.getPerson(personId)
        return ResponseEntity(person, HttpStatus.OK)
    }

    @GetMapping("/all")
    fun getUser() : ResponseEntity<MutableIterable<Person>> {
        return ResponseEntity(personService.getPersons(), HttpStatus.OK)
    }

}