package org.example.components;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Component
public class ImageProcessor implements FileProcessor{

    @Override
    public boolean supports(File file) {
        String fileName = file.getName();
        String[] splitted = fileName.split("\\.");
        if (splitted.length != 0){
            String extension = splitted[splitted.length-1];
            return (extension.contains("png") || extension.contains("jpg"));
        }
        return false;
    }

    @Override
    public void process(File file) {
        System.out.println("This module has following functions:\n");
        provideListOfFunctions();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            String input = reader.readLine();
            int choice = Integer.parseInt(input);
            if (choice == 0) showImageSize(file);
            else if (choice == 1) showEXIF(file);
            else if (choice == 2) someFunction(file);
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
        sb.append("0. Show image size\n");
        sb.append("1. Show EXIF\n");
        sb.append("2. Find file with longest name in dir\n");
        System.out.println(sb);
    }

    public void showImageSize(File file){
        try{
            Metadata metadata = ImageMetadataReader.readMetadata(file.getAbsoluteFile());
            Iterable<Directory> dirs = metadata.getDirectories();
            for (var e: dirs){
                if (e.getName().equals("PNG-IHDR")){
                    System.out.println("Image size is:" + e.getString(0x1) + "x" + e.getString(0x2));
                }
                System.out.println(e.getString(0x10e));
            }
        }
        catch (Exception ignore){

        }
    }

    public void showEXIF(File file){
        try{
            Metadata metadata = ImageMetadataReader.readMetadata(file.getAbsoluteFile());
            Iterable<Directory> dirs = metadata.getDirectories();
            for (var e: dirs){
                for (Tag tag : e.getTags()) {
                    System.out.println(tag);
                }
            }
        }
        catch (Exception ignore){

        }
    }

    public void someFunction(File file){

    }

}
