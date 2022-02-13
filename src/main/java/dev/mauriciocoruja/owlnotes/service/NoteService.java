package dev.mauriciocoruja.owlnotes.service;

import dev.mauriciocoruja.owlnotes.entities.Note;
import dev.mauriciocoruja.owlnotes.entities.dto.NoteDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoteService {
    Page<Note> findAllNotes(Pageable pageable);

    Note findNoteById(String id);

    Note createNote(NoteDTO note);

    Note updateNote(String id, NoteDTO note);

    void deleteNote(String id);

    List<Note> findTitleWith(String text);
}
