package com.example.sunpanels.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.time.LocalDateTime

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="note")
class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long = 0

    private lateinit var sunrise : LocalDateTime
    private lateinit var sunset : LocalDateTime

    @ManyToOne
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private var person = Person()

    @ManyToOne
    @JoinColumn(name="sunpanels_id", referencedColumnName = "id")
    private var sunpanel = SunPanel()

}