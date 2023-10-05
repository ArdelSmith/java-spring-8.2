package org.example.modules.text;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public abstract class TextModule implements FileProcessor {
    @Override
    public boolean supports(File file){
        String fileName = file.getName();
        String[] splitted = fileName.split("\\.");
        if (splitted.length != 0){
            String extension = splitted[splitted.length-1];
            return (extension.contains("txt"));
        }
        return false;
    }

    @Override
    public abstract void process(File file);
}
