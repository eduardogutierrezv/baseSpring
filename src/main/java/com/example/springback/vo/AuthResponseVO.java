package com.example.springback.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseVO {
    private String accessToken;
    private String tokenType = "Bearer ";

}
