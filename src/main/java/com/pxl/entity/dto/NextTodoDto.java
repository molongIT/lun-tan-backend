package com.pxl.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NextTodoDto {

    private String userId;

    private String content;
}
