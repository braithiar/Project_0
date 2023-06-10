package com.revature.controllers;

import com.revature.dao.ItemTypeDAO;
import com.revature.models.ItemType;
import com.revature.services.ItemTypeService;
import io.javalin.http.Context;

import java.util.List;

public class ItemTypeController {
  private final ItemTypeService itServ;

  public ItemTypeController() {
    itServ = new ItemTypeService(new ItemTypeDAO());
  }

  public void handleGetOne(Context ctx) {
    int id;

    try {
      id = Integer.parseInt(ctx.pathParam("id"));
    } catch (NumberFormatException nfe) {
      ctx.status(400);
      return;
    }

    ItemType type = itServ.getItemType(id);

    if (type != null) {
      ctx.json(type);
    } else {
      ctx.status(404);
    }
  }

  public void handleGetAll(Context ctx) {
    List<ItemType> types = itServ.getAllItemTypes();

    if (types != null && types.size() > 0) {
      ctx.json(types);
    } else {
      ctx.status(404);
    }
  }

  public void handleCreate(Context ctx) {
    ItemType toCreate = ctx.bodyAsClass(ItemType.class);
    ItemType created = itServ.createItemType(toCreate);

    if (created != null) {
      ctx.status(201);
      ctx.json(created);
    } else {
      ctx.status(400);
    }
  }

  public void handleUpdate(Context ctx) {
    ItemType toUpdate = ctx.bodyAsClass(ItemType.class);
    ItemType updated = itServ.updateItemType(toUpdate);

    if (updated != null) {
      ctx.json(updated);
    } else {
      ctx.status(400);
    }
  }

  /*public void handleDelete(Context ctx) {

  }*/
}
