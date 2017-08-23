package me.farhan.model.dto;

import me.farhan.model.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SecuredUserDeails extends org.springframework.security.core.userdetails.User {

  private User user;

  public SecuredUserDeails(User user, Collection<? extends GrantedAuthority> authorities) {
    super(user.getEmail(), user.getPassword(), authorities);
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public Long getId() {
    return user.getId();
  }

}
