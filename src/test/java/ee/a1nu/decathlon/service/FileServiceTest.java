package ee.a1nu.decathlon.service;


import static org.junit.Assert.assertArrayEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


import org.junit.Test;

public class FileServiceTest {
    FileService fileService = new FileService();
    
    @Test
    public void validateCSVReader() throws IOException {
        File tempFile = File.createTempFile("temp", ".tmp");
        tempFile.deleteOnExit();
        
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        bw.write("John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72\n"+
                "Jane Doe;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6:50.76");
        bw.close();
    
    
        assertArrayEquals(fileService.readCSV(
                tempFile.getAbsolutePath()).get(0),
                new String[] {"John Smith", "12.61", "5.00", "9.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"});
        assertArrayEquals(
                fileService.readCSV(tempFile.getAbsolutePath()).get(1),
                new String[] {"Jane Doe", "13.04", "4.53", "7.79", "1.55", "64.72", "18.74", "24.20", "2.40", "28.20", "6:50.76"});
    }

    @Test(expected = RuntimeException.class)
    public void fileNotFound() {
        fileService.readCSV("/some/wrong/path/wrong.txt");
    }
    
    @Test(expected = RuntimeException.class)
    public void incorrectAmountOfAttributes() throws IOException {
        File tempFile = File.createTempFile("temp", ".tmp");
        tempFile.deleteOnExit();
    
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        bw.write("John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81");
        bw.close();
    
        fileService.readCSV(tempFile.getAbsolutePath());
    }
}