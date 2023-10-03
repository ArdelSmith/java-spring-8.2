package org.example.components;

import jdk.jfr.Description;
import org.springframework.stereotype.Component;

import java.io.File;


@Component
public class TextProcessor implements FileProcessor {

    @Override
    public boolean supports(File file) {
        String[] splitted = file.getName().split(".");
        if (splitted.length != 0){
            String extension = splitted[splitted.length-1];
            return (extension.contains(".txt"));
        }
        return false;
    }

    @Override
    public void process(File file) {

    }

    @Override
    public void provideListOfFunctions(){
        StringBuilder sb = new StringBuilder();
        sb.append("0. Show files in directory\n");
        sb.append("1. Count total files size in dir\n");
        sb.append("2. Find file with longest name in dir\n");
        System.out.println(sb);
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
