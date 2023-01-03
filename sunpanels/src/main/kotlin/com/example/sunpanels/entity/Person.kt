package com.example.sunpanels.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Getter
@Setter
@Table(name="person")
@AllArgsConstructor
@NoArgsConstructor
class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long = 0
    @Column (length= 75, nullable = false)
    private var email: String = ""

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER,mappedBy= "person")
     private lateinit var notes : ArrayList<Note>
}