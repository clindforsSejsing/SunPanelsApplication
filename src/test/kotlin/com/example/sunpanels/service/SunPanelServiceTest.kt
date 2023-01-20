package com.example.sunpanels.service


import com.example.sunpanels.entity.SunPanel
import com.example.sunpanels.repository.SunPanelRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*


class SunPanelServiceTest {
    private val sunPanelRepository: SunPanelRepository = mockk()
    private val sunPanelService = SunPanelService(sunPanelRepository)
    private var sunPanel = SunPanel()
    private val list = ArrayList<SunPanel>()

    @Test
    fun saveSunPanel_andReturnSavedSunPanel() {
        //given
        every { sunPanelRepository.save(sunPanel) } returns sunPanel
        //when
        val result = sunPanelService.saveSunPanel(sunPanel)
        //then
        verify(exactly = 1) { sunPanelRepository.save(sunPanel) }
        Assertions.assertEquals(sunPanel, result)
    }

    @Test
    fun getSunPanel_thenReturnSunPanel() {
        //given
        every { sunPanelRepository.findById(1L) } returns Optional.of(sunPanel)
        //when
        val result = sunPanelService.getSunPanel(1L)
        //then
        verify(exactly = 1) { sunPanelRepository.findById(1L) }
        Assertions.assertEquals(Optional.of(sunPanel), result)
    }

    @Test
    fun getAllSunPanels_andReturnListOfSunPanels() {
        //given
        every { sunPanelRepository.findAll() } returns list
        //when
        val result = sunPanelService.getAllSunPanels()
        //then
        verify(exactly = 1) { sunPanelRepository.findAll() }
        Assertions.assertEquals(list, result)
    }


    @Test
    fun updateASunPanel_andReturnUpdatedSunPanel() {
        //given
        sunPanel.amountOfPanels = 1
        sunPanel.sekPerKwh = 1.32F
        sunPanel.id = 1L
        every { sunPanelRepository.findById(1L).get() } returns sunPanel
        every { sunPanelRepository.save(sunPanel) } returns sunPanel
        //when
        sunPanel.amountOfPanels = 10
        sunPanel.sekPerKwh = 2.15F
        val result = sunPanelService.updateASunPanel(1L, sunPanel)
        //then
        verify(exactly = 1) { sunPanelRepository.findById(1L) }
        Assertions.assertEquals(sunPanel, result)
    }

}


