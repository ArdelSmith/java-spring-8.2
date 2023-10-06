package org.example.modules.dir;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirLongestNameModule extends DirModule{



    @Override
    public void showFunctionDescription() {
        System.out.println("This function finds file with longest name in directory");
    }

    @Override
    public void process(File file){
        File[] files = file.listFiles();
        File f;
        if (files.length != 0) f = files[0];
        else {
            System.out.println("No files in dir");
            return;
        }
        for (File value : files) {
            if (value.length() > f.length()) f = value;
        }
        System.out.println("The longest file in dir is: " + f.getName() + " with size of " + f.length() + " bytes");
    }
}
