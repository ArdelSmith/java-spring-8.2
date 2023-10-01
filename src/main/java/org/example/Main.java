package org.example;

import org.example.components.FileProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan(basePackageClasses = Main.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
                pattern = "org\\.example\\.components\\.(Directory|Image).*"))
public class Main {
    public static Map<String, FileProcessor> fileProcessorsList = new HashMap<>();

    private static File file;

    public static void main(String[] args) {
        ConfigurableApplicationContext app_context = SpringApplication.run(Main.class, args);
        fileProcessorsList = app_context.getBeansOfType(FileProcessor.class);
        if (args.length != 1) {
            System.out.println("Add filename as an argument");
            return;
        }

        String fileName = args[0];
        if (checkFileExists(fileName)) {
            findSupportableComponents(file);
        } 
        else {
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
        List<FileProcessor> components = new ArrayList<>();
        fileProcessorsList.forEach((k, v) -> {
            if (v.supports(file)) components.add(v);
        });
    }

}
