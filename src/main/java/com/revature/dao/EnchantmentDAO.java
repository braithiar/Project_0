package com.revature.dao;

import com.revature.dao.interfaces.EnchantmentDAOInterface;
import com.revature.models.Enchantment;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnchantmentDAO implements EnchantmentDAOInterface {
  private static final Logger logger =
    LoggerFactory.getLogger(EnchantmentDAO.class);

  @Override
  public Enchantment getEnchantment(int id) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      return getEnchantment(id, conn);
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get enchantment by ID***");
    }
    return null;
  }

  @Override
  public Enchantment getEnchantment(int id, Connection conn) throws SQLException {
    String sql = "SELECT name, description FROM enchantments WHERE id=?";
    PreparedStatement query = conn.prepareStatement(sql);

    query.setInt(1, id);

    ResultSet rs = query.executeQuery();

    if (rs.next()) {
      return new Enchantment(
        id,
        rs.getString("name"),
        rs.getString("description")
      );
    }

    return null;
  }
}
