package com.revature.dao.interfaces;

import com.revature.models.ItemType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItemTypeDAOInterface {
  ItemType getItemType(int id);
  ItemType getItemType(int id, Connection conn) throws SQLException;

  List<ItemType> getAllItemTypes();

  ItemType createItemType(ItemType it);

  ItemType updateItemType(ItemType it);
}
