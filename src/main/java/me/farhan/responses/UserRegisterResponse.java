package me.farhan.responses;

import lombok.Value;
import me.farhan.model.dto.UserDto;

@Value
public class UserRegisterResponse<T> {
  public final T user;
  public final String message;
}
