package me.farhan.services;


import me.farhan.model.dto.SecuredUserDeails;
import me.farhan.model.entity.User;
import me.farhan.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(email);
    if (user == null) {
      throw new UsernameNotFoundException("No user found with user : " + email);
    }
    return new SecuredUserDeails(user, getAuthorities(Arrays.asList("ROLE_USER")));
  }

  private static List<GrantedAuthority> getAuthorities(List<String> roles) {
    if (CollectionUtils.isEmpty(roles))
      return null;

    List<GrantedAuthority> authorities = new ArrayList<>();
    roles.stream().map(s -> authorities.add(new SimpleGrantedAuthority(s)));
//    for (String role : roles) {
//      authorities.add(new SimpleGrantedAuthority(role));
//    }
    return authorities;
  }
}