package org.example.modules.image;

import org.springframework.stereotype.Component;

import javax.imageio.*;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

@Component
public class TypeTransfer extends ImageModule{
    @Override
    public void showFunctionDescription() {
        System.out.println("This function will convert your .png image to .jpeg");
    }

    @Override
    public void process(File file){
        try{

            BufferedImage inputImage = ImageIO.read(file);

            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpg");
            ImageWriter writer = writers.next();

            File outputFile = new File("output.jpg");
            ImageOutputStream outputStream = ImageIO.createImageOutputStream(outputFile);
            writer.setOutput(outputStream);

            ImageWriteParam params = writer.getDefaultWriteParam();
            params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            params.setCompressionQuality(0.5f);

            writer.write(null, new IIOImage(inputImage, null, null), params);

            outputStream.close();
            writer.dispose();
        }
        catch (Exception ignore){

        }
    }
}
