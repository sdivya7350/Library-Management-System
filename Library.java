package lms_project;

import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books;
    private final String FILE_NAME = "library.dat";

    public Library() {
        books = loadBooks();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void searchByTitle(String title) {
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No book found with title: " + title);
    }

    public void searchByAuthor(String author) {
        boolean found = false;
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) System.out.println("No book found by author: " + author);
    }

    public void issueBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                if (b.isIssued()) {
                    System.out.println("Book is already issued.");
                } else {
                    b.setIssued(true);
                    System.out.println("Book issued successfully.");
                    saveBooks();
                }
                return;
            }
        }
        System.out.println("Book not found with ISBN: " + isbn);
    }

    public void returnBook(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) {
                if (b.isIssued()) {
                    b.setIssued(false);
                    System.out.println("Book returned successfully.");
                    saveBooks();
                } else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book not found with ISBN: " + isbn);
    }

    public void saveBooks() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(books);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public List<Book> loadBooks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Book>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
