package dev.venketesh.splitwise;

import dev.venketesh.splitwise.command.CommandExecutor;
import jakarta.persistence.EntityListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class SplitwiseApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseApplication.class, args);
    }

    private Scanner scanner = new Scanner(System.in);
    @Autowired
    private CommandExecutor commandExecutor;

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            System.out.println("Enter command: ");
            String command = scanner.nextLine();
            commandExecutor.execute(command);
        }
    }
}
