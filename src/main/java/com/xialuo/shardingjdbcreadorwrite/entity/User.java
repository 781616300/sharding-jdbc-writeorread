package com.xialuo.shardingjdbcreadorwrite.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {

  private long userId;
  private String name;
  private int age;

  public User() {
  }
}
