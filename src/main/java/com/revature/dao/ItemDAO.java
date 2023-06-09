package com.revature.dao;

import com.revature.dao.interfaces.ItemDAOInterface;
import com.revature.models.Item;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ItemDAO implements ItemDAOInterface {
  private static final Logger logger = LoggerFactory.getLogger(ItemDAO.class);

  @Override
  public Item getItem(int id) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      return getItem(id, conn);
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get item by ID***");
    }
    return null;
  }

  @Override
  public Item getItem(int id, Connection conn) throws SQLException {
    String sql =
      "SELECT name, description, price, item_type_fk, enchantment_fk FROM items WHERE id=?";
    PreparedStatement query = conn.prepareStatement(sql);

    query.setInt(1, id);

    ResultSet rs = query.executeQuery();

    if (rs.next()) {
      ItemTypeDAO itDAO = new ItemTypeDAO();
      EnchantmentDAO eDAO = new EnchantmentDAO();

      return new Item(
        id,
        rs.getString("name"),
        rs.getString("description"),
        rs.getDouble("price"),
        itDAO.getItemType(rs.getInt("item_type_fk"), conn),
        eDAO.getEnchantment(rs.getInt("enchantment_fk"), conn)
      );
    }

    return null;
  }

  @Override
  public List<Item> getAllItemd() {
    return null;
  }

  @Override
  public Item addItem(Item i) {
    return null;
  }

  @Override
  public Item updateItem(Item i) {
    return null;
  }

  @Override
  public boolean deleteItem(int id) {
    return false;
  }
}
