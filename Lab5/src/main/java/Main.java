import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* public static void main(String[] args) {

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

    } */
public class Main {
    public static void main(String[] args) throws IOException {
        Catalog catalog = new Catalog();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String commandString = scanner.nextLine();
            String[] tokens = commandString.split(" ");

            try {
                if (tokens[0].equals("add")) {
                    if (tokens.length < 4) {   //necesita obligatoriu id, nume si path. Tags sunt optionale
                        throw new InvalidCommandException("Invalid add command");
                    }
                    String id = tokens[1];
                    String name = tokens[2];
                    String location = tokens[3];
                    Map<String, String> tags = new HashMap<>();
                    for (int i = 4; i < tokens.length; i++) {
                        String[] tagTokens = tokens[i].split(":");
                        tags.put(tagTokens[0], tagTokens[1]);
                    }
                    Command command = new AddCommand(catalog, id, name, location, tags);
                    command.execute();
                } else if (tokens[0].equals("list")) {
                    if (tokens.length > 1) {
                        throw new InvalidCommandException("Invalid list command");
                    }
                    Command command = new ListCommand(catalog);
                    command.execute();
                } else if (tokens[0].equals("view")) {
                    if (tokens.length != 2) {
                        throw new InvalidCommandException("Invalid view command");
                    }
                    String location = tokens[1];
                    Command command = new ViewCommand(location);
                    command.execute();
                } else if (tokens[0].equals("report")) {
                    if (tokens.length > 1) {
                        throw new InvalidCommandException("Invalid report command");
                    }
                    Command command = new ReportCommand(catalog);
                    command.execute();
                } else if (tokens[0].equals("save")) {
                    if (tokens.length != 2) {
                        throw new InvalidCommandException("Invalid save command");
                    }
                    String fileName = tokens[1];
                    Command command = new SaveCommand(catalog, fileName);
                    command.execute();
                } else if (tokens[0].equals("load")) {
                    if (tokens.length != 2) {
                        throw new InvalidCommandException("Invalid load command");
                    }
                    LoadCommand loadCommand = new LoadCommand(catalog, tokens[1]);
                    loadCommand.execute();
                } else if (tokens[0].equals("exit")) {
                    break;
                } else {
                    throw new InvalidCommandException("Invalid command");
                }
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
