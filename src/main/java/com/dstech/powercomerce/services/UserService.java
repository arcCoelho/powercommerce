package com.dstech.powercomerce.services;

import com.dstech.powercomerce.entities.Role;
import com.dstech.powercomerce.entities.User;
import com.dstech.powercomerce.projection.UserDetailsProjection;
import com.dstech.powercomerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("caiu aqui!!!!");
        List<UserDetailsProjection> authorizations = repository.searchUserAndRolesByEmail(username);

        if(authorizations.size() == 0){
            throw new UsernameNotFoundException("usuário não encontrado!");
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(authorizations.get(0).getPassword());

        for (UserDetailsProjection projection : authorizations){
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }
}
