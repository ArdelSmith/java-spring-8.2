package org.example.modules.dir;

import org.example.modules.FileProcessor;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public abstract class DirModule implements FileProcessor {
    public boolean supports(File file) {
        return file.isDirectory();
    }

    @Override
    public abstract void process(File file);
}
