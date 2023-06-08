package com.revature.dao;

import com.revature.dao.interfaces.PurchaseDAOInterface;
import com.revature.models.Purchase;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO implements PurchaseDAOInterface {
  private static final Logger logger =
    LoggerFactory.getLogger(PurchaseDAO.class);

  @Override
  public List<Purchase> getCustomerPurchases(int customerId) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "SELECT id, customer_fk, item_fk FROM purchases WHERE customer_fk=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, customerId);

      ResultSet rs = query.executeQuery();
      List<Purchase> purchases = new ArrayList<>();

      while (rs.next()) {
        ItemDAO iDAO = new ItemDAO();

        purchases.add(
          new Purchase(
            rs.getInt("id"),
            rs.getInt("customer_fk"),
            iDAO.getItem(rs.getInt("item_fk"))
          )
        );
      }

      return purchases;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get customer purchases***");
    }
    return null;
  }

  @Override
  public boolean deletePurchases(int customerId) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "DELETE FROM purchases p WHERE p.customer_fk=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, customerId);

      query.executeUpdate();

      return true;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to delete purchases by customer ID***");
    }
    return false;
  }
}
