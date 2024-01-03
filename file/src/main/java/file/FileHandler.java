package file;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import core.Receipt;

/**
 * Class for handling reading from and writing to file
 */
public class FileHandler {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final Path filePath = Path.of(System.getProperty("user.home") + System.getProperty("file.separator") + "receipts.json");

    /**
     * Reads a list of receipts from a predetermined file
     * 
     * @return List<Receipt> saved in file
     * @throws IOException if there is an issue reading from file
     */
    public static List<Receipt> readReceipts() throws IOException {
        if (!Files.exists(filePath)) {
            return new ArrayList<>();
        }
        Reader reader = new FileReader(filePath.toFile(), Charset.forName("UTF-8"));
        Type receiptListType = new TypeToken<List<Receipt>>() {}.getType();
        List<Receipt> data = gson.fromJson(reader, receiptListType);
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    /**
     * Writes the given receipt to file
     * 
     * @param receipt - Receipt to write to file
     * @throws IOException if there is an issue writing to file
     */
    public static void writeReceipt(Receipt receipt) throws IOException {
        if (receipt == null) {
            return;
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        List<Receipt> receipts = readReceipts();
        receipts.add(receipt);
        Writer writer = new FileWriter(filePath.toFile(), Charset.forName("UTF-8"));
        gson.toJson(receipts, writer);
        writer.close();
    }
}
