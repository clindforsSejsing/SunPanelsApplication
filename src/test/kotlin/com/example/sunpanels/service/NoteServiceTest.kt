package com.example.sunpanels.service


import com.example.sunpanels.entity.Note
import com.example.sunpanels.entity.Person
import com.example.sunpanels.entity.SunPanel
import com.example.sunpanels.repository.NoteRepository
import com.example.sunpanels.repository.PersonRepository
import com.example.sunpanels.repository.SunPanelRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList


class NoteServiceTest {
    private val noteRepository: NoteRepository = mockk()
    private val personRepository: PersonRepository = mockk()
    private val sunPanelRepository: SunPanelRepository = mockk()
    private val noteService = NoteService(noteRepository, personRepository, sunPanelRepository)
    private val note = Note()
    private val person = Person()
    private val sunPanel = SunPanel()
    private val list = ArrayList<Note>()


    @Test
    fun saveNote_andReturnSavedNote() {
        //given
        val localDateTime : LocalDateTime = LocalDateTime.of(2018, 6, 30,1,1)

        note.sunrise= localDateTime
        note.sunset = localDateTime
        note.person = person
        note.sunpanel = sunPanel
        note.nmbOfSunHours = 4
        note.incomeSek = 40F

        every { personRepository.findById(1L) } returns Optional.of(person)
        every { sunPanelRepository.findById(1L) } returns Optional.of(sunPanel)
        every { noteRepository.save(note) } returns note
        //when
        val result = noteService.saveNote(note, 1L,  1L)
        //then
        verify(exactly = 1) { noteRepository.save(note)  }
        Assertions.assertEquals(note, result)

    }

    @Test
    fun getANote() {

        //given
        every { noteRepository.findById(1L) } returns Optional.of(note)
        //when
        val result = noteService.getANote(1L)
        //then
        verify(exactly = 1) { noteRepository.findById(1L) }
        Assertions.assertEquals(Optional.of(note), result)
    }

        @Test
        fun getAllNotesForOnePerson() {
            //given
            every { personRepository.findById(1L) } returns Optional.of(person)
            every { noteRepository.findAll() } returns list
            person.id = 1L
            //when
            val result = noteService.getAllNotesForOnePerson(1L)
            //then
            verify(exactly = 1) { noteRepository.findAll() }
            Assertions.assertEquals(list, result)
        }

        @Test
        fun deleteANoteById_failed() {
            //given
            every { noteRepository.findById(1L) } returns Optional.of(note)
            every { noteRepository.delete(any())  } returns Unit
           // doNothing().`when`(noteRepository.delete(note))
            //when
            val result = noteService.deleteANoteById(1L)
            //then
            verify(exactly = 1) { noteRepository.delete(note) }
            Assertions.assertEquals(false, result)
        }

    @Test
    fun deleteANoteById_success() {
        //given
        every { noteRepository.findById(1L) } returns Optional.of(note) andThen Optional.empty()
        every { noteRepository.delete(any())  } returns Unit
        // doNothing().`when`(noteRepository.delete(note))
        //when
        val result = noteService.deleteANoteById(1L)
        //then
        verify(exactly = 1) { noteRepository.delete(note) }
        Assertions.assertEquals(true, result)
    }

}