package org.example.modules.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EXIF extends ImageModule{
    @Override
    public void showFunctionDescription() {
        System.out.println("This function will provide EXIF info");
    }

    @Override
    public void process(File file){
        try{
            Metadata metadata = ImageMetadataReader.readMetadata(file.getAbsoluteFile());
            Iterable<Directory> dirs = metadata.getDirectories();
            for (var e: dirs){
                for (Tag tag : e.getTags()) {
                    System.out.println(tag);
                }
            }
        }
        catch (Exception ignore){

        }
    }
}
