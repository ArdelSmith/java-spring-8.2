package org.example.modules.music;

import com.mpatric.mp3agic.Mp3File;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class DurationModule extends MusicModule{

    public void showDuration(File file){
        Header h = null;
        FileInputStream f = null;
        try {
            f = new FileInputStream(file.getAbsolutePath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Mp3File.class.getName()).log(Level.SEVERE, null, ex);
        }
        var bitstream = new Bitstream(f);
        try {
            h = bitstream.readFrame();
        } catch (BitstreamException ex) {
            Logger.getLogger(Mp3File.class.getName()).log(Level.SEVERE, null, ex);
        }
        int size = h.calculate_framesize();
        float ms_per_frame = h.ms_per_frame();
        int maxSize = h.max_number_of_frames(10000);
        float t = h.total_ms(size);
        long tn = 0;
        try {
            tn = f.getChannel().size();
        } catch (IOException ex) {
            Logger.getLogger(Mp3File.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            int min = h.min_number_of_frames(500);
            var res = h.total_ms((int) tn)/1000;
            System.out.println("Duration is: " + (int)res /60 + " minutes and " + (int)res%60 + " seconds");
        }
        catch (Exception e){

        }
    }

    @Override
    public void showFunctionDescription() {
        System.out.println("This function will show duration of your music file");
    }
}
