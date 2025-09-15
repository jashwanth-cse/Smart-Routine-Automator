package com.smartroutine;
import com.smartroutine.model.*;
import com.smartroutine.utils.ProgramFetcher;
import com.smartroutine.utils.ProgramInfo;
import java.util.*;
import java.util.concurrent.*;

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
    List<ProgramInfo> installed = ProgramFetcher.getInstalledPrograms();
    if (installed.isEmpty()) {
        System.out.println("No programs found.");
        break;
    }

    // Show only display names
    for (int i = 0; i < installed.size(); i++) {
        System.out.println((i + 1) + ". " + installed.get(i).getName());
    }
    System.out.print("Choose a program: ");
    int progChoice = scanner.nextInt();
    scanner.nextLine();

    if (progChoice < 1 || progChoice > installed.size()) {
        System.out.println("Invalid choice!");
        break;
    }

    ProgramInfo selectedProgram = installed.get(progChoice - 1);

    // Add AppAction to the current routine
    routine.addAction(new AppAction(selectedProgram.getName(), selectedProgram.getFullPath()));
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
            System.out.print("Enter admin password to continue: ");
            String pwd = scanner.nextLine();
            if (!pwd.equals("1234")) {  // hardcoded password for now
                System.out.println("Incorrect password! Access denied.");
                break;
            }

            System.out.println("Select System Action:");
            System.out.println("1. Shutdown");
            System.out.println("2. Restart");
            System.out.println("3. Logoff");
            System.out.println("4. Sleep");
            System.out.println("5. Lock");
            int sysChoice = scanner.nextInt();
            scanner.nextLine();

            String sysCommand = switch (sysChoice) {
                case 1 -> "shutdown";
                case 2 -> "restart";
                case 3 -> "logoff";
                case 4 -> "sleep";
                case 5 -> "lock";
                default -> "";
            };

            // Add confirmation for critical actions
            if (sysCommand.equals("shutdown") || sysCommand.equals("restart") || sysCommand.equals("logoff")) {
                System.out.print("⚠️ Are you sure you want to " + sysCommand + "? (Y/N): ");
                String confirm = scanner.nextLine().trim().toLowerCase();
                if (!confirm.equals("y")) {
                    System.out.println("System action cancelled.");
                    break;
                }
            }

            if (!sysCommand.isEmpty()) {
                routine.addAction(new SystemAction("System Command", sysCommand));
            } else {
                System.out.println("Invalid option!");
            }
        }

                case 5 -> adding = false;
                default -> System.out.println("Invalid choice!");
            }
        }

        routines.add(routine);
        System.out.println("Routine added successfully!");
    }


        private static void executeRoutines() {
            System.out.println("Starting scheduler... (press Ctrl+C to stop)");
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

            scheduler.scheduleAtFixedRate(() -> {
                for (Routine routine : routines) {
                    if (routine.getTrigger().isTriggered()) {
                        System.out.println("Executing routine: " + routine.getName());
                        routine.execute();
                    } else {
                        System.out.println("Waiting for: " + routine.getName() +
                                " (Scheduled for " + routine.getTrigger().getTime() + ")");
                    }
                }
            }, 0, 1, TimeUnit.MINUTES); // checks every 1 minute

        }
}
