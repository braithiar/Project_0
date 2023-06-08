package com.revature.models;

public class ItemType {
  private int id;
  private String type;

  public ItemType() {
  }

  public ItemType(int id, String type) {
    this.id = id;
    this.type = type;
  }

  /**
   * Constructor intended to INSERT a new item type into the database.
   *
   * @param type the type that describes a category of item
   */
  public ItemType(String type) {
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  @Override
  public String toString() {
    return "ItemType{" +
           "id=" + id +
           ", type='" + type + '\'' +
           '}';
  }
}
