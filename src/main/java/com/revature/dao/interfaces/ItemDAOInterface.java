package com.revature.dao.interfaces;

import com.revature.models.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAOInterface {
  Item getItem(int id);
  Item getItem(int id, Connection conn) throws SQLException;
  List<Item> getAllItemd();
  Item addItem(Item i);
  Item updateItem(Item i);
  boolean deleteItem(int id);
}
