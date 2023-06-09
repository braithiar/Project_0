package com.revature.controllers;

import com.revature.dao.PurchaseDAO;
import com.revature.models.Purchase;
import com.revature.services.PurchaseService;
import io.javalin.http.Context;

import java.util.List;

public class PurchaseController {
  private final PurchaseService purServ;

  public PurchaseController() {
    purServ = new PurchaseService(new PurchaseDAO());
  }

  public void handleGetAll(Context ctx) {
    List<Purchase> purchases = purServ.getAllPurchases();

    if (purchases != null) {
      ctx.json(purchases);
    } else {
      ctx.status(400);
    }
  }

  public void handleGetOne(Context ctx) {
    int pid;

    try {
      pid = Integer.parseInt(ctx.pathParam("pid"));
    } catch (NumberFormatException nfe) {
      ctx.status(400);
      return;
    }

    Purchase p = purServ.getOnePurchase(pid);

    if (p != null) {
      ctx.json(p);
    } else {
      ctx.status(404);
    }
  }

  public void handleCreate(Context ctx) {

  }

  public void handleUpdate(Context ctx) {

  }

  public void handleDelete(Context ctx) {

  }

  public void handleDeleteAll(Context ctx) {

  }
}
