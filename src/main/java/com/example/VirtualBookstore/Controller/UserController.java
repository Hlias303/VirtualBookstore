package com.example.VirtualBookstore.Controller;


import com.example.VirtualBookstore.DTO.LoginResponse;
import com.example.VirtualBookstore.Model.User;
import com.example.VirtualBookstore.Service.Interface.JwtService;
import com.example.VirtualBookstore.Service.Interface.RolesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final RolesService role_service;
    private final JwtService jwtService;

    public UserController(AuthenticationManager authenticationManager,
                          RolesService role_service,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.role_service = role_service;
        this.jwtService = jwtService;
    }

    @PostMapping("/Register")
    public void AddUser(@RequestBody User user){
        try {
            role_service.SaveUser(user);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostMapping("/Register/Admin")
    public void AddAdmin(@RequestBody User user){
        role_service.SaveAdmin(user);
    }

    @GetMapping("/Users")
    public List<User> ShowUsers(){
        return role_service.ShowUsers();
    }

    @DeleteMapping("/Users/{id}")
    public void DeleteUser(@PathVariable int id){
        role_service.DeleteUser(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){

        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if (auth.isAuthenticated()) {

                // Roles come from the authenticated principal (loaded from the DB),
                // e.g. ["USER"] or ["ADMIN"] — no hardcoded usernames.
                List<String> roles = auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList();

                LoginResponse loginResponse = new LoginResponse(
                        jwtService.generateToken(user.getUsername()),
                        user.getUsername(),
                        roles);

                return ResponseEntity.ok(loginResponse);
            }

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login-Failed");

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login-Failed");
        }
    }
}
