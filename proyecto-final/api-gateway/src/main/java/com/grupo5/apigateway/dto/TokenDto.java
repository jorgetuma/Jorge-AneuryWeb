package com.grupo5.apigateway.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenDto {
    private String token;

    public boolean isValid() {
        return getToken() != null && !getToken().isEmpty();
    }
}
