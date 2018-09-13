package calculator;

import static java.time.temporal.ChronoUnit.DAYS;

import entities.Employee;
import entities.Storage;
import java.time.LocalDate;
import java.util.Set;

public class CalculatorImpl implements Calculator {

  private Storage<Employee> employeeStorage;

  private int[] ids;
  private int mostDays;

  public CalculatorImpl(Storage<Employee> employeeStorage) {
    this.employeeStorage = employeeStorage;
    this.ids = new int[2];
    this.mostDays = Integer.MIN_VALUE;
  }

  @Override
  public void calculate() {
    this.employeeStorage.getAllEntities().forEach((eid, emp) -> {
      calculateForSpecificEmployee(emp);
    });
  }

  @Override
  public String printResult() {
    StringBuilder sb = new StringBuilder();
    sb.append("Employee #1 id: " + this.ids[0]).append(System.lineSeparator());
    sb.append("Employee #2 id: " + this.ids[1] + " ").append(System.lineSeparator());
    sb.append("Days worked together: " + this.mostDays).append(System.lineSeparator());
    return sb.toString();
  }

  @Override
  public void clearCalculations() {
    this.ids = new int[2];
    this.mostDays = 0;
  }

  private void calculateForSpecificEmployee(Employee emp) {
    this.employeeStorage.getAllEntities().forEach((eid2, emp2) -> {
      if(emp.equals(emp2)) {
        return;
      }
      calculateDaysWorkedTogeather(emp, emp2);
    });
  }

  private void calculateDaysWorkedTogeather(Employee emp1, Employee emp2) {
    int daysWorkedTogeather = 0;
    Set<Integer> projectIds = emp1.getProjectsIds();

    for (Integer pid : projectIds) {
      LocalDate startDateEmp1 = emp1.getDateFromByProjectId(pid);
      LocalDate startDateEmp2 = emp2.getDateFromByProjectId(pid);

      LocalDate endDateEmp1 = emp1.getDateToByProjectId(pid);
      LocalDate endDateEmp2 = emp2.getDateToByProjectId(pid);

      if (startDateEmp1 == null || startDateEmp2 == null ||
          endDateEmp1== null || endDateEmp2 == null || endDateEmp1.isBefore(startDateEmp2) || endDateEmp2.isBefore(startDateEmp1) ||
          startDateEmp1.isAfter(endDateEmp2) || startDateEmp2.isAfter(endDateEmp1)) {
        continue;
      }

      LocalDate startDate = startDateEmp1.compareTo(startDateEmp2) > 0 ? startDateEmp1 : startDateEmp2;
      LocalDate endDate = endDateEmp1.compareTo(endDateEmp2) < 0 ? endDateEmp1 : endDateEmp2;
      daysWorkedTogeather += DAYS.between(startDate, endDate);
    }

    if (daysWorkedTogeather > this.mostDays) {
      this.mostDays = daysWorkedTogeather;
      this.ids[0] = emp1.getId();
      this.ids[1] = emp2.getId();
    }
  }
}