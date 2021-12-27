package com.sviluppatoredisuccesso.dressing.security;



import com.sviluppatoredisuccesso.dressing.entity.Role;
import com.sviluppatoredisuccesso.dressing.entity.UserEntity;
import com.sviluppatoredisuccesso.dressing.repository.RoleRepository;
import com.sviluppatoredisuccesso.dressing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // Create user roles
        Role userRole = createRoleIfNotFound(Role.ROLE_USER);
        Role adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);

        // Create users
        createUserIfNotFound("user", userRole);
        createUserIfNotFound("admin", adminRole);

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    UserEntity createUserIfNotFound(final String name, final Role role) {
        UserEntity user = userRepository.getUserByUsername(name);
        if (user == null) {
            user = new UserEntity(name, "{bcrypt}"+new BCryptPasswordEncoder().encode("user"));

            user.setRole(role);
            user = userRepository.save(user);
        }
        return user;
    }
}