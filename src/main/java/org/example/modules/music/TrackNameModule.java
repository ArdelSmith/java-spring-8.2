package org.example.modules.music;

import com.mpatric.mp3agic.Mp3File;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class TrackNameModule extends MusicModule{
    @Override
    public void showFunctionDescription() {
        System.out.println("This function will show trackname");
    }

    @Override
    public void process(File file){
        try{
            Mp3File mp3file = new Mp3File(file.getAbsolutePath());
            String name = mp3file.getId3v1Tag().getTitle();
            System.out.println(name);
        }
        catch (Exception ignored){
        }
    }
}
