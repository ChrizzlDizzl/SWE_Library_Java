import com.example.swe_library.Customer;
import com.example.swe_library.ObjectsDB;
import com.example.swe_library.PersonType;
import com.example.swe_library.PrepareData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.example.swe_library.ObjectsDB.customerMap;
import static org.junit.jupiter.api.Assertions.*;

public class PrepareDataTest {

    @Test
    public void testCutterInput() {
        String line = "\"Alice\",\"Smith\",\"Customer\"";
        String[] cuttedLine = PrepareData.cutterInput(line);

        assertArrayEquals(new String[]{"Alice", "Smith", "Customer"}, cuttedLine);
    }

    @Test
    public void testIdentifyInputs() {
        int taskID1 = PrepareData.identifyInputs("Data: Person, Kunde");
        assertEquals(0, taskID1);

        int taskID2 = PrepareData.identifyInputs("Data: Person, Mitarbeiter");
        assertEquals(1, taskID2);

        int taskID3 = PrepareData.identifyInputs("Data: Media, DVD");
        assertEquals(3, taskID3);

        int taskID4 = PrepareData.identifyInputs("Data: Media, Book");
        assertEquals(2, taskID4);

        int taskID5 = PrepareData.identifyInputs("Data: Invalid");
        assertEquals(-1, taskID5);
    }

    @Test
    public void testDataReader() {
        String filePath = "library.csv";
        PrepareData.dataReader(filePath);

        // Check if the data has been loaded correctly into your program.
        // Add assertions to validate the created objects in ObjectsDB.
        assertTrue(customerMap.size() > 0);
        assertTrue(ObjectsDB.employeeMap.size() > 0);
        // Add more specific assertions as needed.
    }

    @Test
    public void testReturnDateReader() {
        Customer customer = new Customer();
        customer.id = 2;
        customer.password = "1";
        customer.personType = PersonType.valueOf("Mitarbeiter");
        customerMap.put(2, customer);

        // Arrange - Set up the conditions for the test
        String filePath = "src/test/resources/testReturnData.csv";

        // Act - Call the method to read and process the CSV data
        PrepareData.returnDateReader(filePath);

        // Assert - Check if the data has been stored correctly in your program
        int customerID = 2;
        String mediaID = "B2626Z";
        String returnDate = "2023-11-17";

        // Check if the data is correctly loaded into your data structures
        assertTrue(Customer.rentMap.containsKey(customerID));

        assertTrue(Customer.rentedMedia.containsKey(mediaID));

        assertEquals(LocalDate.parse(returnDate), Customer.rentedMedia.get(mediaID));
    }
}
