import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.Arrays;

public class JSONParser {
    private String filePath;
    private Gson gson;

    public JSONParser(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        createSampleJSON();
    }

    private void createSampleJSON() {
        BookShelf bookShelf = new BookShelf();

        bookShelf.addBook(new Book("The Great Gatsby", 1925, 180, Arrays.asList("F. Scott Fitzgerald")));
        bookShelf.addBook(new Book("1984", 1949, 328, Arrays.asList("George Orwell")));
        bookShelf.addBook(new Book("Good Omens", 1990, 288, Arrays.asList("Terry Pratchett", "Neil Gaiman")));

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(bookShelf, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BookShelf parseJSON() {
        try (Reader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, BookShelf.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addBookToJSON(Book newBook) {
        BookShelf bookShelf = parseJSON();
        bookShelf.addBook(newBook);

        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(bookShelf, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
