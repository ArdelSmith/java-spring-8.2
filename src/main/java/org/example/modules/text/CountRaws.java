package org.example.modules.text;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class CountRaws extends TextModule{
    @Override
    public void showFunctionDescription() {
        System.out.println("This function will count amount of raws");
    }

    @Override
    public void process(File file){
        try{
            System.out.println(Files.lines(Paths.get(file.getAbsolutePath())));
        }
        catch (Exception ignore){

        }
    }
}
