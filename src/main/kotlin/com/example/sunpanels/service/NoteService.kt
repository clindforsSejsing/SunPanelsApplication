package com.example.sunpanels.service

import com.example.sunpanels.entity.Note
import com.example.sunpanels.entity.Person
import com.example.sunpanels.entity.SunPanel
import com.example.sunpanels.exceptions.NoteNotFoundException
import com.example.sunpanels.exceptions.PersonNotFoundException
import com.example.sunpanels.exceptions.SunPanelNotFoundException
import com.example.sunpanels.repository.NoteRepository
import com.example.sunpanels.repository.PersonRepository
import com.example.sunpanels.repository.SunPanelRepository
import org.springframework.stereotype.Service
import java.time.temporal.ChronoUnit
import java.util.*


@Service
class NoteService(
    var noteRepository: NoteRepository, var personRepository: PersonRepository,
    var sunPanelRepository: SunPanelRepository
) {

    /**
     * @param note gets input from user, localdatetime for sunrise, sunset.
     * @return feedback if month is june or july, then sets the number of sunhours and income in sek /h.
     * */

    fun saveNote(note: Note, personId: Long, sunpanelId: Long): Note? {
        val selectedPerson: Person = findingPerson(personRepository.findById(personId), personId)
        val selectedSunpanel: SunPanel = findingSunPanel(sunPanelRepository.findById(sunpanelId), sunpanelId)
        val sunriseNmbOfMonth = note.sunrise?.monthValue
        val sunsetNmbOfMonth = note.sunset?.monthValue

        if (sunriseNmbOfMonth == 6 || sunriseNmbOfMonth == 7) {
            if (sunsetNmbOfMonth == 6 || sunsetNmbOfMonth == 7) {
                val AmountOfSunHours = note.sunrise?.until(note.sunset, ChronoUnit.HOURS)
                note.person = selectedPerson
                note.sunpanel = selectedSunpanel
                note.nmbOfSunHours = AmountOfSunHours
                note.incomeSek =
                    calculateIncomeBySwedishCrowns(selectedSunpanel, AmountOfSunHours)
                return noteRepository.save(note)
            }
        } else println("wrong input in date, needs to be in june or july")
        return null
    }

    fun findingPerson(entity: Optional<Person?>, personId: Long): Person {
        return if (entity.isPresent()) entity.get() else throw PersonNotFoundException(personId)
    }

    fun findingSunPanel(entity: Optional<SunPanel?>, sunpanelId: Long): SunPanel {
        return if (entity.isPresent()) entity.get() else throw SunPanelNotFoundException(sunpanelId)
    }

    fun findingNote(entity: Optional<Note?>, noteId: Long): Note {
        return if (entity.isPresent()) entity.get() else throw NoteNotFoundException(noteId)
    }


    /**
     * @param sunPanel gets input from user
     * @param sunHours gets the amount of hours the sun shines from sunrise til sunset.
     * @return calculate and return income in sek, rounded with two decimals.
     * sunRadiation - per day, sunEfficiancy 20 % per day, production * price, rounded to two dec in SEK.
     * sunRadiation = 0.166 * sunPanelsArea (* sunHours)
     * sunEfficiancy = 0.20 (percent/day)
     * elProduction = sunRadiation * sunEfficiancy
     * incomeInSek = elproduction * electricPrice
     * */

    fun calculateIncomeBySwedishCrowns(sunPanel: SunPanel, sunHours: Long?): Float {
        val sunRadiation: Double = 0.166 * (sunPanel.areaOfPanels) * sunHours!!
        val sunEfficiancy = 0.20
        val elProduction: Double = sunRadiation * sunEfficiancy
        val electricPrice: Double = elProduction * sunPanel.sekPerKwh
        return Math.round(electricPrice * 100) / 100f
    }


    fun getANote(noteId: Long): Optional<Note> {
        return noteRepository.findById(noteId)
    }

    fun getAllNotesForOnePerson(personId: Long): Any {
        val findingNotesByPersonId: Person = personRepository.findById(personId).get()
        val findingNotes: MutableIterable<Note> = noteRepository.findAll()
        val foundNotesByPerson = ArrayList<Note>()

        for (note in findingNotes) {
            if (note.person?.id == personId) {
                foundNotesByPerson.add(note)
            }
        }

        if ((findingNotesByPersonId.id)?.equals(personId) == true) {
            return foundNotesByPerson
        } else {
            println("didnt find it ${findingNotes}")//make error-message
        }
        return foundNotesByPerson
    }

    fun deleteANoteById(noteId: Long): Boolean {
        val findRightNoteById: Note = findingNote(noteRepository.findById(noteId), noteId)
        noteRepository.delete(findRightNoteById)
        return (noteRepository.findById(noteId) != Optional.of(findRightNoteById))
    }

}






