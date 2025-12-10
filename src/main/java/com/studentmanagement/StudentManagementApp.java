package com.studentmanagement;
import java.util.List;
import java.util.Scanner;
public class StudentManagementApp {
    private final StudentService service = new StudentService();
    private final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        new StudentManagementApp().run();
    }
    private void run() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addStudent();
                case "2" -> updateStudent();
                case "3" -> deleteStudent();
                case "4" -> showAllStudents();
                case "5" -> {
                    System.out.println("Exiting. Goodbye.");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
    private void printMenu() {
        System.out.println();
        System.out.println("=== Student Management ===");
        System.out.println("1 - Add Student");
        System.out.println("2 - Update Student");
        System.out.println("3 - Delete Student");
        System.out.println("4 - Show All Students");
        System.out.println("5 - Exit");
        System.out.print("Choose an option: ");
    }
    private void addStudent() {
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Age: ");
        int age = readInt();
        Student s = service.addStudent(name, age);
        System.out.println("Student added successfully: " + s);
    }
    private void updateStudent() {
        System.out.print("Student ID to update: ");
        int id = readInt();
        System.out.print("New Name: ");
        String newName = scanner.nextLine().trim();
        System.out.print("New Age: ");
        int newAge = readInt();
        boolean updated = service.updateStudent(id, newName, newAge);
        if (updated) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }
    private void deleteStudent() {
        System.out.print("Student ID to delete: ");
        int id = readInt();
        boolean deleted = service.deleteStudent(id);
        if (deleted) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }
    private void showAllStudents() {
        List<Student> students = service.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\n=== All Students ===");
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }
    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}