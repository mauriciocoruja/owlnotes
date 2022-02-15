package dev.mauriciocoruja.owlnotes.controller;

import dev.mauriciocoruja.owlnotes.entities.Note;
import dev.mauriciocoruja.owlnotes.entities.dto.NoteDTO;
import dev.mauriciocoruja.owlnotes.entities.dto.pagination.PageDTO;
import dev.mauriciocoruja.owlnotes.entities.util.URL;
import dev.mauriciocoruja.owlnotes.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping(produces = "application/json")
    @Cacheable(value = "allNotes")
    public ResponseEntity<PageDTO<NoteDTO>> getAllNotes(@PageableDefault(
            sort = "dataDeCriacao", direction = Sort.Direction.DESC) Pageable pagination) {

        //TODO implement a way to get URL to navigate through pages

        return ResponseEntity
                .ok()
                .body(new PageDTO<>(this.noteService.findAllNotes(pagination)));
    }

    @GetMapping(value = "/id/", produces = "application/json")
    @Cacheable(value = "noteById")
    public ResponseEntity<Note> getNoteById(@RequestParam String id) {
        return ResponseEntity.ok().body(this.noteService.findNoteById(id));
    }

    @PostMapping(value = "/new-note", produces = "application/json", consumes ="application/json")
    @CacheEvict(value = "allNotes")
    public ResponseEntity<NoteDTO> createNote(@RequestBody NoteDTO noteForm) {
        Note noteCreated = this.noteService.createNote(noteForm);
        NoteDTO note = new NoteDTO(noteCreated);
        return ResponseEntity.created(URI.create("/notes/" + noteCreated.getId())).body(note);
    }

    @PutMapping(value = "/update-note", produces = "application/json", consumes ="application/json")
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
