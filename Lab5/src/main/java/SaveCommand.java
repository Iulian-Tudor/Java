import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class SaveCommand implements Command {
    private Catalog catalog;
    private String filename;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public SaveCommand(Catalog catalog, String filename) {
        this.catalog = catalog;
        this.filename = filename;
    }

    @Override
    public void execute() {
        if (catalog == null) {
            System.out.println("Catalog cannot be null.");;
        }
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), catalog.getDocuments());
            System.out.println("Catalog saved successfully to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving catalog to file: " + e.getMessage());
        }

    }
}
