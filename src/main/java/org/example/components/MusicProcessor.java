package org.example.components;

import com.mpatric.mp3agic.Mp3File;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MusicProcessor implements FileProcessor {
    @Override
    public boolean supports(File file) {
        String fileName = file.getName();
        String[] splitted = fileName.split("\\.");
        if (splitted.length != 0){
            String extension = splitted[splitted.length-1];
            return (extension.contains("mp3") || extension.contains("wav"));
        }
        return false;
    }

    @Override
    public void process(File file) {
        System.out.println("This module has following functions:\n");
        provideListOfFunctions();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            String input = reader.readLine();
            int choice = Integer.parseInt(input);
            if (choice == 0) showTrackName(file);
            else if (choice == 1) showDuration(file);
            else if (choice == 2) showArtistName(file);
            else throw new Exception();
        }
        catch (Exception e){
            System.out.println("Wrong value! Repeat!\n");
            process(file);
        }
    }

    @Override
    public void provideListOfFunctions(){
        StringBuilder sb = new StringBuilder();
        sb.append("0. Show track name\n");
        sb.append("1. Show track duration\n");
        sb.append("2. Show artist name\n");
        System.out.println(sb);
    }

    public void showTrackName(File file){
        try{
            Mp3File mp3file = new Mp3File(file.getAbsolutePath());
            String name = mp3file.getId3v1Tag().getTitle();
            System.out.println(name);
        }
        catch (Exception ignored){
        }
    }

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

    public void showArtistName(File file){
        try{
            Mp3File mp3file = new Mp3File(file.getAbsolutePath());
            String name = mp3file.getId3v1Tag().getArtist();
            System.out.println(name);
        }
        catch (Exception ignored){
        }
    }
}
