package com.revature.controllers;

import com.revature.dao.CustomerDAO;
import com.revature.models.Customer;
import com.revature.services.CustomerService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomerController {
  private final CustomerService cServ;
  private static final Logger logger =
    LoggerFactory.getLogger(CustomerDAO.class);

  public CustomerController() {
    cServ = new CustomerService(new CustomerDAO());
  }

  public void handleGetAll(Context ctx) {
    List<Customer> customers = cServ.getAllCustomers();

    if (customers != null) {
      logger.info("handleGetAll(Context) retrieved all customers in {}",
                  CustomerController.class);
      ctx.json(customers);
    } else {
      logger.warn(
        "handleGetAll(Context) failed to retrieve all customer data {}",
        CustomerController.class);
      ctx.status(404);
    }
  }

  public void handleGetOne(Context ctx) {
    int cid;

    try {
      cid = Integer.parseInt(ctx.pathParam("cid"));
    } catch (NumberFormatException nfe) {
      logger.warn("{} in handleGetOne(Context) in {}",
                  NumberFormatException.class.getSimpleName(),
                  CustomerController.class);
      ctx.status(400);
      return;
    }

    Customer cust = cServ.getCustomer(cid);

    if (cust != null) {
      logger.info("{}#handleGetOne(Context) retrieved {} with id: {}}",
                  CustomerController.class,
                  cust.getFirstName() + " " + cust.getLastName(), cid);
      ctx.json(cust);
    } else {
      logger.warn(
        "{}#handleGetONe(Context) failed to find customer with id: {}",
        CustomerController.class, cid);
      ctx.status(404);
    }
  }

  public void handleCreate(Context ctx) {
    Customer toCreate = ctx.bodyAsClass(Customer.class);
    Customer created = cServ.addNewCustomer(toCreate);

    if (created != null) {
      logger.info(
        "{}#handleCreate(Context) successfully created customer {} with id: {}",
        CustomerController.class,
        created.getFirstName() + " " + created.getLastName(), created.getId());
      ctx.status(201);
      ctx.json(created);
    } else {
      logger.warn(
        "{}#handleCreate(Context) was unable to create new customer with {}",
        CustomerController.class, toCreate.toString());
      ctx.status(400);
    }
  }

  public void handleUpdate(Context ctx) {
    Customer toUpdate = ctx.bodyAsClass(Customer.class);
    Customer updated = cServ.updateCustomer(toUpdate);

    if (updated != null) {
      logger.info(
        "{}#handleUpdate(Context) successfully created customer {} with id: {}",
        CustomerController.class,
        updated.getFirstName() + " " + updated.getLastName(), updated.getId());
      ctx.json(updated);
    } else {
      logger.warn(
        "{}#handleUpdate(Context) was unable to update customer with id {}",
        CustomerController.class, toUpdate.getId());
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
      logger.warn(
        "{}#handleDelete(Context) was unable to delete customer with id {}",
        CustomerController.class, cid);
      ctx.status(404);
    }
  }
}
