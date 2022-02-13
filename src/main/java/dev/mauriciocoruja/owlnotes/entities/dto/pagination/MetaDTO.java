package dev.mauriciocoruja.owlnotes.entities.dto.pagination;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MetaDTO {
    private int totalPages;
    private long totalRecords;
}
