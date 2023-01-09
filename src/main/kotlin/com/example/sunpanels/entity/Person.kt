package com.example.sunpanels.entity

import jakarta.persistence.*


@Entity
@Table(name="person")
class Person {

    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) var id: Long? = null
    @Column (length= 75, unique = true, nullable = false)
    lateinit var email: String



  /*  @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER,mappedBy= "person")
     private lateinit var notes : ArrayList<Note>*/
}