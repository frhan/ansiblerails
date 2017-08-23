package me.farhan.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Value
public class UserDto {

  @NotNull
  @NotEmpty
  private final String email;

  @NotNull
  @NotEmpty
  @JsonIgnore
  private final String password;
}
