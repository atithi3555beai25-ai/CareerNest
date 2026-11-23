import java.util.Scanner;

public class InputHandler {
    private static Scanner scanner = new Scanner(System.in);

    public static String line(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static int number(String message) {
        while (true) {
            try {
                return Integer.parseInt(line(message));
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid whole number");
            }
        }
    }

    public static double decimal(String message) {
        while (true) {
            try {
                return Double.parseDouble(line(message));
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid number");
            }
        }
    }
}
