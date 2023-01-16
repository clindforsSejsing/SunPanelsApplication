package com.example.sunpanels.exceptions

class NoteNotFoundException (noteId : Long?) : RuntimeException(("note with id '$noteId' does not exist"))