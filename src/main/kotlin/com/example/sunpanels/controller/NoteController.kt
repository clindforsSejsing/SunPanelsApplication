package com.example.sunpanels.controller

import com.example.sunpanels.entity.Note
import com.example.sunpanels.service.NoteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/api/note")
class NoteController (var noteService: NoteService){

    @PostMapping("/person/{personId}/sunpanel/{sunpanelId}")
    fun addANote(@RequestBody note: Note, @PathVariable personId : Long, @PathVariable sunpanelId : Long ): Any {
        if(noteService.saveNote(note, personId, sunpanelId) != null){
            return ResponseEntity(noteService.saveNote(note, personId, sunpanelId), HttpStatus.CREATED)
        }else return ResponseEntity("Wrong format on date, needs to be in june or july, try again",HttpStatus.BAD_REQUEST)
    }

      @GetMapping("/{noteId}")
    fun getANote(@PathVariable noteId: Long): ResponseEntity<Optional<Note>> {
        val note: Optional<Note> = noteService.getANote(noteId)
        return ResponseEntity(note, HttpStatus.OK)
    }

    @GetMapping("/person/{personId}")
    fun getAllNotes(@PathVariable personId: Long): Any {
        val note: Any = noteService.getAllNotesForOnePerson(personId)
        return ResponseEntity(note, HttpStatus.OK)
    }

    @DeleteMapping("/{noteId}/remove")
    fun deleteOneNote(@PathVariable noteId: Long): ResponseEntity<Note> {
        val note : Note? = noteService.deleteANoteById(noteId)
        return ResponseEntity(note, HttpStatus.OK)
    }
}