package springboot;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.Receipt;
import file.FileHandler;

@RestController
@RequestMapping(ReceiptScannerController.RECEIPTSCANNER_MODEL_SERVICE_PATH)
public class ReceiptScannerController {
    
    public static final String RECEIPTSCANNER_MODEL_SERVICE_PATH = "receiptscanner";

    /**
     * Gets all receipts
     * 
     * @return List<Receipt> with all the receipts saved
     * @throws IOException if there is a problem writing the receipt
     */
    @GetMapping
    public List<Receipt> getReceipts() throws IOException {
        return FileHandler.readReceipts();
    }

    /**
     * Adds the given receipt
     * 
     * @param receipt - Receipt to be added
     * @throws IOException if there is a problem writing the receipt
     */
    @PostMapping
    public void addReceipt(@RequestBody Receipt receipt) throws IOException {
        FileHandler.writeReceipt(receipt);
    }
}
