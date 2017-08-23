package me.farhan.services;

import me.farhan.exceptions.UserExistsException;
import me.farhan.model.dto.UserDto;
import me.farhan.model.entity.Role;
import me.farhan.model.entity.User;
import me.farhan.repositories.RoleRepository;
import me.farhan.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public User registerUser(final UserDto userDto) throws UserExistsException {
    if (userDto == null)
      return null;

    if (isUserExists(userDto.getEmail()))
      throw new UserExistsException("User Already exists with this email");

    User user = new User();
    user.setEmail(userDto.getEmail());
    user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    Role role = roleRepository.findByName("user");
    if (role != null)
      user.setRole(role);
    return userRepository.save(user);
  }

  private boolean isUserExists(final String email) {
    return userRepository.findByEmail(email) != null ? true : false;
  }

}
