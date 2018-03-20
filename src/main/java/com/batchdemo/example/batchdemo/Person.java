package com.batchdemo.example.batchdemo;

/**
 * Created by jcthalys on Mar, 2018
 */
public class Person {

  private String firstName;
  private Integer age;
  private String email;

  public Person() {
  }

  public Person(String firstName, Integer age, String email) {
    this.firstName = firstName;
    this.age = age;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
