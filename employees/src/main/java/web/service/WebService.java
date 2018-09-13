package web.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public final class WebService extends AbstractVerticle {
  private static final String RESOURCE_PATH = "web-resources";
  private static final String INDEX_ROUTE_PATH = "/home";  
  
  private static final String INDEX_PAGE_RESOURCE = INDEX_ROUTE_PATH + ".html";  
  
  private int port;

  public WebService(int port) {
      this.port = port;
  }

  @Override
  public void start() throws Exception {
      System.out.println("Starting WebService.");
      Router router = Router.router(vertx);

      router.route(INDEX_ROUTE_PATH).handler(StaticHandler.create(RESOURCE_PATH + INDEX_PAGE_RESOURCE));      

      vertx.createHttpServer().requestHandler(router::accept).listen(this.port, resultOfStart -> {
          if (resultOfStart.succeeded()) {
              System.out.println("WebServer started and listening on port " + this.port);
          } else {
            System.err.println("WebServer could NOT start on port: " + this.port + ". Reason: "
                      + resultOfStart.cause().getMessage());
          }
      });
  }
}
