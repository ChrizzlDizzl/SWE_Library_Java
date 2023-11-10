// ... (imports)

import com.example.swe_library.CreateBackup;
import com.example.swe_library.PrepareData;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BackupTest {

    @Test
    void testCreateBackup() {
        String dataFile = "library.csv";
        PrepareData.dataReader(dataFile);

        // Perform the backup
        CreateBackup.createBackup();

        // Verify that the file is created
        assertTrue(Objects.requireNonNull(new java.io.File("library.csv").exists()));

        // Additional verifications based on the actual content of the file
        verifyCsvContent("library.csv", "Data: \"Person\",\"Mitarbeiter\"");
        verifyCsvContent("library.csv", "\"0\",\"12345678\",\"Mitarbeiter\"");
        verifyCsvContent("library.csv", "\"1\",\"98765432\",\"Mitarbeiter\"");
    }

    @Test
    void testBackupReturnDates() {
        String dataFile = "library.csv";
        PrepareData.dataReader(dataFile);
        String fileReturnDates = "returnDates.csv";
        PrepareData.returnDateReader(fileReturnDates);

        // Perform the backup for media data only (not return dates)
        CreateBackup.createBackup();

        // Perform the backup for return dates
        CreateBackup.backupReturnDates();

        // Verify that the file is created
        assertTrue(Objects.requireNonNull(new java.io.File("returnDates.csv").exists()));

        // Additional verifications based on the actual content of the file
        verifyCsvContent("returnDates.csv", "\"2\",\"2\",\"2023-11-17\"");
        // Add more verifications as needed
    }

    private void verifyCsvContent(String fileName, String expectedContent) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Verify the content
            String line;
            boolean contentFound = false;
            while ((line = reader.readLine()) != null) {
                if (line.equals(expectedContent)) {
                    contentFound = true;
                    break;
                }
            }
            assertTrue(contentFound, "Expected content not found in the file: " + expectedContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
