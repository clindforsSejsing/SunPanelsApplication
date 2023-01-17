package com.example.sunpanels.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name="sunpanel")
class SunPanel {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) var id: Long? = null
     var areaOfPanels : Float = 0.0f
     var sekPerKwh : Float = 0.0f
     var amountOfPanels : Int = 0

    @JsonIgnore
    @OneToMany(mappedBy = "sunpanel",fetch = FetchType.EAGER)
    var notes : List<Note> = ArrayList<Note>()

}
