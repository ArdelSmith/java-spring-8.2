package org.example.components;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public interface FileProcessor {
    boolean supports(File file);
    void process(File file);

    void provideListOfFunctions();
}
