package dev.mauriciocoruja.owlnotes.controller;

import dev.mauriciocoruja.owlnotes.entities.Note;
import dev.mauriciocoruja.owlnotes.entities.dto.NoteDTO;
import dev.mauriciocoruja.owlnotes.entities.dto.pagination.PageDTO;
import dev.mauriciocoruja.owlnotes.entities.util.URL;
import dev.mauriciocoruja.owlnotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notes")
public class NoteController {


    private final int notePerPage = 5;

    @Autowired
    private NoteService noteService;

    @GetMapping
    @Cacheable(value = "allNotes")
    public ResponseEntity<PageDTO<NoteDTO>> getAllNotes(@RequestParam(name = "page") int page) {

        Pageable pageable = PageRequest.of(page, this.notePerPage);
        Page<Note> allNotes = this.noteService.findAllNotes(pageable);

        return ResponseEntity
                .ok()
                .body(new PageDTO<>(allNotes));
    }

    @GetMapping("/id/")
    @Cacheable(value = "noteById")
    public ResponseEntity<Note> getNoteById(@RequestParam String id) {
        return ResponseEntity.ok().body(this.noteService.findNoteById(id));
    }

    @PostMapping("/new-note")
    @CacheEvict(value = "allNotes")
    public ResponseEntity<NoteDTO> createNote(@RequestBody NoteDTO noteForm) {
        Note noteCreated = this.noteService.createNote(noteForm);
        NoteDTO note = new NoteDTO(noteCreated);
        return ResponseEntity.created(URI.create("/notes/" + noteCreated.getId())).body(note);
    }

    @PutMapping("/update-note")
    @CacheEvict(cacheNames = {"noteById", "allNotes"})
    public ResponseEntity<NoteDTO> updateNote(@RequestParam String id, @RequestBody NoteDTO noteForm) {
        Note updatedNote = this.noteService.updateNote(id, noteForm);
        NoteDTO note = new NoteDTO(updatedNote);
        return ResponseEntity.ok().body(note);
    }

    @DeleteMapping("/delete-note")
    @CacheEvict(cacheNames = {"noteById", "allNotes"})
    public ResponseEntity<Void> deleteNote(@RequestParam String id) {
        this.noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/title-search")
    public ResponseEntity<List<NoteDTO>> searchTitleWith(@RequestParam(value = "text", defaultValue = "") String text) {
        return ResponseEntity
                .ok().body(this.noteService.findTitleWith(URL.decodeParam(text))
                        .stream()
                        .map(NoteDTO::new)
                        .collect(Collectors.toList()));
    }
}
