import java.io.IOException;

public interface Command {
    public void execute() throws InvalidCommandException;
}
