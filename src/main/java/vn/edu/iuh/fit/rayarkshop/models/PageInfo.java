package vn.edu.iuh.fit.rayarkshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageInfo {

    private boolean firstPage;
    private boolean lastPage;
    private int currentPage;
    private int totalPages;

}
