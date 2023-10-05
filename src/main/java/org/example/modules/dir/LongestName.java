package org.example.modules.dir;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class LongestName extends DirModule{



    @Override
    public void showFunctionDescription() {
        System.out.println("This function finds file with longest name in directory");
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
