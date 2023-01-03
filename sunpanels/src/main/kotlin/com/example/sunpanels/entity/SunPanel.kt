package com.example.sunpanels.entity

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="sunpanel")
class SunPanel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long = 0
    private var areaOfPanels : Float = 0.0f
    private var elPrice : Float = 0.0f

    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.EAGER,mappedBy= "sunpanel")
    private lateinit var sunpanelnotes : ArrayList<Note>

}