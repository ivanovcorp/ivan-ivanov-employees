package entities;

import java.util.HashMap;
import java.util.Map;

public final class ProjectStorageImpl implements Storage<Project> {

  private static ProjectStorageImpl instance;
  private Map<Integer, Project> projectStorage;

  private ProjectStorageImpl() {
    this.projectStorage = new HashMap<>();
  }

  public static ProjectStorageImpl getInstance() {
    if (instance == null) {
      instance = new ProjectStorageImpl();
    }
    return instance;
  }

  @Override
  public void add(Project entity) {
    if(!checkIfEntityExists(entity.getProjectId())) {
      this.projectStorage.put(entity.getProjectId(), entity);
    }
  }

  @Override
  public boolean checkIfEntityExists(int id) {
    return this.projectStorage.containsKey(id);
  }

  @Override
  public Project getEntity(int id) {
    if (!checkIfEntityExists(id)) {
      // exception
    }
    return this.projectStorage.get(id);
  }

  @Override
  public Map<Integer, Project> getAllEntities() {
    return this.projectStorage;
  }

  @Override
  public void clear() {
    this.projectStorage = new HashMap<>();
  }
}
