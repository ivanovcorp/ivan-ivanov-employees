package entities;

import java.util.HashMap;
import java.util.Map;

public final class EmployeesStorageImpl implements Storage<Employee> {
  private static EmployeesStorageImpl instance;

  private Map<Integer, Employee> employeeStorage;

  private EmployeesStorageImpl() {
    this.employeeStorage = new HashMap<>();
  }

  public static EmployeesStorageImpl getInstance() {
    if (instance == null) {
      instance = new EmployeesStorageImpl();
    }
    return instance;
  }

  @Override
  public void add(Employee entity) {
    if(!checkIfEntityExists(entity.getId())) {
      this.employeeStorage.put(entity.getId(), entity);
    }
  }

  @Override
  public boolean checkIfEntityExists(int id) {
    return this.employeeStorage.containsKey(id);
  }

  @Override
  public Employee getEntity(int id) {
    if (!checkIfEntityExists(id)) {
      // exception
    }
    return this.employeeStorage.get(id);
  }

  @Override
  public Map<Integer, Employee> getAllEntities() {
    return this.employeeStorage;
  }

  @Override
  public void clear() {
    this.employeeStorage = new HashMap<>();
  }
}
