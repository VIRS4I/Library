package library.items;
import library.items.Book;

public class NonFictionBook extends Book {
    private String subjectArea;

    public NonFictionBook(String title, String author, int year, String isbn, String subjectArea) {
        super(title, author, year, isbn);
        this.subjectArea = subjectArea;
    }

    @Override
    public String getBookDetails() {
        return "NonFictionBook{" + "title='" + title + ", author='" + author + ", year=" + year + ", isbn='" + isbn + ", subjectArea='" + subjectArea + '}';
    }
}