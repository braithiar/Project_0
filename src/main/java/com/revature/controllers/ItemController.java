package com.revature.controllers;

import com.revature.dao.ItemDAO;
import com.revature.models.Item;
import com.revature.services.ItemService;
import io.javalin.http.Context;

import java.util.List;

public class ItemController {
  private final ItemService iServ;

  public ItemController() {
    iServ = new ItemService(new ItemDAO());
  }

  public void handleGetAll(Context ctx) {
    List<Item> items = iServ.getAllItems();

    if (items != null) {
      ctx.json(items);
    } else {
      ctx.status(404);
    }
  }

  public void handleGetOne(Context ctx) {
    int id;

    try {
      id = Integer.parseInt(ctx.pathParam("id"));
    } catch (NumberFormatException nfe) {
      ctx.status(400);
      return;
    }

    Item i = iServ.getItem(id);

    if (i != null) {
      ctx.json(i);
    } else {
      ctx.status(404);
    }
  }

  public void handleCreate(Context ctx) {
    Item toCreate = ctx.bodyAsClass(Item.class);
    Item created = iServ.addItem(toCreate);

    if (created != null) {
      ctx.status(201);
      ctx.json(created);
    } else {
      ctx.status(400);
    }
  }

  public void handleUpdate(Context ctx) {
    Item toUpdate = ctx.bodyAsClass(Item.class);
    Item updated = iServ.updateItem(toUpdate);

    if (updated != null) {
      ctx.status(201);
      ctx.json(updated);
    } else {
      ctx.status(400);
    }
  }
}
