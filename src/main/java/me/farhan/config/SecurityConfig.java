package me.farhan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    RequestMatcher publicRequestMatcher = new RegexRequestMatcher("^/(public).*", "GET");
    RequestMatcher apiRequestMatcher = new RegexRequestMatcher("^/(front|stocks|edition).*", "GET");
    RequestMatcher postRequestMatcher = new RegexRequestMatcher(".*", "POST");
    RequestMatcher adminRequestMatcher = new RegexRequestMatcher("^.*(/admin/).*", null);

    http.csrf().disable()
            .authorizeRequests()
            .requestMatchers(publicRequestMatcher).permitAll()
            .requestMatchers(apiRequestMatcher).permitAll();
  }

  }