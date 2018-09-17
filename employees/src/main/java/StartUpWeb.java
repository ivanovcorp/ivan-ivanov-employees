import static java.time.temporal.ChronoUnit.DAYS;

import calculator.Calculator;
import calculator.CalculatorImpl;
import entities.Employee;
import entities.EmployeesStorageImpl;
import entities.Project;
import entities.ProjectStorageImpl;
import entities.Storage;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import java.time.LocalDate;
import parser.Parser;
import parser.ParserImpl;
import web.rest.RestAPI;
import web.service.WebService;

public class StartUpWeb {
  
  private static final int PORT_WEB_SERVER = 9213;
  private static final int PORT_REST_SERVER = 9777;
  
  public static void main(String[] args) {
    Storage<Employee> employeeStorage = EmployeesStorageImpl.getInstance();
    Storage<Project> projectStorage = ProjectStorageImpl.getInstance();
    Parser parser = new ParserImpl(projectStorage, employeeStorage);
    Calculator calculator = new CalculatorImpl(employeeStorage);
    
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new WebService(PORT_WEB_SERVER));
    vertx.deployVerticle(new RestAPI(PORT_REST_SERVER, parser, calculator));
    
  }
   
  
}
