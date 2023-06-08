package com.revature;

import com.revature.dao.CustomerDAO;
import com.revature.models.Customer;
import com.revature.util.JavalinAppConfig;

public class Launcher {
  public static void main(String[] args) {
    JavalinAppConfig app = new JavalinAppConfig();

    app.start(7070);

    CustomerDAO cd = new CustomerDAO();
    Customer c = new Customer("jt", "hugsnstuff", 2);
    System.out.println(cd.getCustomer(2));
  }
}
