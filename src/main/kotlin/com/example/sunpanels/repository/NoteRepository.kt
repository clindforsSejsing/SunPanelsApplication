package com.example.sunpanels.repository

import com.example.sunpanels.entity.Note
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository : CrudRepository<Note, Long> {

}