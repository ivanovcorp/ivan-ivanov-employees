package entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface Employee {

  int getId();

  LocalDate getDateFromByProjectId(int id);

  LocalDate getDateToByProjectId(int id);

  void linkProject(int id);

  Set<Integer> getProjectsIds();

  void linkDateFromByProjectId(int id, LocalDate fromDate);

  void linkDateToByProjectId(int id, LocalDate toDate);
}
