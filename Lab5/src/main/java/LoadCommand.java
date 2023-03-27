import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Command {
    private Catalog catalog;
    private String filename;

    public LoadCommand(Catalog catalog, String filename) {
        this.catalog = catalog;
        this.filename = filename;
    }

    @Override
    public void execute() {
        File file = new File(filename);
        if (!file.exists() || !file.isFile()) {
            System.out.println("File not found: " + filename);
            return;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Document[] documents = objectMapper.readValue(new File(filename), Document[].class);
            for (Document document : documents) {
                System.out.println(document);
            }
        } catch (IOException e) {
            System.out.println("Error loading catalog from file: " + e.getMessage());
        }
    }
}

