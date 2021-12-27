package com.sviluppatoredisuccesso.dressing.service;

import com.sviluppatoredisuccesso.dressing.dto.GeneralDto;
import com.sviluppatoredisuccesso.dressing.entity.Role;
import com.sviluppatoredisuccesso.dressing.entity.UserEntity;
import com.sviluppatoredisuccesso.dressing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class UserDetailsServiceImpl implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserEntity user = userRepository.getUserByUsername(email);
        String test = "{bcrypt}"+new BCryptPasswordEncoder().encode(password);
        if(!new BCryptPasswordEncoder().matches(password, user.getPassword().substring(8,user.getPassword().length()))) {


            throw new AuthenticationException("password non trovata") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName())); // description is a string

        return new UsernamePasswordAuthenticationToken(email, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}