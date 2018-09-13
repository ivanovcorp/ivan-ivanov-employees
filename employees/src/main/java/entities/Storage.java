package entities;

import java.util.Map;

public interface Storage<T> {

  void add(T entity);

  boolean checkIfEntityExists(int id);

  T getEntity(int id);

  Map<Integer, T> getAllEntities();

  void clear();
}
