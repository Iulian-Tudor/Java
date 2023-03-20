import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Catalog {
    private List<Document> documents;
    private Map<String, String> tags;

    public Catalog() {
        documents = new ArrayList<>();
        tags = new HashMap<>();
    }

    public void add(Document doc) {
        documents.add(doc);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Catalog:\n");
        for (Document doc : documents) {
            sb.append(doc.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void save(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(fileName), this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Catalog load(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(fileName), Catalog.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setTag(String name, String value) {
        tags.put(name, value);
    }

    public String getTag(String name) {
        return tags.get(name);
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public static void main(String[] args) {
        Catalog catalog = new Catalog();
        Document doc1 = new Document("doc1", "Document 1", "/path/to/doc1", "tag1:value1", "tag2:value2");
        Document doc2 = new Document("doc2", "Document 2", "http://example.com/doc2", "tag3:value3");
        catalog.add(doc1);
        catalog.add(doc2);
        catalog.setTag("category", "books");
        System.out.println(catalog.toString());
        catalog.save("catalog.json");
        Catalog loadedCatalog = Catalog.load("catalog.json");
        System.out.println(loadedCatalog.toString());
    }
}