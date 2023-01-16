package com.example.sunpanels.service

import com.example.sunpanels.entity.SunPanel
import com.example.sunpanels.repository.SunPanelRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class SunPanelService(var sunPanelRepository: SunPanelRepository) {

    fun saveSunPanel(sunPanel: SunPanel): SunPanel? {
        val s = sunPanel
        val caclArea: Any = calculateArea(sunPanel.amountOfPanels)
        s.areaOfPanels = caclArea as Float
        return sunPanelRepository.save(sunPanel)
    }

    fun getSunPanel(sunpanelId: Long): Optional<SunPanel> {
        return sunPanelRepository.findById(sunpanelId)
    }

    fun getAllSunPanels(): MutableIterable<SunPanel> {
        return sunPanelRepository.findAll()
    }

    //make patch work
    fun updateASunPanel(sunpanelId: Long, sunPanel: SunPanel): Any {
        val findingRightId: SunPanel = sunPanelRepository.findById(sunpanelId).get()

        if ((findingRightId.id)?.equals(sunpanelId) == true) {
            println("getting hits.........yes!")
            val s = sunPanel
            val caclArea: Any = calculateArea(sunPanel.amountOfPanels)
            s.areaOfPanels = caclArea as Float
            s.id = sunpanelId
            return sunPanelRepository.save(sunPanel)

        } else println("noway, didnt update " + sunPanel)//throw error

        return sunPanelRepository.findById(sunpanelId)

    }

    //add docs: 1 panel = 1.7 m x 1 m. Calculate and return area.
    fun calculateArea(nmbOfPanels: Int): Float {
        val areaOfAmountOfPanels: Float = (nmbOfPanels * (1.7 * 1)).toFloat()
        return areaOfAmountOfPanels
    }
}


