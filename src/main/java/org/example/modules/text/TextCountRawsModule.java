package org.example.modules.text;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class TextCountRawsModule extends TextModule{
    @Override
    public void showFunctionDescription() {
        System.out.println("This function will count amount of raws");
    }

    @Override
    public void process(File file){
        try(var e = Files.lines(Paths.get(file.getAbsolutePath()))){
            System.out.println(e.count());
        }
        catch (Exception ignore){

        }
    }
}
