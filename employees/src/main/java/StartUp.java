import calculator.Calculator;
import calculator.CalculatorImpl;
import entities.Employee;
import entities.EmployeesStorageImpl;
import entities.Project;
import entities.ProjectStorageImpl;
import entities.Storage;
import interpreter.CommandInterpreter;
import interpreter.CommandInterpreterImpl;
import parser.Parser;
import parser.ParserImpl;

public class StartUp {
    public static void main(String[] args) {
        CommandInterpreter cmd = null;
        Storage<Employee> employeeStorage = EmployeesStorageImpl.getInstance();
        Storage<Project> projectStorage = ProjectStorageImpl.getInstance();
        Parser parser = new ParserImpl(projectStorage, employeeStorage);
        Calculator calculator = new CalculatorImpl(employeeStorage);
        if (args.length > 0) {
            cmd = new CommandInterpreterImpl(args, parser, calculator);
        } else {
            cmd = new CommandInterpreterImpl(parser, calculator);
        }
        cmd.start();
    }
}
