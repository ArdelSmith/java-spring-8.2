package org.example.components;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ImageProcessor implements FileProcessor{

    @Override
    public boolean supports(File file) {
        String[] splitted = file.getName().split(".");
        if (splitted.length != 0){
            String extension = splitted[splitted.length-1];
            return (extension.contains(".jpg") || extension.contains(".png"));
        }
        return false;
    }

    @Override
    public void process(File file) {

    }

    @Override
    public void provideListOfFunctions(){

    }

    public void showImageSize(File file){

    }

    public void showEXIF(File file){

    }

    //TODO:
    //public void someFunction(File file){
    //
    //}

}
