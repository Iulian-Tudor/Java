public class ListCommand implements Command {
    private Catalog catalog;

    public ListCommand(Catalog catalog) {
        this.catalog = catalog;
    }

    public void execute() throws InvalidCommandException {
        System.out.println(catalog.toString());
    }
}
