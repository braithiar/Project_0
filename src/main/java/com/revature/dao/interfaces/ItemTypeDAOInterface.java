package com.revature.dao.interfaces;

import com.revature.models.ItemType;

import java.sql.Connection;
import java.sql.SQLException;

public interface ItemTypeDAOInterface {
  ItemType getItemType(int id);
  ItemType getItemType(int id, Connection conn) throws SQLException;
}
