import java.util.Map;

public class AddCommand implements Command {
    private Catalog catalog;
    private String id;
    private String name;
    private String location;
    private Map<String, String> tags;

    public AddCommand(Catalog catalog, String id, String name, String location, Map<String, String> tags) {
        this.catalog = catalog;
        this.id = id;
        this.name = name;
        this.location = location;
        this.tags = tags;
    }

    public void execute() throws InvalidCommandException {
        Document doc = new Document(id, name, location);
        for (Map.Entry<String, String> entry : tags.entrySet()) {
            doc.addTag(entry.getKey(), entry.getValue());
        }
        catalog.addDocument(doc);
    }
}
