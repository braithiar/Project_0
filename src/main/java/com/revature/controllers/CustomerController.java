package com.revature.controllers;

import com.revature.dao.CustomerDAO;
import com.revature.services.CustomerService;
import io.javalin.http.Context;

public class CustomerController {
  private final CustomerService cServ;

  public CustomerController() {
    cServ = new CustomerService(new CustomerDAO());
  }

  public void handleGetAll(Context ctx) {

  }

  public void handleGetOne(Context ctx) {

  }

  public void handleCreate(Context ctx) {

  }

  public void handleUpdate(Context ctx) {

  }

  public void handleDelete(Context ctx) {

  }
}
