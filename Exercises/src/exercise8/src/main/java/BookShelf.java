import java.util.List;
import java.util.ArrayList;

public class BookShelf {
    private List<Book> books;

    public BookShelf() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public String toString() {
        return "BookShelf{" +
                "books=" + books +
                '}';
    }
}
