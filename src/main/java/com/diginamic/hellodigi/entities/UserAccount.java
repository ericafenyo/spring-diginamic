package com.diginamic.hellodigi.entities;

import com.diginamic.hellodigi.businessmodel.UserRole;
import jakarta.persistence.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


@Entity(name = "user_accounts")
public class UserAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username", unique = true)
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "role", length = 20, unique = true)
  @Enumerated(EnumType.STRING)
  private UserRole role;

//  List<SimpleGrantedAuthority> authorities = Arrays.asList(
//      new SimpleGrantedAuthority("ROLE_USER"),
//      new SimpleGrantedAuthority("ROLE_ADMIN")
//  );

  public UserDetails asUser() {
    return  User.withUsername(getUsername())
        .password(getPassword())
        .authorities("ROLE_" + getRole().name())
        .build();
  }

  public Long getId() {
    return id;
  }

  public UserAccount setId(Long id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }
}
