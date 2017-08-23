package me.farhan.repositories;

import me.farhan.model.entity.Role;
import me.farhan.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}
