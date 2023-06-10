package com.revature.models;

public class Item {
  private int id;
  private String name;
  private String desc;
  private double price;
  private int itemTypeId;
  private ItemType type;
  private int enchantId;
  private Enchantment enchant;

  public Item() {
  }

  public Item(int id, String name, String desc, double price, ItemType type,
              Enchantment enchant) {
    this.id = id;
    this.name = name;
    this.desc = desc;
    this.price = price;
    this.type = type;
    this.enchant = enchant;
    itemTypeId = -1;
    enchantId = -1;
  }

  /**
   * Constructor for INSERTing a new item to the database;
   *
   * @param name          the item's name
   * @param desc          a description of the item
   * @param price         the item's price
   * @param itemTypeId    the ID that indicates the ItemType
   * @param enchantId an OPTIONAL ID that indicates an enchantment
   */
  public Item(String name, String desc, double price, int itemTypeId,
              int enchantId) {
    this.name = name;
    this.desc = desc;
    this.price = price;
    this.itemTypeId = itemTypeId;
    this.enchantId = enchantId;
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

  public double getPrice() {
    return price;
  }

  public int getItemTypeId() {
    return itemTypeId;
  }

  public ItemType getType() {
    return type;
  }

  public int getEnchantId() {
    return enchantId;
  }

  public Enchantment getEnchant() {
    return enchant;
  }

  @Override
  public String toString() {
    return "Item{" +
           "id=" + id +
           ", name='" + name + '\'' +
           ", desc='" + desc + '\'' +
           ", price=" + price +
           ", itemTypeId=" + itemTypeId +
           ", type=" + type +
           ", enchantmentId=" + enchantId +
           ", enchant=" + enchant +
           '}';
  }
}
