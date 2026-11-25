import java.util.Scanner;

/**
 * Central console input helper reusing a single Scanner bound to System.in.
 */
public class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InputValidator validator = new InputValidator();

    public static String line(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static int number(String message) {
        while (true) {
            try {
                return Integer.parseInt(line(message));
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    public static double decimal(String message) {
        while (true) {
            try {
                return Double.parseDouble(line(message));
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number.");
            }
        }
    }

    public static String nonEmptyLine(String message) {
        while (true) {
            String value = line(message);
            if (validator.nonEmpty(value)) {
                return value;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public static int positiveNumber(String message) {
        while (true) {
            int value = number(message);
            if (validator.positive(value)) {
                return value;
            }
            System.out.println("Enter a positive whole number greater than 0.");
        }
    }

    public static int nonNegativeNumber(String message) {
        while (true) {
            int value = number(message);
            if (validator.validBacklogs(value)) {
                return value;
            }
            System.out.println("Enter 0 or a positive whole number.");
        }
    }

    public static double cgpa(String message) {
        while (true) {
            double value = decimal(message);
            if (validator.cgpa(value)) {
                return value;
            }
            System.out.println("CGPA must be between 0.0 and 10.0.");
        }
    }

    public static String validName(String message) {
        while (true) {
            String value = line(message);
            if (validator.validName(value)) {
                return value;
            }
            System.out.println("Enter a valid name using letters, spaces, '.', '-' or '''.");
        }
    }

    public static String validBranch(String message) {
        while (true) {
            String value = line(message);
            if (validator.validBranch(value)) {
                return value;
            }
            System.out.println("Enter a valid non-empty branch.");
        }
    }

    public static boolean confirm(String message) {
        while (true) {
            String value = line(message + " (y/n): ").toLowerCase();
            if ("y".equals(value) || "yes".equals(value)) {
                return true;
            }
            if ("n".equals(value) || "no".equals(value)) {
                return false;
            }
            System.out.println("Please enter 'y' or 'n'.");
        }
    }
}
