package com.example.springback.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRestController<T> {

    private String message;
    private Integer code;
    private T body;

}
