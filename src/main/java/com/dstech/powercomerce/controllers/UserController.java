package com.dstech.powercomerce.controllers;


import com.dstech.powercomerce.dto.UserDTO;
import com.dstech.powercomerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getLogged(){
        UserDTO dto = service.getMe();
        return ResponseEntity.ok(dto);
    }

}
