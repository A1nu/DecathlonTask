package ee.a1nu.decathlon.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ee.a1nu.decathlon.DTO.CompetitorsDTO;
import ee.a1nu.decathlon.model.Competitor;

public class FileService {
    private static final String BOM_UTF8_MARKER = "\uFEFF";
    private static final int EXPECTED_VALUES_PER_LINE = 11;
    private static final Logger LOG = Logger.getLogger(FileService.class.getName());
    
    public List<String[]> readCSV(String filename) {
        List<String[]> strings = new ArrayList<>();
        BufferedReader reader;
        
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));
            
            String line;
            boolean firstLine = true;
            
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    line = line.replace(BOM_UTF8_MARKER, "");
                    firstLine = false;
                }
                
                if (!line.isEmpty()) {
                    strings.add(splitCSVLine(line));
                }
            }
        }
        catch (FileNotFoundException e) {
            LOG.severe("The specified CSV file " + filename + " cannot be found.\n" +
                    "Verify that the file name is correctly specified and that it exists in the target directory.\n");
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return strings;
    }
    
    private String[] splitCSVLine(String line) {
        String[] strings = line.split(";");
        
        if (strings.length != EXPECTED_VALUES_PER_LINE) {
            LOG.severe("The CSV file is not in an expected format.\n" +
                    "Expected " + EXPECTED_VALUES_PER_LINE + " comma or semicolon separated values per line, " +
                    "found " + strings.length + ".\n");
            throw new RuntimeException();
        }
        
        return strings;
    }
    
    public void printXML(List<Competitor> competitors, String filename) {
        try {
            File file = new File(filename);
            JAXBContext context = JAXBContext.newInstance(CompetitorsDTO.class);
            Marshaller marshaller = context.createMarshaller();
        
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            CompetitorsDTO competitorDTO = new CompetitorsDTO(competitors);
        
            marshaller.marshal(competitorDTO, file);
        
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }
}
