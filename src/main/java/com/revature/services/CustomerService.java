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

    char[] fNameChars = firstName.toLowerCase().toCharArray();
    StringBuilder firstFormatted = new StringBuilder();

    firstFormatted.append(Character.toUpperCase(fNameChars[0]));

    for (int i = 1; i < fNameChars.length; ++i) {
      if (fNameChars[i - 1] == ' ') {
        firstFormatted.append(Character.toUpperCase(fNameChars[i]));
        continue;
      }

      firstFormatted.append(fNameChars[i]);
    }

    char[] lNameChars = lastName.toLowerCase().toCharArray();
    StringBuilder lastFormatted = new StringBuilder();
    int index = 1;

    if (lastName.toLowerCase().startsWith("mc") ||
        lastName.toLowerCase().startsWith("o'")) {
      lastFormatted.append(Character.toUpperCase(lNameChars[0]));
      lastFormatted.append(lNameChars[1]);
      lastFormatted.append(Character.toUpperCase(lNameChars[2]));

      index = 3;
    } else {
      lastFormatted = new StringBuilder(
        Character.toUpperCase(lNameChars[0])
      );
    }

    for (int i = index; i < lNameChars.length; ++i) {
      if (lNameChars[i - 1] == ' ') {
        lastFormatted.append(Character.toUpperCase(lNameChars[i]));
        continue;
      }

      lastFormatted.append(lNameChars[i]);
    }

    if (firstFormatted.length() > 0 && lastFormatted.length() > 0) {
      return cDAO.getCustomer(firstFormatted.toString(),
                              lastFormatted.toString());
    }

    return null;
  }

  public List<Customer> getAllCustomers() {
    return cDAO.getAllCustomers();
  }

  public Customer addNewCustomer(Customer c) {
    if (c != null && c.getProfessionId() > 0) {
      return cDAO.addNewCustomer(c);
    }

    return null;
  }

  public Customer updateCustomer(Customer c) {
    if (c != null) {
      return cDAO.updateCustomer(c);
    }

    return null;
  }

  public boolean deleteCustomer(int id) {
    if (id > 0) {
      return cDAO.deleteCustomer(id);
    }

    return false;
  }
}
