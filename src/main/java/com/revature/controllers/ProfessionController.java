package com.revature.controllers;

import com.revature.dao.ProfessionDAO;
import com.revature.services.ProfessionService;
import io.javalin.http.Context;

public class ProfessionController {
  private final ProfessionService pServ;

  public ProfessionController() {
    pServ = new ProfessionService(new ProfessionDAO());
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
