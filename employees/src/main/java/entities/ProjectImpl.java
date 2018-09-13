package entities;

import java.util.HashMap;
import java.util.Map;

public class ProjectImpl implements Project {

  private int id;

  private Map<Integer, Employee> employees;

  public ProjectImpl(Integer projectId) {
    this.id = projectId;
    this.employees = new HashMap<>();
  }

  @Override
  public int getProjectId() {
    return this.id;
  }

  @Override
  public Employee getEmployee(int id) {
    return this.employees.get(id);
  }

  @Override
  public void linkEmployee(Employee employee) {
    this.employees.put(employee.getId(), employee);
  }


}
