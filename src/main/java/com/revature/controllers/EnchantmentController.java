package com.revature.controllers;

import com.revature.dao.EnchantmentDAO;
import com.revature.services.EnchantmentService;
import io.javalin.http.Context;

public class EnchantmentController {
  private final EnchantmentService eServ;

  public EnchantmentController() {
    eServ = new EnchantmentService(new EnchantmentDAO());
  }

  public void handleGetAll(Context ctx) {

  }

  public void handleCreate(Context ctx) {

  }

  public void handleUpdate(Context ctx) {

  }

  public void handleDelete(Context ctx) {

  }
}
