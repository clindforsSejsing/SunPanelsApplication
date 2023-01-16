package com.example.sunpanels.controller

import com.example.sunpanels.entity.SunPanel
import com.example.sunpanels.service.SunPanelService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/sunpanel")
class SunPanelController(var sunPanelService: SunPanelService) {

    @PostMapping("/add")
    fun addSunPanel(@RequestBody sunPanel: SunPanel): ResponseEntity<SunPanel> {
        return ResponseEntity(sunPanelService.saveSunPanel(sunPanel), HttpStatus.CREATED)
    }

    @PatchMapping("/{sunpanelId}")
    fun updateASunPanel(@PathVariable sunpanelId: Long, @RequestBody sunPanel: SunPanel): Any {
        return ResponseEntity(sunPanelService.updateASunPanel(sunpanelId, sunPanel), HttpStatus.OK)
    }

    @GetMapping("/{sunpanelId}")
    fun getASunPanel(@PathVariable sunpanelId: Long): ResponseEntity<Optional<SunPanel>> {
        val sunPanel: Optional<SunPanel> = sunPanelService.getSunPanel(sunpanelId)
        return ResponseEntity(sunPanel, HttpStatus.OK)
    }

    @GetMapping("/all")
    fun getAllSunpanels(): ResponseEntity<MutableIterable<SunPanel>> {
        return ResponseEntity(sunPanelService.getAllSunPanels(), HttpStatus.OK)
    }

}
