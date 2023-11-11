import com.example.sweLibrary.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ChangeDataTests {
    @Test
    public void testChangeData_DeleteMedia() {
        String dataFile = "src\\test\\resources\\testLibrary.csv";
        PrepareData.dataReader(dataFile);
        String fileReturnDates = "src\\test\\resources\\testReturnData.csv";
        PrepareData.returnDateReader(fileReturnDates);

        String input = "A123X\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ChangeData.changeData(0);

        // Check if the media was removed from ObjectsDB.mediaMap
        Assertions.assertFalse(ObjectsDB.mediaMap.containsKey("A123X"));
    }
    @Test
    public void testNewData_AddNewMedia() {
        String dataFile = "src\\test\\resources\\testLibrary.csv";
        PrepareData.dataReader(dataFile);
        String fileReturnDates = "src\\test\\resources\\testReturnData.csv";
        PrepareData.returnDateReader(fileReturnDates);

        String input = "newMediaID\nBuch\nBookTitle\n2023-01-01\nAuthor\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        ChangeData.newData(0);

        Assertions.assertTrue(ObjectsDB.mediaMap.containsKey("newMediaID"));
    }
}
