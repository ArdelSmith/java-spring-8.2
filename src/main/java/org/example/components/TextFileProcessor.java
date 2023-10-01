package org.example.components;

import org.springframework.stereotype.Component;

import java.io.File;


@Component
public class TextFileProcessor implements FileProcessor {

    @Override
    public boolean supports(File file) {
        return false;
    }

    @Override
    public void process(File file) {

    }
}
