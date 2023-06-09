package com.revature.controllers;

import com.revature.dao.ItemDAO;
import com.revature.services.ItemService;
import io.javalin.http.Context;

public class ItemController {
  private final ItemService iServ;

  public ItemController() {
    iServ = new ItemService(new ItemDAO());
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
