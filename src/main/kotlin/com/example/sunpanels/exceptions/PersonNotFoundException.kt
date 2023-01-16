package com.example.sunpanels.exceptions

class PersonNotFoundException(id: Long?) : RuntimeException("Person with id '$id' does not exist")