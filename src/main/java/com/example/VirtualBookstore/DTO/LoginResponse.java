package com.example.VirtualBookstore.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response returned by POST /login.
 * Carries the JWT plus the user's real roles loaded from the database,
 * so the frontend never has to guess/hardcode who is an admin.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private String username;
    private List<String> roles;
}
