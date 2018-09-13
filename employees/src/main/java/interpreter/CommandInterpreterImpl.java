package interpreter;

import calculator.Calculator;
import io.DataReader;
import java.util.Scanner;

import parser.Parser;

public class CommandInterpreterImpl implements CommandInterpreter {
    private static final String UNSUPPORTED_ARGUMENTS_EXCEPTION_MESSAGE = "We currently support only 1 file per process iteration.";

    private boolean isInitialized;
    private String providedPath;
    private Scanner scanner;
    private boolean running;
    private Parser parser;
    private Calculator calculator;

    public CommandInterpreterImpl(String[] args, Parser parser, Calculator calculator) {
        this.initScanner();
        if (args.length > 1) {
            throw new UnsupportedOperationException(UNSUPPORTED_ARGUMENTS_EXCEPTION_MESSAGE);
        }
        this.providedPath = args[0];
        this.isInitialized = true;
        this.setModules(parser, calculator);
    }

    public CommandInterpreterImpl(Parser parser, Calculator calculator) {
        this.initScanner();
        this.isInitialized = false;
        this.setModules(parser, calculator);
    }

    @Override
    public void start() {
        this.running = true;
        while (this.running) {
            if (this.providedPath == null) {
                System.out.println("Currently there is no file provided to process.");
                System.out.println("Please select a command: 1 - Add path to new file; 2 - Exit");
                int cmd = this.scanner.nextInt();
                if (cmd != 1 && cmd != 2) {
                    System.out.println("Wrong command. Please try again.");
                } else {
                    if (cmd == 2) {
                        processCommand(cmd);
                    } else {
                        System.out.print("Please provide path to your file: ");
                        this.providedPath = this.scanner.next();
                        if (this.providedPath != null && !this.providedPath.isEmpty()) {
                            this.isInitialized = true;
                        } else if (this.providedPath == null){
                            System.out.println("Provided path is null or empty. Pleases try again.");
                        }
                        this.isInitialized = false;
                    }
                }
            } else {
                System.out.println("Please select a command: 1 - Calculate; 2 - Exit");
                int commandCode = this.scanner.nextInt();
                this.processCommand(commandCode);
            }
        }
    }

    private void processCommand(int commandCode) {
        switch (commandCode) {
            case 1:
                DataReader.readFile(this.providedPath).forEach(line -> {
                    this.parser.parseLine(line);
                });
                this.calculator.calculate();
                System.out.println("Result: \r\n" + this.calculator.printResult());
                this.providedPath = null;
                this.parser.clearStorages();
                this.calculator.clearCalculations();
                break;
            case 2:
                this.running = false;
                break;
        }
    }

    private void initScanner() {
        this.scanner = new Scanner(System.in);
    }

    private void setModules(Parser parser, Calculator calculator) {
      this.parser = parser;
      this.calculator = calculator;
    }
}
