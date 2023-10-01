package org.example;

import org.example.components.FileProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Main {

    @Autowired
    public static Map<String, FileProcessor> fileProcessorsList = new HashMap<>();

    private static ConfigurableApplicationContext app_context;

    private static File file;

    public static void main(String[] args) {
        app_context = SpringApplication.run(Main.class, args);
        fileProcessorsList = app_context.getBeansOfType(FileProcessor.class);
        if (args.length != 1) {
            System.out.println("Add filename as an argument");
            return;
        }

        String fileName = args[0];
        if (checkFileExists(fileName)) {
            Path p = Paths.get(fileName);
            findSupportableComponents(file);
        } else {
            System.out.println("File or directory doesn't exist");
        }
    }

    private static boolean checkFileExists(String fileName) {
        Path p = Paths.get(fileName);
        File temp_file = new File(p.toAbsolutePath().toString());
        if (temp_file.exists()) {
            file = temp_file;
            return true;
        }
        return false;
    }

    private static void findSupportableComponents(File file) {
        fileProcessorsList.stream()
                .filter(processor -> processor.supports(file))
                .forEach(processor -> System.out.println(processor.getClass().getSimpleName()));

    }

}
