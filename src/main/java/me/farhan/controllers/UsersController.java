package me.farhan.controllers;

import me.farhan.exceptions.UserExistsException;
import me.farhan.model.dto.UserDto;
import me.farhan.model.entity.User;
import me.farhan.responses.UserRegisterResponse;
import me.farhan.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "users")
public class UsersController {

  @Autowired
  private UserService userService;

  @GetMapping("/ok")
  public ResponseEntity<?> get(){
    return ResponseEntity.ok("OK");
  }

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody @Valid final UserDto userDto) {
    try {
      User user = userService.registerUser(userDto);
    }catch (UserExistsException e){
      return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body(new UserRegisterResponse<UserDto>(userDto,"User Already Exists"));
    }

    return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .body(new UserRegisterResponse<UserDto>(userDto,"User registered successfully"));
  }
}
