import java.io.*;
import java.nio.file.*;
import java.util.*;

class FileListMaker {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String> list = new ArrayList<>();
    static boolean needsToBeSaved = false;
    static String currentFilename = null;

    public static void main(String[] args) {
        boolean quit = false;

        while (!quit) {
            showMenu();
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "A": addItem(); break;
                case "D": deleteItem(); break;
                case "I": insertItem(); break;
                case "M": moveItem(); break;
                case "V": viewList(); break;
                case "C": clearList(); break;
                case "O": openList(); break;
                case "S": saveList(); break;
                case "Q": quit = quitProgram(); break;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("A - Add item");
        System.out.println("D - Delete item");
        System.out.println("I - Insert item");
        System.out.println("M - Move item");
        System.out.println("V - View list");
        System.out.println("C - Clear list");
        System.out.println("O - Open list from disk");
        System.out.println("S - Save list to disk");
        System.out.println("Q - Quit");
        System.out.print("Enter your choice: ");
    }

    static void addItem() {
        System.out.print("Enter item to add: ");
        list.add(scanner.nextLine());
        needsToBeSaved = true;
    }

    static void deleteItem() {
        viewList();
        System.out.print("Enter index to delete: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= 0 && index < list.size()) {
            list.remove(index);
            needsToBeSaved = true;
        } else {
            System.out.println("Invalid index.");
        }
    }

    static void insertItem() {
        System.out.print("Enter item to insert: ");
        String item = scanner.nextLine();
        System.out.print("Enter index to insert at: ");
        int index = Integer.parseInt(scanner.nextLine());
        if (index >= 0 && index <= list.size()) {
            list.add(index, item);
            needsToBeSaved = true;
        } else {
            System.out.println("Invalid index.");
        }
    }

    static void moveItem() {
        viewList();
        System.out.print("Enter index of item to move: ");
        int from = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new index: ");
        int to = Integer.parseInt(scanner.nextLine());
        if (from >= 0 && from < list.size() && to >= 0 && to <= list.size()) {
            String item = list.remove(from);
            list.add(to, item);
            needsToBeSaved = true;
        } else {
            System.out.println("Invalid index.");
        }
    }

    static void viewList() {
        System.out.println("\nCurrent List:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ": " + list.get(i));
        }
    }

    static void clearList() {
        list.clear();
        needsToBeSaved = true;
    }

    static void openList() {
        if (needsToBeSaved) {
            System.out.print("Unsaved changes. Save current list? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                saveList();
            }
        }
        System.out.print("Enter filename to open: ");
        String filename = scanner.nextLine();
        try {
            list = new ArrayList<>(Files.readAllLines(Paths.get(filename + ".txt")));
            currentFilename = filename + ".txt";
            needsToBeSaved = false;
            System.out.println("File loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    static void saveList() {
        if (currentFilename == null) {
            System.out.print("Enter filename to save as: ");
            currentFilename = scanner.nextLine() + ".txt";
        }
        try {
            Files.write(Paths.get(currentFilename), list);
            needsToBeSaved = false;
            System.out.println("List saved.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    static boolean quitProgram() {
        if (needsToBeSaved) {
            System.out.print("You have unsaved changes. Save before quitting? (y/n): ");
            if (scanner.nextLine().equalsIgnoreCase("y")) {
                saveList();
            }
        }
        System.out.println("Goodbye!");
        return true;
    }
}