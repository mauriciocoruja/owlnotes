package dev.mauriciocoruja.owlnotes.impl;

import dev.mauriciocoruja.owlnotes.entities.Note;
import dev.mauriciocoruja.owlnotes.entities.dto.NoteDTO;
import dev.mauriciocoruja.owlnotes.service.exceptions.EntityNotFoundException;
import dev.mauriciocoruja.owlnotes.repository.NoteRepository;
import dev.mauriciocoruja.owlnotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Page<Note> findAllNotes(Pageable pageable) {
        return this.noteRepository.findAll(pageable);
    }

    @Override
    public Note findNoteById(String id) {
        return this.noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found"));
    }

    @Override
    public Note createNote(NoteDTO note) {
        return noteRepository.insert(Note.builder()
                .createDate(LocalDateTime.now())
                .title(note.getTitle())
                .content(note.getContent())
                .build());
    }

    @Override
    public Note updateNote(String id, NoteDTO note) {
        Note n = this.findNoteById(id);
        updateNoteData(note, n);
        return this.noteRepository.save(n);
    }

    private void updateNoteData(NoteDTO note, Note n) {
        n.setUpdateDate(LocalDateTime.now());
        n.setTitle(note.getTitle());
        n.setContent(note.getContent());
    }

    @Override
    public void deleteNote(String id) {
        this.noteRepository.deleteById(this.findNoteById(id).getId());
    }


    @Override
    public List<Note> findTitleWith(String text) {
        return this.noteRepository.findText(text);
    }


}
