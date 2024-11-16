import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class XMLParser {
    private Document doc;
    private String filePath;

    public XMLParser(String filePath) {
        this.filePath = filePath;
        createSampleXML();
    }

    private void createSampleXML() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.newDocument();

            // Create root element
            Element rootElement = doc.createElement("BookShelf");
            doc.appendChild(rootElement);

            // Add sample books
            addBookToXML("The Great Gatsby", 1925, 180, new String[]{"F. Scott Fitzgerald"});
            addBookToXML("1984", 1949, 328, new String[]{"George Orwell"});
            addBookToXML("Good Omens", 1990, 288, new String[]{"Terry Pratchett", "Neil Gaiman"});

            // Save the XML file
            saveXMLToFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addBookToXML(String title, int year, int pages, String[] authors) {
        Element book = doc.createElement("Book");

        Element titleElem = doc.createElement("title");
        titleElem.setTextContent(title);

        Element yearElem = doc.createElement("publishedYear");
        yearElem.setTextContent(String.valueOf(year));

        Element pagesElem = doc.createElement("numberOfPages");
        pagesElem.setTextContent(String.valueOf(pages));

        Element authorsElem = doc.createElement("authors");
        for (String author : authors) {
            Element authorElem = doc.createElement("author");
            authorElem.setTextContent(author);
            authorsElem.appendChild(authorElem);
        }

        book.appendChild(titleElem);
        book.appendChild(yearElem);
        book.appendChild(pagesElem);
        book.appendChild(authorsElem);

        doc.getDocumentElement().appendChild(book);
    }

    public void saveXMLToFile() {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BookShelf parseXML() {
        BookShelf bookShelf = new BookShelf();
        try {
            NodeList bookList = doc.getElementsByTagName("Book");

            for (int i = 0; i < bookList.getLength(); i++) {
                Node bookNode = bookList.item(i);
                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookElement = (Element) bookNode;

                    String title = bookElement.getElementsByTagName("title").item(0).getTextContent();
                    int year = Integer.parseInt(bookElement.getElementsByTagName("publishedYear").item(0).getTextContent());
                    int pages = Integer.parseInt(bookElement.getElementsByTagName("numberOfPages").item(0).getTextContent());

                    NodeList authorNodes = bookElement.getElementsByTagName("author");
                    ArrayList<String> authors = new ArrayList<>();
                    for (int j = 0; j < authorNodes.getLength(); j++) {
                        authors.add(authorNodes.item(j).getTextContent());
                    }

                    Book book = new Book(title, year, pages, authors);
                    bookShelf.addBook(book);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookShelf;
    }
}