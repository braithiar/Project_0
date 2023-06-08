package com.revature.dao.interfaces;

import com.revature.models.Customer;

import java.util.List;

public interface CustomerDAOInterface {
  Customer getCustomer(int id);
  Customer getCustomer(String firstName, String lastName);
  List<Customer> getAllCustomers();
  boolean addNewCustomer(Customer c);
  boolean updateCustomerFirstName(int id, String newFirstName);
  boolean updateCustomerLastName(int id, String newLastName);
  boolean updateCustomerProfession(int id, int newProfessionId);
  boolean deleteCustomer(int id);
}