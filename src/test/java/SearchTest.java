import com.example.swe_library.PrepareData;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.example.swe_library.InteractionsMedia.searchItem;
import static org.junit.Assert.assertEquals;

public class SearchTest {
    @Test
    public void testSearchItem() {
        //load data
        String dataFile = "src\\test\\resources\\testLibrary.csv";
        PrepareData.dataReader(dataFile);
        String fileReturnDates = "src\\test\\resources\\testReturnData.csv";
        PrepareData.returnDateReader(fileReturnDates);

        // Given
        String input = "A123X";
        String expectedOutput = "Search: \n" +
                "Searchresult 0: \n" +
                "MediaCategory: Buch\n" +
                "MediaID: A123X\n" +
                "MediaName: Harry Potter und der Stein der Weisen\n" +
                "PublishDate: 1997-07-02\n" +
                "Publisher: Carlsen\n" +
                "Publisher: false";

        // Redirect System.out to capture console output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // When
        searchItem(input);

        // Reset System.out
        System.setOut(System.out);

        // Normalize the expected output by replacing consecutive whitespaces with a single space
        String normalizedExpectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();
        String normalizedActualOutput = outContent.toString().replaceAll("\\s+", " ").trim();

        // Normalize the actual output by replacing consecutive whitespaces with a single space
        expectedOutput= expectedOutput.toString().replaceAll("\\s+", " ").trim();
        // Then
        assertEquals(normalizedExpectedOutput, normalizedActualOutput);
    }
}

