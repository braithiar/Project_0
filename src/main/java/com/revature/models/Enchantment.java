package com.revature.models;

public class Enchantment {
  private int id;
  private String name;
  private String desc;

  public Enchantment() {
  }

  public Enchantment(int id, String name, String desc) {
    this.id = id;
    this.name = name;
    this.desc = desc;
  }

  /**
   * Constructor intended to be used to INSERT a new enchantment into the database.
   *
   * @param name the name of the enchantment
   * @param desc a description of the enchantment
   */
  public Enchantment(String name, String desc) {
    this.name = name;
    this.desc = desc;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDesc() {
    return desc;
  }

  @Override
  public String toString() {
    return "Enchantment{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", desc='" + desc + '\'' +
           '}';
  }
}
