package me.farhan.model.dto;

import me.farhan.model.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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

  public Date getLastPasswordResetDate(){
    Calendar instance = Calendar.getInstance();
    instance.set(1999,3,5);
    return instance.getTime();
  }

}
