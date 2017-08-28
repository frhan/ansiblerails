package me.farhan.controllers;

import me.farhan.model.dto.SecuredUserDeails;
import me.farhan.model.dto.UserDto;
import me.farhan.responses.JwtAuthenticationResponse;
import me.farhan.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.ws.Response;

@RestController
@RequestMapping("auth")
public class UserAuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;


  @PostMapping("/token")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid UserDto userDto, Device device) {

    final Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());
    final String token = jwtTokenUtil.generateToken(userDetails, device);

    return ResponseEntity.ok(new JwtAuthenticationResponse(token));
  }

  @GetMapping(value = "/refresh")
  public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
    String token = request.getHeader("Authorization");
    String username = jwtTokenUtil.getUsernameFromToken(token);
    SecuredUserDeails user = (SecuredUserDeails) userDetailsService.loadUserByUsername(username);

    if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
      String refreshedToken = jwtTokenUtil.refreshToken(token);
      return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
    } else {
      return ResponseEntity.badRequest().body(null);
    }
  }
}
