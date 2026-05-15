/**
 * Book.java
 * Represents a single book in the library system.
 */
public class Book {

    // ── Attributes ──
    private String  title;
    private String  author;
    private String  ISBN;
    private int     publicationYear;
    private boolean isAvailable;   // true = available, false = checked out

    // ── Constructor ──
    /**
     * Creates a new Book.  All books start as "available" by default.
     *
     * @param title           the book's title
     * @param author          the book's author
     * @param ISBN            the book's ISBN identifier
     * @param publicationYear the year the book was published
     */
    public Book(String title, String author, String ISBN, int publicationYear) {
        this.title           = title;
        this.author          = author;
        this.ISBN            = ISBN;
        this.publicationYear = publicationYear;
        this.isAvailable     = true;   // available when first added
    }

    // ── Getters ──
    /** @return the book's title */
    public String getTitle() { return title; }

    /** @return the book's author */
    public String getAuthor() { return author; }

    /** @return the book's ISBN */
    public String getISBN() { return ISBN; }

    /** @return the book's publication year */
    public int getPublicationYear() { return publicationYear; }

    /** @return true if the book is available, false if checked out */
    public boolean isAvailable() { return isAvailable; }

    // ── Setter ──
    /**
     * Updates the availability status of the book.
     *
     * @param available true to mark available; false to mark checked out
     */
    public void setAvailable(boolean available) { this.isAvailable = available; }

    // ── toString ──
    /**
     * Returns a formatted, human-readable description of the book.
     */
    @Override
    public String toString() {
        return String.format(
            "Title: %-30s | Author: %-20s | ISBN: %-15s | Year: %d | Status: %s",
            title, author, ISBN, publicationYear,
            isAvailable ? "Available" : "Checked Out"
        );
    }
}
