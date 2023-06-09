package com.revature.dao;

import com.revature.dao.interfaces.ItemTypeDAOInterface;
import com.revature.models.ItemType;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemTypeDAO implements ItemTypeDAOInterface {
  private static final Logger logger =
    LoggerFactory.getLogger(ItemTypeDAO.class);

  @Override
  public ItemType getItemType(int id) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      getItemType(id, conn);
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get item type by ID***");
    }
    return null;
  }

  @Override
  public ItemType getItemType(int id, Connection conn) throws SQLException {
    String sql = "SELECT type FROM item_types WHERE id=?";
    PreparedStatement query = conn.prepareStatement(sql);

    query.setInt(1, id);

    ResultSet rs = query.executeQuery();

    if (rs.next()) {
      return new ItemType(
        id,
        rs.getString("type")
      );
    }

    return null;
  }
}
