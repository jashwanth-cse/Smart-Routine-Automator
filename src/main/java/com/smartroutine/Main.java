package com.smartroutine;
import com.smartroutine.utils.ProgramFetcher;
import com.smartroutine.model.*;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Routine> routines = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== Smart Routine Automator ====");
            System.out.println("1. Add Routine");
            System.out.println("2. Execute All Routines");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addRoutine();
                case 2 -> executeRoutines();
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void addRoutine() {
        System.out.print("Enter routine name: ");
        String name = scanner.nextLine();

        // Trigger
        System.out.println("Select Trigger:");
        System.out.println("1. Run Immediately");
        System.out.println("2. Run at Specific Time (HH:MM)");
        int trigChoice = scanner.nextInt();
        scanner.nextLine();

        Trigger trigger;
        if (trigChoice == 1) {
            trigger = new Trigger("IMMEDIATE");
        } else {
            System.out.print("Enter time (HH:MM): ");
            String time = scanner.nextLine();
            trigger = new Trigger(time);
        }

        Routine routine = new Routine(name, trigger);

        // Add Actions
        boolean adding = true;
        while (adding) {
            System.out.println("Select Action Type:");
            System.out.println("1. Open Program");
            System.out.println("2. Open Website");
            System.out.println("3. Reminder");
            System.out.println("4. System Command");
            System.out.println("5. Done adding actions");
            int actionChoice = scanner.nextInt();
            scanner.nextLine();

            switch (actionChoice) {
                case 1 -> {
                        List<String> installed = ProgramFetcher.getInstalledPrograms();
                        if (installed.isEmpty()) {
                            System.out.println("No programs found.");
                            break;
                        }

                        for (int i = 0; i < installed.size(); i++) {
                            System.out.println((i + 1) + ". " + installed.get(i));
                        }
                        System.out.print("Choose a program: ");
                        int progChoice = scanner.nextInt();
                        scanner.nextLine();

                        String programName = installed.get(progChoice - 1);
                        routine.addAction(new AppAction(programName, programName));
                    }
                case 2 -> {
                    System.out.print("Enter website URL: ");
                    String url = scanner.nextLine();
                    routine.addAction(new WebAction("Open Website", url));
                }
                case 3 -> {
                    System.out.print("Enter reminder text: ");
                    String text = scanner.nextLine();
                    routine.addAction(new ReminderAction("Reminder", text));
                }
                case 4 -> {
                    System.out.print("Enter system command: ");
                    String cmd = scanner.nextLine();
                    routine.addAction(new SystemAction("System Command", cmd));
                }
                case 5 -> adding = false;
                default -> System.out.println("Invalid choice!");
            }
        }

        routines.add(routine);
        System.out.println("Routine added successfully!");
    }

    private static void executeRoutines() {
        for (Routine routine : routines) {
            if (routine.getTrigger().isTriggered()) {
                routine.execute();
            } else {
                System.out.println("Skipping routine: " + routine.getName() +
                        " (Scheduled for " + routine.getTrigger().getTime() + ")");
            }
        }
    }
}
