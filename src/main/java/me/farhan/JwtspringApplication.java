package me.farhan;

import me.farhan.model.entity.Role;
import me.farhan.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JwtspringApplication {

  @Autowired
  private RoleRepository roleRepository;

  public static void main(String[] args) {
    SpringApplication.run(JwtspringApplication.class, args);
  }


  @Bean
  public CommandLineRunner demo() {
    return (args) -> {

      try {

        if (roleRepository.findByName("user") == null) {
          Role role = new Role();
          role.setName("user");
          roleRepository.save(role);
        }

        if (roleRepository.findByName("admin") == null) {
          Role role = new Role();
          role.setName("admin");
          roleRepository.save(role);
        }

      } catch (Exception e) {

      }

    };
  }
}
