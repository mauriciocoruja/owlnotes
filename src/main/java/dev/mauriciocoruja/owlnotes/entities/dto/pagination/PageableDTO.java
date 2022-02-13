package dev.mauriciocoruja.owlnotes.entities.dto.pagination;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageableDTO {
    private int pageNumber;
    private int pageSize;
}
