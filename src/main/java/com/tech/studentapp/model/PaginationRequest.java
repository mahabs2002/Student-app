package com.tech.studentapp.model;

import lombok.Data;

@Data
public class PaginationRequest {
    int page;
    int size;
}
