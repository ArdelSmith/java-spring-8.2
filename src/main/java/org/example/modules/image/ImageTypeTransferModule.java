package org.example.modules.image;

import org.springframework.stereotype.Component;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ImageTypeTransferModule extends ImageModule{
    @Override
    public void showFunctionDescription() {
        System.out.println("This function will convert your .png image to .jpeg");
    }

    @Override
    public void process(File file){
        try{
            Path source = Paths.get(file.getAbsolutePath());
            Path target = Paths.get(file.getPath() + ".jpg");

            BufferedImage originalImage = ImageIO.read(source.toFile());

            // jpg needs BufferedImage.TYPE_INT_RGB
            // png needs BufferedImage.TYPE_INT_ARGB

            // create a blank, RGB, same width and height
            BufferedImage newBufferedImage = new BufferedImage(
                    originalImage.getWidth(),
                    originalImage.getHeight(),
                    BufferedImage.TYPE_INT_RGB);

            // draw a white background and puts the originalImage on it.
            newBufferedImage.createGraphics()
                    .drawImage(originalImage,
                            0,
                            0,
                            Color.WHITE,
                            null);

            // save an image
            ImageIO.write(newBufferedImage, "jpg", target.toFile());
        }
        catch (Exception ignore){
        }
    }
}
