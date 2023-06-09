package com.revature.controllers;

import com.revature.dao.ItemTypeDAO;
import com.revature.services.ItemTypeService;
import io.javalin.http.Context;

public class ItemTypeController {
  private final ItemTypeService itServ;

  public ItemTypeController() {
    itServ = new ItemTypeService(new ItemTypeDAO());
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
