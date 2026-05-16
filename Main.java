import java.util.Scanner;

/**
 * Main.java
 * Entry point for the Library Book Management System.
 * Presents an interactive menu and delegates actions to the Library class.
 */
public class Main {

    public static void main(String[] args) {

        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        System.out.println("──────────────────────────────────────");
        System.out.println("           Welcome to the             ");
        System.out.println("   Library Book Management System     ");
        System.out.println("──────────────────────────────────────");

        // ── Main menu loop ──
        while (true) {

            // Print menu
            System.out.println("\nPlease choose an option:");
            System.out.println("  1. Add Book");
            System.out.println("  2. Remove Book");
            System.out.println("  3. Search Book");
            System.out.println("  4. Display All Books");
            System.out.println("  5. Check Out Book");
            System.out.println("  6. Return Book");
            System.out.println("  7. Load Books From File");
            System.out.println("  8. Sort Books by Title");
            System.out.println("  9. Exit");
            System.out.print("Enter your choice: ");

            String input  = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please enter a number 1-9.");
                continue;
            }

            if (choice < 1 || choice > 9) {
                System.out.println("Invalid option. Please enter a number 1-9.");
                continue;
            }

            switch (choice) {

                case 1: // ── Add Book ──
                
                    String title;
                    while (true) {
                        System.out.print("Enter title: ");
                        title = scanner.nextLine().trim();
                
                        if (!title.isEmpty()) {
                            break;
                        }
                
                        System.out.println("Title cannot be blank.");
                    }
                
                    String author;
                    while (true) {
                        System.out.print("Enter author: ");
                        author = scanner.nextLine().trim();
                
                        if (!author.isEmpty()) {
                            break;
                        }
                
                        System.out.println("Author cannot be blank.");
                    }
                
                    String isbn;
                    while (true) {
                        System.out.print("Enter ISBN: ");
                        isbn = scanner.nextLine().trim();
                
                        if (!isbn.isEmpty()) {
                            break;
                        }
                
                        System.out.println("ISBN cannot be blank.");
                    }
                
                    int year;
                    while (true) {
                        System.out.print("Enter publication year: ");
                        String yearInput = scanner.nextLine().trim();
                
                        try {
                            year = Integer.parseInt(yearInput);
                
                            if (year >= 1000 && year <= 9999) {
                                break;
                            }
                
                            System.out.println("Year must be a 4-digit number (example: 2026).");
                
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid year. Please enter a number.");
                        }
                    }
                
                    Book newBook = new Book(title, author, isbn, year);
                    library.addBook(newBook);
                    break;
                    
                case 2: // ── Remove Book ──
                    System.out.print("Enter ISBN of book to remove: ");
                    String removeISBN = scanner.nextLine().trim();
                    library.removeBook(removeISBN);
                    break;

                case 3: // ── Search Book ──
                    System.out.print("Enter title, author, or ISBN to search: ");
                    String keyword = scanner.nextLine().trim();
                    library.searchBook(keyword);
                    break;

                case 4: // ── Display All Books ──
                    library.displayBooks();
                    break;

                case 5: // ── Check Out Book ──
                    System.out.print("Enter ISBN of book to check out: ");
                    String checkOutISBN = scanner.nextLine().trim();
                    library.checkOutBook(checkOutISBN);
                    break;

                case 6: // ── Return Book ──
                    System.out.print("Enter ISBN of book to return: ");
                    String returnISBN = scanner.nextLine().trim();
                    library.returnBook(returnISBN);
                    break;

                case 7: // ── Load Books From File ──
                    System.out.print("Enter filename (e.g., books.txt): ");
                    String filename = scanner.nextLine().trim();
                    library.loadBooksFromFile(filename);
                    break;

                case 8: // ── Sort Books by Title ──
                    library.sortBooksByTitle();
                    break;

                case 9: // ── Exit ──
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
            }
        }
    }
}
