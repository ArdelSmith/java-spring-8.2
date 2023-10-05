package org.example.modules.text;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;

import java.io.File;


@Component
public class TextProcessor implements FileProcessor {

    @Override
    public boolean supports(File file) {
        String fileName = file.getName();
        String[] splitted = fileName.split("\\.");
        if (splitted.length != 0){
            String extension = splitted[splitted.length-1];
            return (extension.contains("txt"));
        }
        return false;
    }

    @Override
    public void showFunctionDescription() {

    }


    //This func counts amount of raws
    public void CountRaws(File file){

    }
    //This func counts amount of diff words
    public void CountWords(File file){

    }

    //This function counts chars
    public void CountChars(File file){

    }
}
