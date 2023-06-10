package com.revature.dao;

import com.revature.dao.interfaces.CustomerDAOInterface;
import com.revature.dao.interfaces.ProfessionDAOInterface;
import com.revature.dao.interfaces.PurchaseDAOInterface;
import com.revature.models.Customer;
import com.revature.util.ConnectionUtility;
import org.postgresql.replication.ReplicationSlotInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerDAOInterface {
  private static final Logger logger =
    LoggerFactory.getLogger(CustomerDAO.class);

  @Override
  public Customer getCustomer(int cid) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "SELECT first_name, last_name, profession_fk FROM customers WHERE id=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, cid);

      ResultSet rs = query.executeQuery();

      if (rs.next()) {
        ProfessionDAO pDAO = new ProfessionDAO();
        PurchaseDAO purDAO = new PurchaseDAO();

        return new Customer(
          cid,
          rs.getString("first_name"),
          rs.getString("last_name"),
          pDAO.getProfession(rs.getInt("profession_fk"), conn),
          purDAO.getCustomerPurchases(cid, conn)
        );
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get customer with ID***");
    }
    return null;
  }

  @Override
  public Customer getCustomer(String firstName, String lastName) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "SELECT id, profession_fk FROM customers WHERE first_name=? AND last_name=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setString(1, firstName);
      query.setString(2, lastName);

      ResultSet rs = query.executeQuery();

      if (rs.next()) {
        ProfessionDAO pDAO = new ProfessionDAO();
        PurchaseDAO purDAO = new PurchaseDAO();
        int id = rs.getInt("id");

        return new Customer(
          id,
          firstName,
          lastName,
          pDAO.getProfession(rs.getInt("profession_fk"), conn),
          purDAO.getCustomerPurchases(id, conn)
        );
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get customer with first/last name***");
    }
    return null;
  }

  @Override
  public List<Customer> getAllCustomers() {
    long timeTaken = System.currentTimeMillis();
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "SELECT id, first_name, last_name, profession_fk FROM customers";
      Statement query = conn.createStatement();

      ResultSet rs = query.executeQuery(sql);
      List<Customer> customers = new ArrayList<>();
      ProfessionDAO pDAO = new ProfessionDAO();
      PurchaseDAO purDAO = new PurchaseDAO();

      while (rs.next()) {
        int id = rs.getInt("id");

        customers.add(
          new Customer(
            id,
            rs.getString("first_name"),
            rs.getString("last_name"),
            pDAO.getProfession(rs.getInt("profession_fk"), conn),
            purDAO.getCustomerPurchases(id, conn)
          )
        );
      }

      timeTaken = System.currentTimeMillis() - timeTaken;
      logger.info("Took " + timeTaken + "ms to retrieve all customers.");

      return customers;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get all customers***");
    }
    return null;
  }

  @Override
  public Customer addNewCustomer(Customer c) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "INSERT INTO customers (first_name, last_name, profession_fk) VALUES (?, ?, ?) RETURNING *";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setString(1, c.getFirstName());
      query.setString(2, c.getLastName());
      query.setInt(3, c.getProfessionId());

      ResultSet rs = query.executeQuery();
      ProfessionDAO pDAO = new ProfessionDAO();
      PurchaseDAO purDAO = new PurchaseDAO();

      if (rs.next()) {
        int cid = rs.getInt("id");

        logger.info(
          "Added cid: " + cid + " name: " + c.getFirstName() + " " +
          c.getLastName());

        return new Customer(
          cid,
          c.getFirstName(),
          c.getLastName(),
          pDAO.getProfession(c.getProfessionId()),
          purDAO.getCustomerPurchases(cid)
        );
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to add new customer***");
    }
    return null;
  }

  @Override
  public Customer updateCustomer(Customer c) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String select =
        "SELECT id, first_name, last_name, profession_fk FROM customers WHERE id=?";
      PreparedStatement query = conn.prepareStatement(select);

      query.setInt(1, c.getId());

      ResultSet rs = query.executeQuery();
      Customer dbRecord = null;
      ProfessionDAOInterface pDAO = new ProfessionDAO();
      PurchaseDAOInterface pursDAO = new PurchaseDAO();

      if (rs.next()) {
        dbRecord = new Customer(
          rs.getInt("id"),
          rs.getString("first_name"),
          rs.getString("last_name"),
          pDAO.getProfession(rs.getInt("profession_fk"), conn),
          pursDAO.getCustomerPurchases(rs.getInt("id"), conn)
        );
      }

      if (c.getId() != dbRecord.getId()) {
        return null;
      }

      if (!c.getFirstName().isEmpty() &&
          !c.getFirstName().equals(dbRecord.getFirstName())) {
        String sql = "UPDATE customers SET first_name=? WHERE id=?";
        query = conn.prepareStatement(sql);

        query.setString(1, c.getFirstName());
        query.setInt(2, c.getId());

        query.executeUpdate();
      }

      if (!c.getLastName().isEmpty() &&
          !c.getLastName().equals(dbRecord.getLastName())) {
        String sql = "UPDATE customers SET last_name=? WHERE id=?";
        query = conn.prepareStatement(sql);

        query.setString(1, c.getLastName());
        query.setInt(2, c.getId());

        query.executeUpdate();
      }

      if (c.getProfessionId() != -1 ||
          c.getProfession() != null &&
          c.getProfessionId() != dbRecord.getProfessionId() ||
          c.getProfession().getId() != dbRecord.getProfession().getId()) {
        String sql = "UPDATE customers SET profession_fk=? WHERE id=?";
        query = conn.prepareStatement(sql);

        if (c.getProfessionId() != -1) {
          query.setInt(1, c.getProfessionId());
          query.setInt(2, c.getId());
        } else if (c.getProfession() != null) {
          query.setInt(1, c.getProfession().getId());
          query.setInt(2, c.getId());
        }

        query.executeUpdate();
      }

      logger.info(
        "Successfully updated " + c.getFirstName() + " " + c.getLastName() +
        "'s personal information.");
      return c;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to the database to update customer information***");
    }

    return null;
  }

  @Override
  public boolean deleteCustomer(int id) {
    PurchaseDAO pDAO = new PurchaseDAO();

    pDAO.deleteCustomerPurchases(id);

    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "DELETE FROM customers c WHERE c.id=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, id);

      if (query.executeUpdate() > 0) {
        return true;
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to delete customer by ID***");
    }

    return false;
  }
}
