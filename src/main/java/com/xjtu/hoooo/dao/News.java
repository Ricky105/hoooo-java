package com.xjtu.hoooo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class News {
    private String title;
    private String type;
    private LocalDate date;
    private String url;
}
