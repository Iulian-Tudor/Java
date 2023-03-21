import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
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

    /*
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

            byte[] mapData = Files.readAllBytes(Paths.get("catalog.json"));
            Map<String,String> myMap = new HashMap<String, String>();
            ObjectMapper mapper = new ObjectMapper();
            myMap = mapper.readValue(mapData, HashMap.class);
            //return mapper.readValue(new File(fileName), Catalog.class);
            System.out.println(myMap);

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    } */

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

        // Create a new catalog
        Catalog catalog = new Catalog();

        // Create some new documents and add them to the catalog
        Document doc1 = new Document("1", "Document 1", "/path/to/doc1", "author:John Doe", "type:pdf");
        Document doc2 = new Document("2", "Document 2", "/path/to/doc2", "author:Jane Smith", "type:doc");
        Document doc3 = new Document("3", "Document 3", "/path/to/doc3", "author:John Doe", "type:pdf");
        try {
            CatalogueManager.add(doc1, catalog);
            CatalogueManager.add(doc2, catalog);
            CatalogueManager.add(doc3, catalog);
        } catch (CatalogueManager.DocumentNullException | CatalogueManager.CatalogNullException e) {
            e.printStackTrace();
        }

        // Print out the catalog
        try {
            System.out.println(CatalogueManager.catalogToString(catalog));
        } catch (CatalogueManager.CatalogNullException e) {
            e.printStackTrace();
        }

        // Add some tags to the catalog
        catalog.setTag("owner", "Alice");
        catalog.setTag("department", "Marketing");

        // Save the catalog to a file
        try {
            CatalogueManager.save(catalog, "catalog2.json");
        } catch (CatalogueManager.CatalogNullException | CatalogueManager.CatalogSaveException e) {
            e.printStackTrace();
        }

        // Load the catalog from a file
        Catalog loadedCatalog = null;
        try {
            loadedCatalog = CatalogueManager.load("catalog2.json");
        } catch (CatalogueManager.CatalogLoadException e) {
            e.printStackTrace();
        }

    }
}