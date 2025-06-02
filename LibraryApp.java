package lms_project;

import java.util.Scanner;

public class LibraryApp {
    public static void main(String[] args) {
        Library lib = new Library();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Search Book by Author");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();
                    lib.addBook(new Book(title, author, isbn));
                    lib.saveBooks();
                }
                case 2 -> lib.viewBooks();
                case 3 -> {
                    System.out.print("Enter Title to search: ");
                    String title = sc.nextLine();
                    lib.searchByTitle(title);
                }
                case 4 -> {
                    System.out.print("Enter Author to search: ");
                    String author = sc.nextLine();
                    lib.searchByAuthor(author);
                }
                case 5 -> {
                    System.out.print("Enter ISBN to issue: ");
                    String isbn = sc.nextLine();
                    lib.issueBook(isbn);
                }
                case 6 -> {
                    System.out.print("Enter ISBN to return: ");
                    String isbn = sc.nextLine();
                    lib.returnBook(isbn);
                }
                case 7 -> {
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
