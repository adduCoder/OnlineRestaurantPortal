package com.user.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Address {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "address_id")
  private Integer id;
  @Column(name = "street", nullable = false)
  private String street;
  @Column(name = "state", nullable = false)
  private String state;
  @Column(name = "city", nullable = false)
  private String city;
  @Column(name = "pincode", nullable = false)
  private Integer pinCode;

  private Integer userId;

}
