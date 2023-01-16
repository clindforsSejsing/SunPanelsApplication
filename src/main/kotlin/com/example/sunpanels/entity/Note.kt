package com.example.sunpanels.entity

import jakarta.persistence.*
import java.time.LocalDateTime



@Entity
@Table(name="note")
class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
    var sunrise : LocalDateTime? = null
    var sunset : LocalDateTime? = null
    var incomeSek : Float = 0.0f
    var nmbOfSunHours : Long? = 0

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name="person_id", referencedColumnName = "id")
     var person : Person? = null

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name="sunpanel_id", referencedColumnName = "id")
     var sunpanel : SunPanel? = null

}