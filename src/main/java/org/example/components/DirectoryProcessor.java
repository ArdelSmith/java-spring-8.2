package org.example.components;


import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class DirectoryProcessor implements FileProcessor{
    @Override
    public boolean supports(File file) {
        return file.isDirectory();
    }

    @Override
    public void process(File file) {

    }
}
