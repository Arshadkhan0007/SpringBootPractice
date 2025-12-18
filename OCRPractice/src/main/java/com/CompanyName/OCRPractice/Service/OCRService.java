package com.CompanyName.OCRPractice.Service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

@Service
public class OCRService {

    public String extractTextFromImage(MultipartFile imageFile){
        ITesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\Sreenivas Bandaru\\Desktop\\TessData\\tessdata_best-main\\");
        tesseract.setLanguage("eng");
        try{
            BufferedImage bufferedImage = ImageIO.read(imageFile.getInputStream());
            if(bufferedImage == null){
                return "Image could not be read!";
            }
            return tesseract.doOCR(bufferedImage);
        } catch (TesseractException e) {
            return "Tesseract Exception has occurred";
        } catch (IOException e) {
            return "IOException has occurred";
        }
    }

}
