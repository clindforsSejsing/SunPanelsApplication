package com.example.sunpanels.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*


@Entity
@Table(name="person")
class Person {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) var id: Long? = null
    @Column (length= 75, unique = true, nullable = false)
    lateinit var email: String

    @JsonIgnore
    @OneToMany(mappedBy = "person",  fetch = FetchType.EAGER)
    var notes : List<Note> = ArrayList<Note>()

}