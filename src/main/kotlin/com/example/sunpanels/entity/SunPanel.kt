package com.example.sunpanels.entity

import jakarta.persistence.*

@Entity
@Table(name="sunpanel")
class SunPanel {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY) var id: Long? = null
     var areaOfPanels : Float = 0.0f
     var elPrice : Float = 0.0f
     var amountOfPanels : Int = 0

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER,mappedBy= "sunpanel")
      var sunpanelnotes : List<Note>? = null


}