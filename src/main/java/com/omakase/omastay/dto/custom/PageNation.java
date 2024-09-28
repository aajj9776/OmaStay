package com.omakase.omastay.dto.custom;

import lombok.Data;

@Data
public class PageNation {
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
