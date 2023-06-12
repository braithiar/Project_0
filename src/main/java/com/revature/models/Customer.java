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
    professionId = -1; // Only used to create new customers
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
    profession = null;
    purchases = null;
  }

  public Customer(int id, String firstName, String lastName, int professionId) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    if (professionId > 0) {
      this.professionId = professionId;
    } else {
      this.professionId = -1;
    }
    profession = null;
    purchases = null;
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
    return "\nCustomer{\n" +
           "id=" + id +
           ", firstName='" + firstName + "',\n" +
           " lastName='" + lastName + "',\n" +
           " professionId=" + professionId + ",\n" +
           " profession=" + profession + ",\n" +
           " purchases=" + purchases +
           "\n}\n";
  }
}
