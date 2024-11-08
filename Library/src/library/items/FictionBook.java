package library.items;

public class FictionBook extends Book {
    private String genre;

    public FictionBook(String title, String author, int year, String isbn, String genre) {
        super(title, author, year, isbn);
        this.genre = genre;
    }

    @Override
    public String getBookDetails() {
        return "FictionBook{" + "title='" + title + ", author='" + author + ", year=" + year + ", isbn='" + isbn + ", genre='" + genre + '}';
    }
}


