import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        // XML parsing demonstration
        System.out.println("XML Parsing Demonstration:");
        XMLParser xmlParser = new XMLParser("books.xml");
        BookShelf xmlBookShelf = xmlParser.parseXML();
        System.out.println("Initial XML content:");
        System.out.println(xmlBookShelf);

        // Add new book to XML
        xmlParser.addBookToXML("The Hobbit", 1937, 310, new String[]{"J.R.R. Tolkien"});
        xmlParser.saveXMLToFile();
        xmlBookShelf = xmlParser.parseXML();
        System.out.println("\nXML content after adding new book:");
        System.out.println(xmlBookShelf);

        // JSON parsing demonstration
        System.out.println("\nJSON Parsing Demonstration:");
        JSONParser jsonParser = new JSONParser("books.json");
        BookShelf jsonBookShelf = jsonParser.parseJSON();
        System.out.println("Initial JSON content:");
        System.out.println(jsonBookShelf);

        // Add new book to JSON
        Book newBook = new Book("The Hobbit", 1937, 310, Arrays.asList("J.R.R. Tolkien"));
        jsonParser.addBookToJSON(newBook);
        jsonBookShelf = jsonParser.parseJSON();
        System.out.println("\nJSON content after adding new book:");
        System.out.println(jsonBookShelf);
    }
}
