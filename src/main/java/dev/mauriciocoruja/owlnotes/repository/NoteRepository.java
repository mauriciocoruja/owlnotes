package dev.mauriciocoruja.owlnotes.repository;

import dev.mauriciocoruja.owlnotes.entities.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {

    @Query("{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'content': { $regex: ?0, $options: 'i' } }] }")
    List<Note> findText(String text);

}
