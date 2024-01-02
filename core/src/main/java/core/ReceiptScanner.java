package core;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ReceiptScanner {

    private static String scanImage(String filename) throws TesseractException {
        File imageFile = new File("core/src/main/resources/core/" + filename);
        ITesseract instance = new Tesseract();
        instance.setDatapath("tessdata");
        return instance.doOCR(imageFile);
    }

    public static Receipt scanReceipt(String filename) throws TesseractException {
        String receiptText = scanImage(filename);
        String[] lines = receiptText.split(System.lineSeparator());

        Receipt receipt = new Receipt();
        for(String line : lines) {
            String[] splitLine = line.trim().split("\\s+");

            int i = 0;
            String name = splitLine[i];
            while (!splitLine[i].matches("-?\\d+(\\.\\d+)?|-?\\d+(\\,\\d+)?") && i != splitLine.length) {
                name += " " + splitLine[i];
                i++;
            }

            float price = 0;
            String priceStr = splitLine[splitLine.length-1].replace(',', '.');
            if (priceStr.matches("-?\\d+(\\.\\d+)?")) {
                price = Float.parseFloat(priceStr);
            }

            receipt.addItem(name, price);
        }

        return receipt;
    }

    public static void main(String args[]) throws Exception {
        System.out.println(scanReceipt("IMG_3974.png"));
    }
}
