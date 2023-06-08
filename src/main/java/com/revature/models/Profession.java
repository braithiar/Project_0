package com.revature.models;

public class Profession {
  private int id;
  private String prof;

  public Profession() {
  }

  public Profession(int id, String prof) {
    this.id = id;
    this.prof = prof;
  }

  /**
   * Constructor intended to be used to INSERT a new profession.
   *
   * @param prof
   */
  public Profession(String prof) {
    this.prof = prof;
  }

  public int getId() {
    return id;
  }

  public String getProf() {
    return prof;
  }

  @Override
  public String toString() {
    return "Profession{" +
           "id=" + id +
           ", prof='" + prof + '\'' +
           '}';
  }
}
