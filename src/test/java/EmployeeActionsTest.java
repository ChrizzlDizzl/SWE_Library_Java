import com.example.swe_library.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.time.LocalDate;

class EmployeeActionsTest {

    @Test
    void returnMediaTest() {
        String dataFile = "src\\test\\resources\\testLibrary.csv";
        PrepareData.dataReader(dataFile);
        String fileReturnDates = "src\\test\\resources\\testReturnData.csv";
        PrepareData.returnDateReader(fileReturnDates);

        HashMap<String, LocalDate> rentedMedia = new HashMap<>();
        rentedMedia.put("A123X", LocalDate.now().minusDays(7)); // Set return date 7 days ago
        Customer.rentMap.put(2, rentedMedia);

        EmployeeActions.returnMedia(0, 2, "A123X");

        assertFalse(Customer.rentMap.containsKey(2));

        assertFalse(rentedMedia.containsKey("A123X"));
    }

    @Test
    public void testRentMedia_SuccessfulRent() {
        String dataFile = "src\\test\\resources\\testLibrary.csv";
        PrepareData.dataReader(dataFile);
        String fileReturnDates = "src\\test\\resources\\testReturnData.csv";
        PrepareData.returnDateReader(fileReturnDates);

        EmployeeActions.rentMedia(0, 3, "B123X");

        assertTrue(Customer.rentedMedia.containsKey("B123X"));
        LocalDate expectedReturnDate = LocalDate.now().plusDays(14);
        assertEquals(expectedReturnDate, Customer.rentedMedia.get("B123X"));
    }
}