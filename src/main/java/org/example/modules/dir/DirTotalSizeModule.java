package org.example.modules.dir;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirTotalSizeModule extends DirModule{


    @Override
    public void showFunctionDescription() {
        System.out.println("This");
    }

    @Override
    public void process(File file){
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
}
