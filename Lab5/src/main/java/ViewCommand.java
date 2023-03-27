import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ViewCommand implements Command {
    private String location;

    public ViewCommand(String location) {
        this.location = location;
    }

    public void execute() throws InvalidCommandException {
        try {
            Desktop.getDesktop().open(new File(location));
        } catch (IOException e) {
            throw new InvalidCommandException("Could not open file: " + e.getMessage());
        }
    }
}
