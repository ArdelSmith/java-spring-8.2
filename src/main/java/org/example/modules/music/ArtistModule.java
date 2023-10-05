package org.example.modules.music;

import com.mpatric.mp3agic.Mp3File;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class ArtistModule extends MusicModule{

    public void showArtistName(File file){
        try{
            Mp3File mp3file = new Mp3File(file.getAbsolutePath());
            String name = mp3file.getId3v1Tag().getArtist();
            System.out.println(name);
        }
        catch (Exception ignored){
        }
    }

    @Override
    public void showFunctionDescription() {
        System.out.println("This function will show name of artist of your music file");
    }
}
