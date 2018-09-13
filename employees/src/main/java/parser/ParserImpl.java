package parser;

import entities.Employee;
import entities.EmployeeImpl;
import entities.Project;
import entities.ProjectImpl;
import entities.Storage;
import java.time.LocalDate;
import parser.date.DateParser;

public final class ParserImpl implements Parser {

  private final Storage<Employee> employeeStorage;
  private final Storage<Project> projectStorage;

  public ParserImpl(Storage<Project> projectStorage, Storage<Employee> employeeStorage) {
    this.projectStorage = projectStorage;
    this.employeeStorage = employeeStorage;
  }

  @Override
  public void clearStorages() {
    this.employeeStorage.clear();
    this.projectStorage.clear();
  }

  @Override
  public void parseLine(String line) {
    if (line.equals("") || line.isEmpty()) {
      return;
    }
    String[] splitted = line.split(", ");
    Integer id = parseNumber(splitted[0]);
    Integer projectId = parseNumber(splitted[1]);
    LocalDate dateFrom = DateParser.parseDate(splitted[2]);
    LocalDate dateTo = DateParser.parseDate(splitted[3]);

    Employee employee = parseEmployee(id, projectId, dateFrom, dateTo);
    parseProject(projectId, employee);
  }

  private Employee parseEmployee(int id, int projectId, LocalDate dateFrom, LocalDate dateTo) {
    Employee emp = null;

    if (this.employeeStorage.checkIfEntityExists(id)) {
      emp = this.employeeStorage.getEntity(id);
    } else {
      emp = new EmployeeImpl(id);
      this.employeeStorage.add(emp);
    }

    emp.linkProject(projectId);
    emp.linkDateFromByProjectId(projectId, dateFrom);
    emp.linkDateToByProjectId(projectId, dateTo);

    return emp;
  }
  
  private void parseProject(int projectId, Employee employee) {
    Project project = null;

    if (this.projectStorage.checkIfEntityExists(projectId)) {
      project = this.projectStorage.getEntity(projectId);
    } else {
      project = new ProjectImpl(projectId);
    }
    project.linkEmployee(employee);
    this.projectStorage.add(project);
  }

  private int parseNumber(String numberToBeParsed) {
    return Integer.parseInt(numberToBeParsed);
  }
}
