package com.example.sunpanels

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SunpanelsApplication

fun main(args: Array<String>) {
	runApplication<SunpanelsApplication>(*args)
}
