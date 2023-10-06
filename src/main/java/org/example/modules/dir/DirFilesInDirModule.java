package org.example.modules.dir;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirFilesInDirModule extends DirModule{

    @Override
    public void showFunctionDescription() {
        System.out.println("This function shows files in dir");
    }

    @Override
    public void process(File file){
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
}
