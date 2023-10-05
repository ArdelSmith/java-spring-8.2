package org.example.modules;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public interface FileProcessor {
    boolean supports(File file);

    void showFunctionDescription();
}
