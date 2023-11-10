import static org.junit.Assert.*;

import com.example.swe_library.*;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.HashMap;

public class CustomerActionsTest {

    @Test
    public void testCustomerAccount() {
        String dataFile = "src\\test\\resources\\testLibrary.csv";
        PrepareData.dataReader(dataFile);
        String fileReturnDates = "src\\test\\resources\\testReturnData.csv";
        PrepareData.returnDateReader(fileReturnDates);

        LocalDate dateNow = LocalDate.now().plusDays(7);
        // Rent the sample media to the customer
        HashMap<String, LocalDate> rentMap = new HashMap<>();
        rentMap.put("A123X", LocalDate.now().plusDays(7)); // assuming a 7-day rental period
        Customer.rentMap.put(2, rentMap);

        // Redirect standard output for testing
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Call the customerAccount method
        CustomerActions.customerAccount(2);

        // Restore standard output
        System.setOut(System.out);

        //Verify the output
        String expectedOutput = "Search result 0: \n" +
                "MediaCategory: Buch\n" +
                "MediaID: A123X\n" +
                "MediaName: Harry Potter und der Stein der Weisen\n" +
                "PublishDate: " + "1997-07-02" + "\n" +
                "Publisher: Carlsen\n" +
                "\nYour Return-date is: " + dateNow;

        // Normalize the expected output by replacing consecutive whitespaces with a single space
        String normalizedExpectedOutput = expectedOutput.replaceAll("\\s+", " ").trim();

        // Normalize the actual output by replacing consecutive whitespaces with a single space
        String normalizedActualOutput = outContent.toString().replaceAll("\\s+", " ").trim();

        // Assert that the normalized actual output matches the normalized expected output
        assertEquals(normalizedExpectedOutput, normalizedActualOutput);
    }
}
