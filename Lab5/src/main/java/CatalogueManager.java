import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CatalogueManager {

    public static class DocumentNullException extends Exception {
        public DocumentNullException() {
            super("Document cannot be null");
        }
    }

    public static class CatalogNullException extends Exception {
        public CatalogNullException() {
            super("Catalog cannot be null");
        }
    }

    public static class CatalogSaveException extends Exception {
        public CatalogSaveException() {
            super("Error saving catalog");
        }
    }

    public static class CatalogLoadException extends Exception {
        public CatalogLoadException() {
            super("Error loading catalog");
        }
    }

    public static void add(Document doc, Catalog catalog) throws DocumentNullException, CatalogNullException {
        if (doc == null) {
            throw new DocumentNullException();
        }
        if (catalog == null) {
            throw new CatalogNullException();
        }
        catalog.add(doc);
    }

    public static String catalogToString(Catalog catalog) throws CatalogNullException {
        if (catalog == null) {
            throw new CatalogNullException();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Catalog:\n");
        for (Document doc : catalog.getDocuments()) {
            sb.append(doc.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void save(Catalog catalog, String fileName) throws CatalogNullException, CatalogSaveException {
        if (catalog == null) {
            throw new CatalogNullException();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(fileName), catalog);
        } catch (JsonProcessingException e) {
            throw new CatalogSaveException();
        } catch (IOException e) {
            throw new CatalogSaveException();
        }
    }

    public static Catalog load(String fileName) throws CatalogLoadException {
        try {
            byte[] mapData = Files.readAllBytes(Paths.get(fileName));
            Map<String, String> myMap = new HashMap<String, String>();
            ObjectMapper mapper = new ObjectMapper();
            myMap = mapper.readValue(mapData, HashMap.class);
            //return mapper.readValue(new File(fileName), Catalog.class);
            System.out.println(myMap);
        } catch (JsonParseException e) {
            throw new CatalogLoadException();
        } catch (IOException e) {
            throw new CatalogLoadException();
        }
        return null;
    }
}
