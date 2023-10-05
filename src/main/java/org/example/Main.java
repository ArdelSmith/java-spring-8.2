package org.example;

import org.example.modules.FileProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@ComponentScan(basePackages = "org.example.modules",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
                pattern = "org\\.example\\.modules\\.(music|text).*"))
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
        chooseSupportableComponents(components);
    }

    private static void chooseSupportableComponents(List<FileProcessor> components){
        if (!components.isEmpty()){
            System.out.println("Choose one of the following functions with a number:");
            for (int i = 0; i < components.size(); i++){
                System.out.println(i + String.format(". %s", components.get(i).getClass().getSimpleName()));
                components.get(i).showFunctionDescription();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try{
                String input = reader.readLine();
                var component = components.get(Integer.parseInt(input));
                component.process(file);
            }
            catch (Exception e){
                System.out.println("Wrong value! Repeat!\n");
                chooseSupportableComponents(components);
            }
        }
        else System.out.println("There is no available components");
    }
}
