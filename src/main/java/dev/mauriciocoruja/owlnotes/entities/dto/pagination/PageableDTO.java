package dev.mauriciocoruja.owlnotes.entities.dto.pagination;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageableDTO {
    private int pageNumber;
    private int pageSize;

    //TODO uncomment bellow to allow the URL info can be showed
    /*private String self;
    private String first;
    private String next;
    private String last;*/
}
