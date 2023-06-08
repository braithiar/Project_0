package com.revature.models;

import java.util.List;

public class Customer {
  private int id;
  private String firstName;
  private String lastName;
  private int professionId;
  private Profession profession;
  private List<Purchase> purchases;

  public Customer() {
  }

  public Customer(int id, String firstName, String lastName,
                  Profession profession, List<Purchase> purchases) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.profession = profession;
    this.purchases = purchases;
  }

  /**
   * Constructor intended for INSERTing a new customer into the database.
   *
   * @param firstName
   * @param lastName
   * @param professionId
   */
  public Customer(String firstName, String lastName, int professionId) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.professionId = professionId;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getProfessionId() {
    return professionId;
  }

  public Profession getProfession() {
    return profession;
  }

  public List<Purchase> getPurchases() {
    return purchases;
  }

  @Override
  public String toString() {
    return "Customer{" +
           "id=" + id +
           ", firstName='" + firstName + '\'' +
           ", lastName='" + lastName + '\'' +
           ", professionId=" + professionId +
           ", profession=" + profession +
           ", purchases=" + purchases +
           "}\n";
  }
}
