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

    if (purchases != null && purchases.size() > 0) {
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
    Purchase toCreate = ctx.bodyAsClass(Purchase.class);
    Purchase created = purServ.createPurchase(toCreate);

    if (created != null) {
      ctx.status(201);
      ctx.json(created);
    } else {
      ctx.status(400);
    }
  }

  public void handleUpdate(Context ctx) {
    Purchase toUpdate = ctx.bodyAsClass(Purchase.class);
    Purchase updated = purServ.updatePurchase(toUpdate);

    if (updated != null) {
      ctx.json(updated);
    } else {
      ctx.status(400);
    }
  }

  public void handleDelete(Context ctx) {
    Purchase toDelete = ctx.bodyAsClass(Purchase.class);
    Purchase deleted = purServ.deletePurchase(toDelete);

    if (deleted != null) {
      ctx.json(deleted);
    } else {
      ctx.status(404);
    }
  }

  public void handleDeleteAll(Context ctx) {
    int cid;

    try {
      cid = Integer.parseInt(ctx.pathParam("cid"));
    } catch (NumberFormatException nfe) {
      ctx.status(400);
      return;
    }

    List<Purchase> deleted = purServ.deletePurchases(cid);

    if (deleted != null && deleted.size() > 0) {
      ctx.json(deleted);
    } else {
      ctx.status(404);
    }
  }
}
