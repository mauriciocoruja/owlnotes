package dev.mauriciocoruja.owlnotes.entities.dto;

import dev.mauriciocoruja.owlnotes.entities.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public static Page<NoteDTO> converterToPage(Page<Note> notes) {
        return notes.map(NoteDTO::new);
    }
}
