package dev.mauriciocoruja.owlnotes.entities.dto.pagination;

import dev.mauriciocoruja.owlnotes.entities.Note;
import dev.mauriciocoruja.owlnotes.entities.dto.NoteDTO;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PageDTO<T> {
    private List<NoteDTO> content;
    private PageableDTO pageInfo;
    private MetaDTO meta;

    public PageDTO(Page<Note> page) {
        this.content = page.getContent().stream().map(NoteDTO::new).collect(Collectors.toList());

        this.pageInfo = PageableDTO.builder()
                .pageNumber(page.getPageable().getPageNumber())
                .pageSize(page.getPageable().getPageSize())
                .build();
        this.meta = MetaDTO.builder()
                .totalRecords(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }
}

