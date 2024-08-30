package com.example.kanban.dto.web;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Long expireMillsIn;

    public JwtResponse(String accessToken, Long expireMillsIn) {
        this.accessToken = accessToken;
        this.expireMillsIn = expireMillsIn;
    }
}
