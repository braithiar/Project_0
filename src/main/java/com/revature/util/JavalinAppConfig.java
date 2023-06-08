package com.revature.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;

public class JavalinAppConfig {
  private Gson gson;
  private Javalin app;
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
    gson = new GsonBuilder().create();
    app = Javalin.create(config -> config.jsonMapper(gsonMapper))
                 .routes(() -> {

                 });
  }

  public void start(int port) {
    app.start(port);
  }
}
