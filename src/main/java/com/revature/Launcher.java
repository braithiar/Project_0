package com.revature;

import com.revature.dao.CustomerDAO;
import com.revature.models.Customer;
import com.revature.services.CustomerService;
import com.revature.util.JavalinAppConfig;

import java.util.List;

public class Launcher {
  public static void main(String[] args) {
    JavalinAppConfig app = new JavalinAppConfig();

    app.start(7070);

    CustomerService cs = new CustomerService(new CustomerDAO());
    List<Customer> custs = cs.getAllCustomers();

    for (Customer c : custs) {
      System.out.println(c);
    }
  }
}
