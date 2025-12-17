import java.util.Scanner;

public class Main
{
    // ANSI escape codes (most modern terminals support these)
    private static final String RESET = "\u001B[0m";
    private static final String BOLD = "\u001B[1m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String MAGENTA = "\u001B[35m";

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println(BOLD + CYAN + "=== Predator-Prey Simulator ===" + RESET);

        int foxes = readInt(scanner, "Enter initial number of foxes", 1, 1);
        int rabbits = readInt(scanner, "Enter initial number of rabbits", 1, 1);
        int steps = readInt(scanner, "Enter number of simulation steps", 1, 10);
        boolean sharedRandom = readBoolean(scanner, "Use shared random (true/false)", true);

        Animal.VERBOSE = false;

        Randomizer.useShared = sharedRandom;
        Randomizer.reset();

        Simulator simulator = new Simulator(foxes, rabbits);

        // Print table header
        System.out.println();
        System.out.println(BOLD + MAGENTA + String.format("%-6s | %-6s | %-7s | %-6s | %-6s", "Step", "Foxes", "Rabbits", "Births", "Deaths") + RESET);
        System.out.println("-----------------------------------------------------");

        boolean stepByStep = readBoolean(scanner, "Pause after each step? (y/N)", false);

        for(int step = 1; step <= steps; step++)
        {
            simulator.simulateOneStep();
            int[] stats = simulator.getCurrentStats(); // {foxes, rabbits, births, deaths}

            // Color values for readability
            String colored = BOLD + YELLOW + String.format("%-6d", step) + RESET + " | "
                    + GREEN + String.format("%-6d", stats[0]) + RESET + " | "
                    + CYAN + String.format("%-7d", stats[1]) + RESET + " | "
                    + MAGENTA + String.format("%-6d", stats[2]) + RESET + " | "
                    + RED + String.format("%-6d", stats[3]) + RESET;

            System.out.println(colored);

            if(stepByStep) {
                System.out.print("Press Enter to continue...");
                scanner.nextLine();
            }
        }

        System.out.println();
        System.out.println(BOLD + CYAN + "Simulation completed." + RESET);

        scanner.close();
    }

    private static int readInt(Scanner scanner, String prompt, int min, int defaultVal) {
        while(true) {
            System.out.print(prompt + " [default: " + defaultVal + "]: ");
            String line = scanner.nextLine().trim();
            if(line.isEmpty()) return defaultVal;
            try {
                int v = Integer.parseInt(line);
                if(v < min) {
                    System.out.println("Please enter a number >= " + min + ".");
                    continue;
                }
                return v;
            } catch(NumberFormatException ex) {
                System.out.println("Invalid number, try again.");
            }
        }
    }

    private static boolean readBoolean(Scanner scanner, String prompt, boolean defaultVal) {
        while(true) {
            System.out.print(prompt + " [default: " + (defaultVal ? "true" : "false") + "]: ");
            String line = scanner.nextLine().trim().toLowerCase();
            if(line.isEmpty()) return defaultVal;
            if(line.equals("true") || line.equals("t") || line.equals("y") || line.equals("yes")) return true;
            if(line.equals("false") || line.equals("f") || line.equals("n") || line.equals("no")) return false;
            System.out.println("Please answer true/false (or y/n).");
        }
    }
}