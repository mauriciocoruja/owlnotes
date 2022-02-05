package dev.mauriciocoruja.owlnotes.entities.dto;

import dev.mauriciocoruja.owlnotes.entities.Note;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class NoteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String content;

    public NoteDTO(Note note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
    }
}
