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
        String dataFile = "src\\test\\resources\\testLibrary.csv";
        PrepareData.dataReader(dataFile);

        // Perform the backup
        CreateBackup.createBackup("src\\test\\resources\\testLibrary.csv");

        // Verify that the file is created
        assertTrue(Objects.requireNonNull(new java.io.File(dataFile).exists()));

        // Additional verifications based on the actual content of the file
        verifyCsvContent(dataFile, "Data: \"Person\",\"Mitarbeiter\"");
        verifyCsvContent(dataFile, "\"0\",\"12345678\",\"Mitarbeiter\"");
        verifyCsvContent(dataFile, "Data: \"Person\",\"Kunde\"");
        verifyCsvContent(dataFile, "\"2\",\"12112003\",\"Kunde\"");
        verifyCsvContent(dataFile, "Data: \"Media\",\"Buch\"");
        verifyCsvContent(dataFile, "\"Buch\",\"A123X\",\"Harry Potter und der Stein der Weisen\",\"1997-07-02\",\"Carlsen\"");
    }

    @Test
    void testBackupReturnDates() {
        String dataFile = "src\\test\\resources\\testLibrary.csv";
        PrepareData.dataReader(dataFile);
        String fileReturnDates = "src\\test\\resources\\testReturnData.csv";
        PrepareData.returnDateReader(fileReturnDates);

        // Perform the backup for media data only (not return dates)
        CreateBackup.createBackup("src\\test\\resources\\testLibrary.csv");

        // Perform the backup for return dates
        CreateBackup.backupReturnDates("src\\test\\resources\\testReturnData.csv");

        // Verify that the file is created
        assertTrue(Objects.requireNonNull(new java.io.File(fileReturnDates).exists()));

        // Additional verifications based on the actual content of the file
        verifyCsvContent(fileReturnDates, "\"2\",\"A123X\",\"2023-11-17\"");
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
