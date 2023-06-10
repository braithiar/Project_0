package com.revature.controllers;

import com.revature.dao.CustomerDAO;
import com.revature.models.Customer;
import com.revature.services.CustomerService;
import io.javalin.http.Context;

import java.util.List;

public class CustomerController {
  private final CustomerService cServ;

  public CustomerController() {
    cServ = new CustomerService(new CustomerDAO());
  }

  public void handleGetAll(Context ctx) {
    List<Customer> customers = cServ.getAllCustomers();

    if (customers != null) {
      ctx.json(customers);
    } else {
      ctx.status(404);
    }
  }

  public void handleGetOne(Context ctx) {
    int cid;

    try {
      cid = Integer.parseInt(ctx.pathParam("cid"));
    } catch (NumberFormatException nfe) {
      ctx.status(400);
      return;
    }

    Customer cust = cServ.getCustomer(cid);

    if (cust != null) {
      ctx.json(cust);
    } else {
      ctx.status(404);
    }
  }

  public void handleCreate(Context ctx) {
    Customer toCreate = ctx.bodyAsClass(Customer.class);
    Customer created = cServ.addNewCustomer(toCreate);

    if (created != null) {
      ctx.status(201);
      ctx.json(created);
    } else {
      ctx.status(400);
    }
  }

  public void handleUpdate(Context ctx) {
    Customer toUpdate = ctx.bodyAsClass(Customer.class);
    Customer updated = cServ.updateCustomer(toUpdate);

    if (updated != null) {
      ctx.json(updated);
    } else {
      ctx.status(400);
    }
  }

  public void handleDelete(Context ctx) {
    int cid;

    try {
      cid = Integer.parseInt(ctx.pathParam("cid"));
    } catch (NumberFormatException nfe) {
      ctx.status(400);
      return;
    }

    if (!cServ.deleteCustomer(cid)) {
      ctx.status(404);
    }
  }
}
