package dev.venketesh.splitwise.command;

public interface Command {
    boolean matches(String cmd);
    void execute(String cmd);
}
