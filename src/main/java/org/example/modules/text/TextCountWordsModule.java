package org.example.modules.text;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

@Component
public class TextCountWordsModule extends TextModule{
    @Override
    public void showFunctionDescription() {
        System.out.println("This function will count amount of words in text");
    }

    @Override
    public void process(File file) {
        try(Scanner sc = new Scanner(new FileInputStream(file))){
            int count=0;
            while(sc.hasNext()){
                sc.next();
                count++;
            }
            System.out.println("Number of words: " + count);
        }
        catch (Exception ignore) {
        }
    }
}
