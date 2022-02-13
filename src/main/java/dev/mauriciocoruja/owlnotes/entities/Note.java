package dev.mauriciocoruja.owlnotes.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@Document
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String title;
    private String content;
}
