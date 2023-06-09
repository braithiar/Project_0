package com.revature.dao.interfaces;

import com.revature.models.Customer;

import java.util.List;

public interface CustomerDAOInterface {
  Customer getCustomer(int cid);
  Customer getCustomer(String firstName, String lastName);
  List<Customer> getAllCustomers();
  Customer addNewCustomer(Customer c);
  Customer updateCustomer(Customer c);
  boolean deleteCustomer(int id);
}