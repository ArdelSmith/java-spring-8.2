package org.example.components;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class MusicProcessor implements FileProcessor {
    @Override
    public boolean supports(File file) {
        String[] splitted = file.getName().split(".");
        if (splitted.length != 0){
            String extension = splitted[splitted.length-1];
            return (extension.contains(".mp3") || extension.contains(".wav"));
        }
        return false;
    }

    @Override
    public void process(File file) {

    }

    @Override
    public void provideListOfFunctions(){

    }

    public void showTrackName(File file){

    }

    public void showDuration(File file){

    }

    //TODO:
    //public void someFunction(){
    //
    //}
}
