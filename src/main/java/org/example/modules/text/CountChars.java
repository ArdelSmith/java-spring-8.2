package org.example.modules.text;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class CountChars extends TextModule{
    @Override
    public void showFunctionDescription() {
        System.out.println("This function will count amount of each char in text");
    }

    @Override
    public void process(File file) {
        try{
            Map<String, Integer> dict = new HashMap<>();
            BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
            String line;
            while ((line = reader.readLine()) != null){
                for (char c:
                        line.toCharArray()) {
                    if (dict.containsKey(c)) dict.put(String.valueOf(c), dict.get(c));
                    else dict.put(String.valueOf(c), 0);
                }
            }
            for (var key:
                    dict.keySet()) {
                System.out.println(key + ": "+ dict.get(key));
            }
        }
        catch (Exception ignore){

        }
    }
}
