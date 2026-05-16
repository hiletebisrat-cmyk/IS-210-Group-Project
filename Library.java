import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Library.java
 * Manages a collection of Book objects.  Provides methods to add, remove,
 * search, display, check out, return, and load books from a file.
 */
public class Library {

    // ── Attributes ──
    private ArrayList<Book> books;

    // ── Constructor ──
    /**
     * Creates a new, empty Library.
     */
    public Library() {
        books = new ArrayList<>();
    }

    // ── Helper Method ──
    private Book findByISBN(String isbn) {
        for (Book book : books) {
            if (book.getISBN().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }

    // ── Add a Book ──
    /**
     * Adds a Book object to the library.
     *
     * @param book the Book to add
     */
    public void addBook(Book book) {
        if (findByISBN(book.getISBN()) != null) {
            System.out.println("Book with ISBN " + book.getISBN() + " already exists.");
            return;
        }
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    // ── Remove a Book ──
    /**
     * Removes a book from the library by ISBN.
     *
     * @param isbn the ISBN of the book to remove
     */
    public void removeBook(String isbn) {
        Book book = findByISBN(isbn);

        if (book == null) {
            System.out.println("Book with ISBN " + isbn + " not found.");
            return;
        }

        books.remove(book);
        System.out.println("Book removed: " + book.getTitle());
    }

    // ── Search for a Book ──
    /**
     * Searches for books whose title, author, OR ISBN contains the keyword
     * (case-insensitive).
     *
     * @param keyword the search term
     */

    public void searchBook(String keyword) {
        boolean found = false;
        String lower = keyword.toLowerCase();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(lower)
                    || book.getAuthor().toLowerCase().contains(lower)
                    || book.getISBN().toLowerCase().contains(lower)) {
                if (!found) {
                    System.out.println("\n───── Search Results ──────────────────────────────");
                }

                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found for the keyword: " + keyword);
        }
    }

    // ── Display All Books ──
    /**
     * Prints every book currently in the library.
     */
    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        String fmt = "| %-20.20s | %-30.30s | %-13.13s | %-11.11s |%n";
        String divider = "───────────────────────────────────────────────────────────────────────────────────────";

        System.out.printf("%n───── Library Catalog (%d book%s) ───────────────────────────────────────────────────────%n",
                books.size(),
                books.size() == 1 ? "" : "s"
        );
        System.out.println(divider);
        System.out.printf(fmt, "Author", "Title", "ISBN", "Status");
        System.out.println(divider);
        for (Book book : books) {
            System.out.printf(fmt, book.getAuthor(), book.getTitle(), book.getISBN(), book.getStatus());
        }
        System.out.println(divider);
    }
        // ── Check Out a Book ──
        /**
         * Marks a book as checked out (unavailable) by ISBN.
         *
         * @param isbn the ISBN of the book to check out
         */
        public void checkOutBook(String isbn) {
            Book book = findByISBN(isbn);

            if (book == null) {
                System.out.println("Book with ISBN " + isbn + " not found.");
                return;
            }

            if (book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Book checked out: " + book.getTitle());
            } else {
                System.out.println("This book is currently checked out: " + book.getTitle());
            }
        }

        // ── Return a Book ──
        /**
         * Marks a book as available (returned) by ISBN.
         *
         * @param isbn the ISBN of the book to return
         */
        public void returnBook(String isbn) {
            Book book = findByISBN(isbn);

            if (book == null) {
                System.out.println("Book with ISBN " + isbn + " not found.");
                return;
            }


            if (!book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("Book returned: " + book.getTitle());
            } else {
                System.out.println("Invalid return: Book was not previously borrowed.");
            }
        }
        public void sortBooksByTitle() {
            books.sort((a, b) -> a.getTitle().compareToIgnoreCase(b.getTitle()));
            System.out.println("Books sorted alphabetically by title.");
        }
        // ── Load Books from File ──
        /**
         * Reads books from a comma-separated text file and adds them to the library.
         * Expected format per line: title,author,ISBN,publicationYear
         * All loaded books default to "available".
         *
         * @param filename path to the text file
         */
        public void loadBooksFromFile(String filename) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

                String line;
                int count = 0;
                int duplicates = 0;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();

                    if (line.isEmpty()) continue;   // skip blank lines

                    String[] parts = line.split(",");

                    if (parts.length == 4) {
                        String title = parts[0].trim();
                        String author = parts[1].trim();
                        String isbn = parts[2].trim();
                        int year;

                        try {
                            year = Integer.parseInt(parts[3].trim());
                        } catch (NumberFormatException e) {
                            System.out.println(
                                    "Invalid year in record — skipping: " + line
                            );
                            continue;
                        }

                        if (findByISBN(isbn) != null) {
                            duplicates++;
                            continue;
                        }

                        books.add(new Book(title, author, isbn, year));
                        count++;

                    } else {
                        System.out.println(
                                "Invalid record — skipping: " + line
                        );
                    }
                }

                if (duplicates > 0) {
                    System.out.printf(
                            "Books loaded from file: %s (%d added, %d duplicate%s skipped)%n",
                            filename,
                            count,
                            duplicates,
                            duplicates == 1 ? "" : "s"
                    );
                } else {
                    System.out.printf(
                            "Books loaded from file: %s (%d book%s added)%n",
                            filename,
                            count,
                            count == 1 ? "" : "s"
                    );
                }

            } catch (IOException e) {
                System.out.println(
                        "Error reading file \"" + filename + "\": " + e.getMessage()
                );
            }
        }
    }
