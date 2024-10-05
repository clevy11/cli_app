import MODEL.Progress;
import DAO.ProgressDAO;


import java.util.List;
import java.util.Scanner;

public class Main {
    private static ProgressDAO progressDAO = new ProgressDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("=== Progress Management CLI Application ===");

        while (running) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    addProgress();
                    break;
                case 2:
                    viewAllProgress();
                    break;
                case 3:
                    updateProgress();
                    break;
                case 4:
                    deleteProgress();
                    break;
                case 5:
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option (1-5).");
            }
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\nSelect an option:");
        System.out.println("1. Add Progress");
        System.out.println("2. View All Progress");
        System.out.println("3. Update Progress");
        System.out.println("4. Delete Progress");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            // Invalid input; choice remains -1
        }
        return choice;
    }

    private static void addProgress() {
        try {
            System.out.print("Enter User ID: ");
            int userId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Module ID: ");
            int moduleId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Progress Status: ");
            String progressStatus = scanner.nextLine();

            System.out.print("Enter Completion Date (YYYY-MM-DD): ");
            String completionDate = scanner.nextLine();

            System.out.print("Enter Score: ");
            int score = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter Attempts: ");
            int attempts = Integer.parseInt(scanner.nextLine());

            Progress progress = new Progress(userId, moduleId, progressStatus, completionDate, score, attempts);
            progressDAO.createProgress(progress);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numerical values for IDs, score, and attempts.");
        }
    }

    private static void viewAllProgress() {
        List<Progress> progressList = progressDAO.getAllProgress();

        if (progressList.isEmpty()) {
            System.out.println("No progress records found.");
        } else {
            System.out.println("\n--- Progress Records ---");
            for (Progress progress : progressList) {
                System.out.println(progress);
            }
        }
    }

    private static void updateProgress() {
        try {
            System.out.print("Enter Progress ID to update: ");
            int progressId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter new User ID: ");
            int userId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter new Module ID: ");
            int moduleId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter new Progress Status: ");
            String progressStatus = scanner.nextLine();

            System.out.print("Enter new Completion Date (YYYY-MM-DD): ");
            String completionDate = scanner.nextLine();

            System.out.print("Enter new Score: ");
            int score = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter new Attempts: ");
            int attempts = Integer.parseInt(scanner.nextLine());

            Progress progress = new Progress(progressId, userId, moduleId, progressStatus, completionDate, score, attempts);
            progressDAO.updateProgress(progress);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numerical values for IDs, score, and attempts.");
        }
    }

    private static void deleteProgress() {
        try {
            System.out.print("Enter Progress ID to delete: ");
            int progressId = Integer.parseInt(scanner.nextLine());

            progressDAO.deleteProgress(progressId);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a numerical value for Progress ID.");
        }
    }
}