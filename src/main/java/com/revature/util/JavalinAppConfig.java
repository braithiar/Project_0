package com.revature.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.controllers.*;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinAppConfig {
  private Gson gson;
  private Javalin app;
  private final CustomerController cCon;
  private final EnchantmentController eCon;
  private final ItemController iCon;
  private final ItemTypeController itCon;
  private final ProfessionController pCon;
  private final PurchaseController purCon;
  private static final Logger logger =
    LoggerFactory.getLogger(JavalinAppConfig.class);
  private JsonMapper gsonMapper = new JsonMapper() {
    @Override
    public String toJsonString(@NotNull Object obj, @NotNull Type type) {
      return gson.toJson(obj, type);
    }

    public <T> T fromJsonString(@NotNull String json,
                                @NotNull Type targetType) {
      return gson.fromJson(json, targetType);
    }
  };

  public JavalinAppConfig() {
    cCon = new CustomerController();
    eCon = new EnchantmentController();
    iCon = new ItemController();
    itCon = new ItemTypeController();
    pCon = new ProfessionController();
    purCon = new PurchaseController();

    gson = new GsonBuilder().create();
    app = Javalin.create(config -> config.jsonMapper(gsonMapper))
                 .before("/*", ctx -> {
                   logger.info("{} request to {}", ctx.method(), ctx.fullUrl());
                 })
                 .routes(() -> {
                   path("customers", () -> {
                     get(cCon::handleGetAll);
                     post(cCon::handleCreate);
                     put(cCon::handleUpdate);
                     delete(cCon::handleDelete);
                     path("{cid}", () -> {
                       get(cCon::handleGetOne);
                     });
                   });
                   path("enchantments", () -> {
                     get(eCon::handleGetAll);
                     post(eCon::handleCreate);
                     put(eCon::handleUpdate);
                     delete(eCon::handleDelete);
                   });
                   path("items", () -> {
                     get(iCon::handleGetAll);
                     post(iCon::handleCreate);
                     put(iCon::handleUpdate);
                     delete(iCon::handleDelete);
                     path("{id}", () -> {
                       get(iCon::handleGetOne);
                     });
                   });
                   path("types", () -> {
                     get(itCon::handleGetAll);
                     post(itCon::handleCreate);
                     put(itCon::handleUpdate);
                     delete(itCon::handleDelete);
                   });
                   path("professions", () -> {
                     get(pCon::handleGetAll);
                     post(pCon::handleCreate);
                     put(pCon::handleUpdate);
                     delete(pCon::handleDelete);
                   });
                   path("purchases", () -> {
                     get(purCon::handleGetAll);
                     post(purCon::handleCreate);
                     put(purCon::handleUpdate);
                     delete(purCon::handleDelete);
                     path("{pid}", () -> {
                       get(purCon::handleGetOne);
                       //delete(purCon::handleDeleteAll);
                     });
                   });
                 });
  }

  public void start(int port) {
    app.start(port);
  }
}
