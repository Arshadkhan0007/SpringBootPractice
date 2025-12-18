package com.example.PdfNExcelHandling.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PdfService {

    public void extractTextFromRegularPdf(InputStream in){
        try(PDDocument document = PDDocument.load(in)){
            PDFTextStripper stripper = new PDFTextStripper();
            System.out.println(stripper.getText(document));
        } catch (IOException e) {
            System.out.println("Unable to extract text\n" + e);
        }
    }

    public void extractTextFromImagePdf(InputStream in){
        try (PDDocument document = PDDocument.load(in)){

            PDFRenderer renderer = new PDFRenderer(document);
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("C:\\Users\\Sreenivas Bandaru\\Desktop\\TessData\\tessdata_best-main");
            tesseract.setLanguage("eng");

            for(int pageNum = 0; pageNum < document.getNumberOfPages(); pageNum++){
                BufferedImage image = renderer.renderImageWithDPI(pageNum, 300);
                System.out.println("Page Number: " + pageNum);
                System.out.println(tesseract.doOCR(image));
            }

        } catch (IOException e){
            System.out.println("Unable to extract text\n" + e);
        } catch (TesseractException e) {
            System.out.println("Unable to extract text\n" + e);
        }
    }

}
