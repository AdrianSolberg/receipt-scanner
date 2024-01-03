package core;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * Class for scanning receipt using tesseract
 */
public class ReceiptScanner {

    /**
     * Scans the text from an image
     * 
     * @param filename - String with the name og thee imagefile to scan
     * @return String with the text from the image
     * @throws TesseractException if there is an issue scanning the image
     */
    private static String scanImage(String filename) throws TesseractException {
        File imageFile = new File("core/src/main/resources/core/" + filename);
        ITesseract instance = new Tesseract();
        instance.setDatapath("tessdata");
        return instance.doOCR(imageFile);
    }

    /**
     * Scans the text from a receipt
     * 
     * @param filename - String with the name og thee imagefile to scan
     * @return String with the text from the receipt
     * @throws TesseractException if there is an issue scanning the receipt
     * @see ReceiptScanner#scanImage(String)
     */
    public static Receipt scanReceipt(String filename) throws TesseractException {
        String receiptText = scanImage(filename);
        String[] lines = receiptText.split(System.lineSeparator());

        // Manipulates each line in order to get item name and price 
        // Note: Other information for the item is ignored
        Receipt receipt = new Receipt();
        for(String line : lines) {
            String[] splitLine = line.trim().split("\\s+");

            int i = 0;
            String name = splitLine[i];
            while (!splitLine[i].matches("-?\\d+(\\.\\d+)?|-?\\d+(\\,\\d+)?") && i != splitLine.length) {
                name += " " + splitLine[i];
                i++;
            }

            double price = 0;
            String priceStr = splitLine[splitLine.length-1].replace(',', '.');
            if (priceStr.matches("-?\\d+(\\.\\d+)?")) {
                price = Double.parseDouble(priceStr);
            }

            receipt.addItem(name, price);
        }

        return receipt;
    }
}
