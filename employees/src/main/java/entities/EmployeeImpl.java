package entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EmployeeImpl implements Employee {

  private int id;

  private Set<Integer> projects;

  private Map<Integer, LocalDate> fromDates;

  private Map<Integer, LocalDate> toDates;

  public EmployeeImpl(int id) {
    this.id = id;
    this.projects = new HashSet<>();
    this.fromDates = new HashMap<>();
    this.toDates = new HashMap<>();
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public LocalDate getDateFromByProjectId(int id) {
    return this.fromDates.get(id);
  }

  @Override
  public LocalDate getDateToByProjectId(int id) {
    return this.toDates.get(id);
  }

  @Override
  public void linkProject(int id) {
    if (!this.projects.contains(id)) {
      this.projects.add(id);
    }
  }

  @Override
  public Set<Integer> getProjectsIds() {
    return this.projects;
  }

  @Override
  public void linkDateFromByProjectId(int id, LocalDate fromDate) {
    this.fromDates.put(id, fromDate);
  }

  @Override
  public void linkDateToByProjectId(int id, LocalDate toDate) {
    this.toDates.put(id, toDate);
  }
}

