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
    private static String scanImage(String filename) throws Exception {
        if (filename == null) {
            throw new IllegalArgumentException("Filename can not be null");
        }
        File imageFile = new File(ReceiptScanner.class.getResource(filename).toURI());
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
    public static Receipt scanReceipt(String filename) throws Exception {
        String receiptText = scanImage(filename);
        String[] lines = receiptText.split(System.lineSeparator());

        // Manipulates each line in order to get item name and price 
        // Note: Other information for the item is ignored
        Receipt receipt = new Receipt();
        for(String line : lines) {
            String[] splitLine = line.trim().split("\\s+");

            int i = 0;
            String name = splitLine[i];
            while (i != splitLine.length && !splitLine[i].matches("-?\\d+(\\.\\d+)?|-?\\d+(\\,\\d+)?")) {
                name += " " + splitLine[i];
                i++;
            }

            double price = 0;
            String priceStr = splitLine[splitLine.length-1].replace(',', '.');
            if (priceStr.matches("-?\\d+(\\.\\d+)?")) {
                price = Double.parseDouble(priceStr);
            }

            receipt.addItem(new Item(name, price));
        }

        return receipt;
    }

    public static void main(String args[]) throws Exception {
        System.out.println(ReceiptScanner.scanImage("test.png"));
        Receipt receipt = ReceiptScanner.scanReceipt("test.png");
        System.out.println(receipt.getTotal());

    }
}
