package org.example.components;

import org.springframework.stereotype.Component;

import javax.naming.spi.DirectoryManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            String input = reader.readLine();
            int choice = Integer.parseInt(input);
            if (choice == 0) showFilesInDir(file);
            else if (choice == 1) countTotalFilesSize(file);
            else if (choice == 2) findFileWithLongestName(file);
            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("Wrong value! Repeat!\n");
            process(file);
        }
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
        File[] files = file.listFiles();
        if (files.length == 0){
            System.out.println("No files in dir");
            return;
        }
        System.out.println("Directory has these files iside:");
        for (int i = 0; i < files.length; i ++){
            System.out.println(i + ". " + files[i].getName());
        }
    }

    public void countTotalFilesSize(File file){
        File[] files = file.listFiles();
        if (files.length == 0){
            System.out.println("No files in dir");
            return;
        }
        long size = 0;
        for (File value : files) {
            if (value.isDirectory()) continue;
            size += value.length();
        }
        System.out.println("Size of files in bytes in dir is: " + size);
    }

    public void findFileWithLongestName(File file){
        File[] files = file.listFiles();
        File f;
        if (files.length != 0) f = files[0];
        else {
            System.out.println("No files in dir");
            return;
        }
        for (int i = 0; i < files.length; i ++){
            if (files[i].length() > f.length()) f = files[i];
        }
        System.out.println("The longest file in dir is: " + f.getName() + " with size of " + f.length() + " bytes");
    }
}
