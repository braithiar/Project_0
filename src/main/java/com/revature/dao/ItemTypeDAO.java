package com.revature.dao;

import com.revature.dao.interfaces.ItemTypeDAOInterface;
import com.revature.models.ItemType;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemTypeDAO implements ItemTypeDAOInterface {
  private static final Logger logger =
    LoggerFactory.getLogger(ItemTypeDAO.class);

  @Override
  public ItemType getItemType(int id) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      return getItemType(id, conn);
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

  @Override
  public List<ItemType> getAllItemTypes() {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "SELECT id, type FROM item_types";
      Statement query = conn.createStatement();

      ResultSet rs = query.executeQuery(sql);
      List<ItemType> types = new ArrayList<>();

      while (rs.next()) {
        types.add(new ItemType(
          rs.getInt("id"),
          rs.getString("type")
        ));
      }

      if (types != null && types.size() > 0){
        return types;
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      System.out.println(
        "***Could not connect to database to get ALL Item Types***");
    }
    return null;
  }

  @Override
  public ItemType createItemType(ItemType it) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "INSERT INTO item_types (type) VALUES (?) RETURNING *";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setString(1, it.getType());

      ResultSet rs = query.executeQuery();

      if (rs.next()) {
        return new ItemType(
          rs.getInt("id"),
          rs.getString("type")
        );
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      System.out.println(
        "***Could not connect to the database to create Item Type***");
    }

    return null;
  }

  @Override
  public ItemType updateItemType(ItemType it) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "UPDATE item_types SET type=? WHERE id=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setString(1, it.getType());
      query.setInt(2, it.getId());

      if (query.executeUpdate() > 0) {
        return it;
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      System.out.println(
        "***Could not connect to the database to update Item Type***");
    }

    return null;
  }
}
