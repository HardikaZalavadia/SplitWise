package commands;

public interface Commands {
    public void execute(String input);
    public boolean match(String input);
}
