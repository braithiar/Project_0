package com.revature.dao;

import com.revature.dao.interfaces.PurchaseDAOInterface;
import com.revature.models.Item;
import com.revature.models.Purchase;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO implements PurchaseDAOInterface {
  private static final Logger logger =
    LoggerFactory.getLogger(PurchaseDAO.class);

  @Override
  public List<Purchase> getCustomerPurchases(int cid) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      getCustomerPurchases(cid, conn);
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get customer purchases***");
    }
    return null;
  }

  @Override
  public List<Purchase> getCustomerPurchases(int cid, Connection conn) throws
    SQLException {
    String sql =
      "SELECT id, customer_fk, item_fk FROM purchases WHERE customer_fk=?";
    PreparedStatement query = conn.prepareStatement(sql);

    query.setInt(1, cid);

    ResultSet rs = query.executeQuery();
    List<Purchase> purchases = new ArrayList<>();
    ItemDAO iDAO = new ItemDAO();

    while (rs.next()) {


      purchases.add(
        new Purchase(
          rs.getInt("id"),
          rs.getInt("customer_fk"),
          iDAO.getItem(rs.getInt("item_fk"), conn)
        )
      );
    }

    if (!purchases.isEmpty() && purchases != null) {
      return purchases;
    }

    return null;
  }

  @Override
  public List<Purchase> getAllPurchases() {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "SELECT id, customer_fk, item_fk FROM purchases";
      Statement query = conn.createStatement();

      ResultSet rs = query.executeQuery(sql);
      List<Purchase> purchases = new ArrayList<>();
      ItemDAO iDAO = new ItemDAO();

      while (rs.next()) {
        purchases.add(
          new Purchase(
            rs.getInt("id"),
            rs.getInt("customer_fk"),
            iDAO.getItem(rs.getInt("item_fk"), conn)
          )
        );
      }

      return purchases;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get ALL purchases***");
    }

    return null;
  }

  @Override
  public Purchase getOnePurchase(int pid) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "SELECT customer_fk, item_fk FROM purchases WHERE id=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, pid);

      ResultSet rs = query.executeQuery();
      ItemDAO iDAO = new ItemDAO();

      if (rs.next()) {
        return new Purchase(
          pid,
          rs.getInt("customer_fk"),
          iDAO.getItem(rs.getInt("item_fk"))
        );
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get purchase by ID***");
    }

    return null;
  }

  @Override
  public Purchase createPurchase(Purchase p) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "INSERT INTO purchases (customer_fk, item_fk) VALUES (?, ?) RETURNING *";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, p.getCustId());
      query.setInt(2, p.getItemId());

      ResultSet rs = query.executeQuery();
      ItemDAO iDAO = new ItemDAO();

      if (rs.next()) {
        int id = rs.getInt("id");
        Item item = iDAO.getItem(p.getItemId());

        logger.info("Added new purchase, id: " + id + " for " + item.getName());

        return new Purchase(
          id,
          p.getCustId(),
          item
        );
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn("***Could not connect to database to create new purchase***");
    }

    return null;
  }

  @Override
  public Purchase updatePurchase(Purchase p) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String select =
        "SELECT id, customer_fk, item_fk FROM purchases WHERE id=?";
      PreparedStatement query = conn.prepareStatement(select);

      query.setInt(1, p.getId());

      ResultSet rs = query.executeQuery();
      Purchase dbRecord = null;
      ItemDAO iDAO = new ItemDAO();

      if (rs.next()) {
        dbRecord = new Purchase(
          rs.getInt("id"),
          rs.getInt("customer_fk"),
          iDAO.getItem(rs.getInt("item_fk"), conn)
        );
      }

      if (p.getCustId() > 0 && p.getCustId() != dbRecord.getCustId()) {
        String sql =
          "UPDATE purchases SET customer_fk=? WHERE id=?";

        query = conn.prepareStatement(sql);

        query.setInt(1, p.getCustId());
        query.setInt(2, p.getId());

        query.executeUpdate();
      }

      if (p.getItemId() > 0 && p.getItemId() != dbRecord.getItemId()) {
        String sql =
          "UPDATE purchases SET item_fk=? WHERE id=?";

        query = conn.prepareStatement(sql);

        query.setInt(1, p.getItemId());
        query.setInt(2, p.getId());

        query.executeUpdate();
      }

      if (p != null && dbRecord != null) {
        logger.info("Updated purchase id: " + p.getId() + " for custID: " +
                    p.getCustId());

        return p;
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to update customer purchase***");
    }

    return null;
  }

  @Override
  public List<Purchase> deleteCustomerPurchases(int cid) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "DELETE FROM purchases p WHERE p.customer_fk=? RETURNING *";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, cid);

      ResultSet rs = query.executeQuery();
      List<Purchase> deleted = new ArrayList<>();
      ItemDAO iDAO = new ItemDAO();

      while (rs.next()) {
        deleted.add(
          new Purchase(
            rs.getInt("id"),
            rs.getInt("customer_fk"),
            iDAO.getItem(rs.getInt("item_fk"), conn)
          )
        );
      }

      return deleted;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to delete purchases by customer ID***");
    }
    return null;
  }

  @Override
  public Purchase deletePurchase(Purchase p) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "DELETE FROM purchases p WHERE p.id=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, p.getId());

      if (query.executeUpdate() > 0) {
        logger.info(p.getId() + " deleted");
        return p;
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to delete purchase PID***");
    }

    return null;
  }
}
