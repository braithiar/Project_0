package com.revature.controllers;

import com.revature.dao.ProfessionDAO;
import com.revature.models.Profession;
import com.revature.services.ProfessionService;
import io.javalin.http.Context;

import java.util.List;

public class ProfessionController {
  private final ProfessionService pServ;

  public ProfessionController() {
    pServ = new ProfessionService(new ProfessionDAO());
  }

  public void handleGetOne(Context ctx) {
    int pid;

    try {
      pid = Integer.parseInt(ctx.pathParam("pid"));
    } catch (NumberFormatException nfe) {
      ctx.status(400);
      return;
    }

    Profession prof = pServ.getProfession(pid);

    if (prof != null) {
      ctx.json(prof);
    } else {
      ctx.status(404);
    }
  }

  public void handleGetAll(Context ctx) {
    List<Profession> profs = pServ.getAllProfessions();

    if (profs != null && profs.size() > 0) {
      ctx.json(profs);
    } else {
      ctx.status(404);
    }
  }

  public void handleCreate(Context ctx) {
    Profession toCreate = ctx.bodyAsClass(Profession.class);
    Profession created = pServ.createProfession(toCreate);

    if (created != null) {
      ctx.status(201);
      ctx.json(created);
    } else {
      ctx.status(400);
    }
  }

  public void handleUpdate(Context ctx) {
    Profession toUpdate = ctx.bodyAsClass(Profession.class);
    Profession updated = pServ.updateProfession(toUpdate);

    if (updated != null) {
      ctx.json(updated);
    } else {
      ctx.status(400);
    }
  }

  /*public void handleDelete(Context ctx) {
    int pid;

    try {
      pid = Integer.parseInt(ctx.pathParam("pid"));
    } catch (NumberFormatException nfe) {
      ctx.status(400);
      return;
    }

    Profession prof = pServ.deleteProfession(pid);

    if (prof != null) {
      ctx.json(prof);
    } else {
      ctx.status(404);
    }
  }*/
}
