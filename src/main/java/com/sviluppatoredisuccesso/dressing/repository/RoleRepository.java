package com.sviluppatoredisuccesso.dressing.repository;

import com.sviluppatoredisuccesso.dressing.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}