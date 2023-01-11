package com.example.sunpanels.repository

import com.example.sunpanels.entity.SunPanel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface SunPanelRepository : CrudRepository<SunPanel, Long> {

}