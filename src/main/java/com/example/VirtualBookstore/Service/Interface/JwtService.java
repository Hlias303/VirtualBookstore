package com.example.VirtualBookstore.Service.Interface;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Service interface for handling JSON Web Token (JWT) operations.
 * Provides methods to generate tokens, extract user information,
 * and validate tokens in Spring Security applications.
 */
public interface JwtService {

    /**
     * Generates a JWT token for a given username using the application secret key.
     *
     * @param username the username to encode in the JWT token
     * @return a generated JWT token string
     */
    String generateToken(String username);

    /**
     * Extracts the username from a JWT token by decoding its payload claims.
     *
     * @param token the JWT token string to extract the username from
     * @return the username embedded in the token
     */
    String extractUserName(String token);

    /**
     * Validates a JWT token against its associated UserDetails credentials.
     * Ensures that the token is not expired and corresponds to a valid user.
     *
     * @param token the JWT token to validate
     * @param userDetails the user account details to compare against
     * @return true if the token is valid; false otherwise
     */
    boolean validateToken(String token, UserDetails userDetails);
}
