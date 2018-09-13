package web.rest;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import calculator.Calculator;
import calculator.CalculatorImpl;
import entities.Employee;
import entities.EmployeesStorageImpl;
import entities.Project;
import entities.ProjectStorageImpl;
import entities.Storage;
import io.DataReader;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import parser.Parser;
import parser.ParserImpl;

public class RestAPI extends AbstractVerticle {
  /* API paths */
  private static final String BASE = "/api/";
  private static final String FILE_PROCESS = BASE + "fileProcess";
  private static final String GET_DATA = BASE + "getData";
  private static final String FILE_PROCESS_WILDCARD = GET_DATA + "/*";

  private int port;
  private Calculator calculator;
  private Parser parser;

  public RestAPI(int port, Parser parser, Calculator calculator) {
      this.port = port;
      this.parser = parser;
      this.calculator = calculator;
  }

  @Override
  public void start() throws Exception {
      System.out.println("Starting REST Server.");
      Router router = Router.router(vertx);
      Set<HttpMethod> allowedMethods = new LinkedHashSet<>(
              Arrays.asList(HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS));
      router.route().handler(CorsHandler.create("*").allowedMethods(allowedMethods));      
      router.route(FILE_PROCESS).handler(BodyHandler.create());
      router.post(FILE_PROCESS).handler(this::processFile);


      vertx.createHttpServer().requestHandler(router::accept).listen(9777, resultOfStart -> {
          if (resultOfStart.succeeded()) {
              System.out.println("REST Server started and listening on port " + this.port);
          } else {
              System.err.println("REST Server could NOT start on port: " + this.port + ". Reason: "
                      + resultOfStart.cause().getMessage());
          }
      });
  }

  private void processFile(RoutingContext routingContext) {      
    String dataProcessed = getDataString(routingContext);
    routingContext.response().putHeader("Content-Type", "application/json").end(dataProcessed);
  }
  
  private String getDataString(RoutingContext routingContext) {
    this.parser.clearStorages();
    this.calculator.clearCalculations();
    
    JsonObject jsonPostObject = new JsonObject(routingContext.getBodyAsString());
    String[] data = jsonPostObject.getString("file").split("\r\n");
    
    Arrays.asList(data).forEach(line -> {
      this.parser.parseLine(line);
    });
    this.calculator.calculate();    
    
    return this.calculator.getJSONData();
  }
}
