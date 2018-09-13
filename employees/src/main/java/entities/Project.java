package entities;

public interface Project {

  int getProjectId();

  Employee getEmployee(int id);

  void linkEmployee(Employee employee);
}
