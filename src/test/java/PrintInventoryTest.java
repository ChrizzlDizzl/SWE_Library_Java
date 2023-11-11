import com.example.sweLibrary.PrepareData;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.example.sweLibrary.InteractionsMedia.showInventory;
import static org.junit.Assert.assertEquals;

public class PrintInventoryTest {
    @Test
    public void testSearchItem() {
        //load data
        String dataFile = "src\\test\\resources\\testLibrary.csv";
        PrepareData.dataReader(dataFile);
        String fileReturnDates = "src\\test\\resources\\testReturnData.csv";
        PrepareData.returnDateReader(fileReturnDates);

        String expectedOutput = "Item 0: \n" +
                "MediaCategory: Buch\n" +
                "MediaID: B123X\n" +
                "MediaName: Harry Potter und der Stein der Weisen\n" +
                "PublishDate: 1997-07-02\n" +
                "Publisher: Carlsen\n" +
                "Availability: true"+"\n\n"+
                "Item 1: \n" +
                "MediaCategory: Buch\n" +
                "MediaID: A123X\n" +
                "MediaName: Harry Potter und der Stein der Weisen\n" +
                "PublishDate: 1997-07-02\n" +
                "Publisher: Carlsen\n" +
                "Availability: false";

        // Redirect System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // When
        showInventory();

        // Reset System.out
        System.setOut(System.out);

        // Normalize the expected output by replacing consecutive whitespaces with a single space
        String normalizedExpectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        String normalizedActualOutput = outContent.toString().replaceAll("\\s+", " ").trim();

        // Normalize the actual output by replacing consecutive whitespaces with a single space
        expectedOutput = expectedOutput.toString().replaceAll("\\s+", " ").trim();
        // Then
        assertEquals(normalizedExpectedOutput, normalizedActualOutput);
    }
}
