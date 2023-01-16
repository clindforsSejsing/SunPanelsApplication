package com.example.sunpanels.exceptions

class SunPanelNotFoundException(id: Long?) : RuntimeException("Person with id '$id' does not exist")