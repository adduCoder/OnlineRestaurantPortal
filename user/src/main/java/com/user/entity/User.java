package com.user.entity;

import com.user.util.Role;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "walletBalance")
  private Double walletBalance = 1000.0;

  @Column(name = "phoneNo", nullable = false)
  private String phoneNo;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private Role role;

}


