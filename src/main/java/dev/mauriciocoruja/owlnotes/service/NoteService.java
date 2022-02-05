package dev.mauriciocoruja.owlnotes.service;

import dev.mauriciocoruja.owlnotes.entities.Note;
import dev.mauriciocoruja.owlnotes.entities.dto.NoteDTO;

import java.util.List;

public interface NoteService {
    List<Note> findAllNotes();

    Note findNoteById(String id);

    Note createNote(NoteDTO note);

    Note updateNote(String id, NoteDTO note);

    void deleteNote(String id);

    List<Note> findTitleWith(String text);
}
