package com.dstech.powercomerce.services;

import com.dstech.powercomerce.entities.User;
import com.dstech.powercomerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(Long userId){
        User me = userService.authenticated();
        if(!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)){
            throw new ForbiddenException("O Clienet só pode visualizar os pedidos dele mesmo");
        }
    }


}
