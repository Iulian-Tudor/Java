import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Catalog {
    private List<Document> documents;

    public Catalog() {
        this.documents = new ArrayList<>();
    }

    public void addDocument(Document doc) {
        documents.add(doc);
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Document doc : documents) {
            sb.append(doc.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public void save(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(fileName), this);
    }

    public static Catalog load(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(fileName), Catalog.class);
    }
}
