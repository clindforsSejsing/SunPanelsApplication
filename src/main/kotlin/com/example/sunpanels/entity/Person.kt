package com.example.sunpanels.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

//https://www.youtube.com/watch?v=kZGtO23Wr3E
//kl 23 min in

@Entity
@Table(name="person")
class Person {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) var id: Long? = null
    @Column (length= 75, unique = true, nullable = false)
    lateinit var email: String
    @Column (length= 75, nullable = false)
    lateinit var name : String
    @Column (length= 75, nullable = false)
     var password : String = ""
        @JsonIgnore
        get() = field
        set(value){
            val passwordEncoder = BCryptPasswordEncoder()
          field = passwordEncoder.encode(value)
        }
    fun comparePassword(password:String): Boolean{
        return BCryptPasswordEncoder().matches(password, this.password)
    }

    @JsonIgnore
    @OneToMany(mappedBy = "person",  fetch = FetchType.EAGER)
    var notes : List<Note> = ArrayList<Note>()

}