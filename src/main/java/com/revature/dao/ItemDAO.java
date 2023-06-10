package com.revature.dao;

import com.revature.dao.interfaces.ItemDAOInterface;
import com.revature.models.Item;
import com.revature.models.Purchase;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
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
  public List<Item> getAllItems() {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "SELECT id, name, description, price, item_type_fk, enchantment_fk FROM items";
      Statement query = conn.createStatement();

      ResultSet rs = query.executeQuery(sql);
      List<Item> items = new ArrayList<>();
      ItemTypeDAO itDAO = new ItemTypeDAO();
      EnchantmentDAO eDAO = new EnchantmentDAO();

      while (rs.next()) {
        items.add(
          new Item(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("price"),
            itDAO.getItemType(rs.getInt("item_type_fk"), conn),
            eDAO.getEnchantment(rs.getInt("enchantment_fk"), conn)
          )
        );
      }

      logger.info("Retrieved all (" + items.size() + ") Items from database");
      return items;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn("***Could not connect to database to retrieve ALL items***");
    }

    return null;
  }

  @Override
  public Item addItem(Item i) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "INSERT INTO items (name, description, price, item_type_fk, enchantment_fk) VALUES (?,?,?,?,?) RETURNING *";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setString(1, i.getName());
      query.setString(2, i.getDesc());
      query.setDouble(3, i.getPrice());
      query.setInt(4, i.getItemTypeId());
      query.setInt(5, i.getEnchantId());

      ResultSet rs = query.executeQuery();
      ItemTypeDAO itDAO = new ItemTypeDAO();
      EnchantmentDAO eDAO = new EnchantmentDAO();

      if (rs.next()) {
        logger.info("Added " + i.getName() + " to database");
        return new Item(
          rs.getInt("id"),
          i.getName(),
          i.getDesc(),
          i.getPrice(),
          itDAO.getItemType(i.getItemTypeId(), conn),
          eDAO.getEnchantment(i.getEnchantId(), conn)
        );
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn("***Could not connect to database to create item***");
    }

    return null;
  }

  @Override
  public Item updateItem(Item i) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String select =
        "SELECT id, name, description, price, item_type_fk, enchantment_fk FROM items WHERE id=?";
      PreparedStatement query = conn.prepareStatement(select);

      query.setInt(1, i.getId());

      ResultSet rs = query.executeQuery();
      Item dbRecord = null;
      ItemTypeDAO itDAO = new ItemTypeDAO();
      EnchantmentDAO eDAO = new EnchantmentDAO();

      if (rs.next()) {
        dbRecord = new Item(
          rs.getInt("id"),
          rs.getString("name"),
          rs.getString("description"),
          rs.getDouble("price"),
          itDAO.getItemType(rs.getInt("item_type_fk"), conn),
          eDAO.getEnchantment(rs.getInt("enchantment_fk"), conn)
        );
      }

      if (i.getId() != dbRecord.getId()) {
        return null;
      }

      if (i.getName() != null && i.getName() != dbRecord.getName()) {
        String sql =
          "UPDATE items SET name=? WHERE id=?";

        query = conn.prepareStatement(sql);

        query.setString(1, i.getName());
        query.setInt(2, i.getId());

        query.executeUpdate();

        logger.info("Updated item (" + i.getId() + ") name to " + i.getName());
      }

      if (i.getDesc() != null && i.getDesc() != dbRecord.getDesc()) {
        String sql =
          "UPDATE items SET description=? WHERE id=?";

        query = conn.prepareStatement(sql);

        query.setString(1, i.getDesc());
        query.setInt(2, i.getId());

        query.executeUpdate();

        logger.info(
          "Updated item (" + i.getId() + ") description to " + i.getDesc());
      }

      if (i.getPrice() > 0 && i.getPrice() != dbRecord.getPrice()) {
        String sql =
          "UPDATE items SET price=? WHERE id=?";

        query = conn.prepareStatement(sql);

        query.setDouble(1, i.getPrice());
        query.setInt(2, i.getId());

        query.executeUpdate();

        logger.info(
          "Updated item (" + i.getId() + ") price to " + i.getPrice());
      }

      if (i.getItemTypeId() > 0 &&
          i.getItemTypeId() != dbRecord.getType().getId()) {
        String sql =
          "UPDATE items SET item_type_fk=? WHERE id=?";

        query = conn.prepareStatement(sql);

        query.setInt(1, i.getItemTypeId());
        query.setInt(2, i.getId());

        query.executeUpdate();

        logger.info(
          "Updated item (" + i.getId() + ")type id to " + i.getItemTypeId());
      }

      if (i.getEnchantId() > 0 &&
          i.getEnchantId() != dbRecord.getEnchant().getId()) {
        String sql =
          "UPDATE items SET enchantment_fk=? WHERE id=?";

        query = conn.prepareStatement(sql);

        query.setInt(1, i.getEnchantId());
        query.setInt(2, i.getId());

        query.executeUpdate();

        logger.info("Updated item (" + i.getId() + ") enchantment id to " +
                    i.getItemTypeId());
      }

      query = conn.prepareStatement(select);
      query.setInt(1, i.getId());

      rs = query.executeQuery();

      if (rs.next()) {
        return new Item(
          i.getId(),
          rs.getString("name"),
          rs.getString("description"),
          rs.getDouble("price"),
          itDAO.getItemType(rs.getInt("item_type_fk"), conn),
          eDAO.getEnchantment(rs.getInt("enchantment_fk"), conn)
        );
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to update item***");
    }

    return null;
  }
}
