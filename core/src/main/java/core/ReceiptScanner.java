package core;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ReceiptScanner {

    public static String scanImage(String filename) throws TesseractException {
        File imageFile = new File("core/src/main/resources/core/" + filename);
        ITesseract instance = new Tesseract();
        instance.setDatapath("tessdata");
        return instance.doOCR(imageFile);
    }
}
