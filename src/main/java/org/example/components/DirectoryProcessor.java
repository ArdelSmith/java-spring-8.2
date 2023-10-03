package org.example.components;

import org.springframework.stereotype.Component;
import java.io.File;

@Component
public class DirectoryProcessor implements FileProcessor{
    @Override
    public boolean supports(File file) {
        return file.isDirectory();
    }

    @Override
    public void process(File file) {
        System.out.println("This module has following functions:\n");
        provideListOfFunctions();
    }

    @Override
    public void provideListOfFunctions(){
        StringBuilder sb = new StringBuilder();
        sb.append("0. Show files in directory\n");
        sb.append("1. Count total files size in dir\n");
        sb.append("2. Find file with longest name in dir\n");
        System.out.println(sb);
    }

    public void showFilesInDir(File file){

    }

    public void countTotalFilesSize(File file){

    }

    public void findFileWithLongestName(File file){

    }
}
