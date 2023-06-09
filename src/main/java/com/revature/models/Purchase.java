package com.revature.models;

public class Purchase {
  private int id;
  private int custId;
  private int itemId;
  private Item item;

  public Purchase() {
  }

  public Purchase(int id, int custId, Item item) {
    this.id = id;
    this.custId = custId;
    this.item = item;
    itemId = -1;
  }

  /**
   * Constructor to INSERT a new purchase into the database.
   *
   * @param custId the customer ID
   * @param itemId the ID of the purchased item
   */
  public Purchase(int custId, int itemId) {
    this.custId = custId;
    this.itemId = itemId;
  }

  public int getId() {
    return id;
  }

  public int getCustId() {
    return custId;
  }

  public int getItemId() {
    return itemId;
  }

  public Item getItem() {
    return item;
  }

  @Override
  public String toString() {
    return "Purchase{" +
           "id=" + id +
           ", custId=" + custId +
           ", itemId=" + itemId +
           ", item=" + item +
           "}\n";
  }
}
