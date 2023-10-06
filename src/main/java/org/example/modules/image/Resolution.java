package org.example.modules.image;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Resolution extends ImageModule{
    @Override
    public void showFunctionDescription() {
       System.out.println("This function will show size of your image");
    }

    @Override
    public void process(File file){
        try{
            Metadata metadata = ImageMetadataReader.readMetadata(file.getAbsoluteFile());
            Iterable<Directory> dirs = metadata.getDirectories();
            for (var e: dirs){
                if (e.getName().equals("PNG-IHDR")){
                    System.out.println("Image size is:" + e.getString(0x1) + "x" + e.getString(0x2));
                }
                System.out.println(e.getString(0x10e));
            }
        }
        catch (Exception ignore){

        }
    }
}
