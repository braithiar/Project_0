package com.revature.dao;

import com.revature.dao.interfaces.CustomerDAOInterface;
import com.revature.models.Customer;
import com.revature.util.ConnectionUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements CustomerDAOInterface {
  private static final Logger logger =
    LoggerFactory.getLogger(CustomerDAO.class);

  @Override
  public Customer getCustomer(int id) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "SELECT first_name, last_name, profession_fk FROM customers WHERE id=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, id);

      ResultSet rs = query.executeQuery();

      if (rs.next()) {
        ProfessionDAO pDAO = new ProfessionDAO();
        PurchaseDAO purDAO = new PurchaseDAO();

        return new Customer(
          id,
          rs.getString("first_name"),
          rs.getString("last_name"),
          pDAO.getProfession(rs.getInt("profession_fk")),
          purDAO.getCustomerPurchases(id)
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
          pDAO.getProfession(rs.getInt("profession_fk")),
          purDAO.getCustomerPurchases(id)
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
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "SELECT id, first_name, last_name, profession_fk FROM customers";
      Statement query = conn.createStatement();

      ResultSet rs = query.executeQuery(sql);
      List<Customer> customers = new ArrayList<>();

      while (rs.next()) {
        ProfessionDAO pDAO = new ProfessionDAO();
        PurchaseDAO purDAO = new PurchaseDAO();
        int id = rs.getInt("id");

        customers.add(
          new Customer(
            id,
            rs.getString("first_name"),
            rs.getString("last_name"),
            pDAO.getProfession(rs.getInt("profession_fk")),
            purDAO.getCustomerPurchases(id)
          )
        );
      }
      return customers;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to get all customers***");
    }
    return null;
  }

  @Override
  public boolean addNewCustomer(Customer c) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "INSERT INTO customers (first_name, last_name, profession_fk) VALUES (?, ?, ?)";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setString(1, c.getFirstName());
      query.setString(2, c.getLastName());
      query.setInt(3, c.getProfessionId());

      query.executeUpdate();

      return true;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to add new customer***");
    }
    return false;
  }

  @Override
  public boolean updateCustomerFirstName(int id, String newFirstName) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "UPDATE customers SET first_name=? WHERE id=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setString(1, newFirstName);
      query.setInt(2, id);

      query.executeUpdate();

      return true;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to update customer first name by ID***");
    }
    return false;
  }

  @Override
  public boolean updateCustomerLastName(int id, String newLastName) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "UPDATE customers SET last_name=? WHERE id=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setString(1, newLastName);
      query.setInt(2, id);

      query.executeUpdate();

      return true;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to update customer last name by ID***");
    }
    return false;
  }

  @Override
  public boolean updateCustomerProfession(int id, int newProfessionId) {
    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql = "UPDATE customers SET profession_fk=? WHERE id=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, newProfessionId);
      query.setInt(2, id);

      query.executeUpdate();

      return true;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to update customer profession***");
    }
    return false;
  }

  @Override
  public boolean deleteCustomer(int id) {
    // TODO: implement in CustomerService/PurchaseService
    PurchaseDAO pDAO = new PurchaseDAO();

    if (!pDAO.deletePurchases(id)) {
      return false;
    }

    try (Connection conn = ConnectionUtility.getConnection()) {
      String sql =
        "DELETE FROM customers c WHERE c.id=?";
      PreparedStatement query = conn.prepareStatement(sql);

      query.setInt(1, id);

      query.executeUpdate();

      return true;
    } catch (SQLException sqle) {
      sqle.printStackTrace();
      logger.warn(
        "***Could not connect to database to delete customer by ID***");
    }
    return false;
  }
}
