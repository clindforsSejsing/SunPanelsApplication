package com.example.sunpanels.repository

import com.example.sunpanels.entity.Person
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository : CrudRepository<Person, Long> {
fun findByEmail(email : String): Person?
}