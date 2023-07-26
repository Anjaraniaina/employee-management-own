package com.learn.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchForm {
    private String keyword;
    private String filterBy;
    private String sex;
    private String order;
    private LocalDate hiringDate;
    private LocalDate departureDate;
}
