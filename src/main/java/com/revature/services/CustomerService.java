package com.revature.services;

import com.revature.dao.interfaces.CustomerDAOInterface;
import com.revature.models.Customer;

import java.util.List;

public class CustomerService {
  private final CustomerDAOInterface cDAO;

  public CustomerService(CustomerDAOInterface cDAO) {
    this.cDAO = cDAO;
  }

  public Customer getCustomer(int id) {
    if (id > 0) {
      return cDAO.getCustomer(id);
    }

    return null;
  }

  public Customer getCustomer(String firstName, String lastName) {
    if (firstName == null || lastName == null || firstName.trim().isEmpty() ||
        lastName.trim().isEmpty()) {
      return null;
    }

    StringBuilder formattedFName = new StringBuilder("");
    StringBuilder formattedLName = new StringBuilder("");

    /*char[] fNameChars = firstName.toCharArray();
    StringBuilder firstFormatted = new StringBuilder(
      Character.toUpperCase(fNameChars[0])
    );

    for (int i = 1; i < fNameChars.length; ++i) {
      if (fNameChars[i-1] == ' ') {
        firstFormatted.append(Character.toUpperCase(fNameChars[i]));
        continue;
      }

      firstFormatted.append(fNameChars[i]);
    }

    char[] lNameChars = lastName.toCharArray();
    StringBuilder lastFormatted = new StringBuilder(
      Character.toUpperCase(lNameChars[0])
    );

    for (int i = 1; i < lNameChars.length; ++i) {
      if (fNameChars[i-1] == ' ') {
        firstFormatted.append(Character.toUpperCase(lNameChars[i]));
        continue;
      }

      firstFormatted.append(lNameChars[i]);
    }*/

    return null;
  }

  public List<Customer> getAllCustomers() {
    return null;
  }

  public boolean addNewCustomer(Customer c) {
    return false;
  }

  public boolean updateCustomerFirstName(int id, String newFirstName) {
    return false;
  }

  public boolean updateCustomerLastName(int id, String newLastName) {
    return false;
  }

  public boolean updateCustomerProfession(int id, int newProfessionId) {
    return false;
  }

  public boolean deleteCustomer(int id) {
    return false;
  }
}
