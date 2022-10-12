package ee.a1nu.decathlon;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import ee.a1nu.decathlon.model.Discipline;
import ee.a1nu.decathlon.model.Competitor;
import ee.a1nu.decathlon.service.CompetitorService;
import ee.a1nu.decathlon.service.FileService;

public class Main {
    
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) {
        validateNumberOfArguments(args);
        
        FileService fileService = new FileService();
        CompetitorService competitorService = new CompetitorService();
        
        List<String[]> competitorsRawData = fileService.readCSV(args[0]);
        List<Competitor> competitors = competitorService.getCompetitors(competitorsRawData);
        
        competitors.forEach(competitor -> competitor.setTotalScore(calculateTotalScore(competitor)));
        
        competitors.sort(Collections.reverseOrder());
        competitors.forEach(competitor -> competitor.setPlace(calculatePlace(competitor, competitors)));
        
        fileService.printXML(competitors, args[1]);
    }
    
    static void validateNumberOfArguments(String[] args) {
        if (args.length !=  2) {
            displayUsageMessage();
            throw new IllegalArgumentException();
        }
    }
    
    private static void displayUsageMessage() {
        StringBuilder sb = new StringBuilder("Invalid number of arguments.");
    
        sb.append("\nUsage: <program> <input-path> <output-name>\n");
        LOG.severe(sb.append("Example: -console -xml output.xml\n").toString());
    }
    
    static int calculateTotalScore(Competitor competitor) {
        int totalScore = 0;
        
        for (Discipline event : Discipline.values()) {
            totalScore += event.calculateEventScore(competitor.getResults(event.getIndex()));
        }
        
        return totalScore;
    }
    static String calculatePlace(Competitor competitor, List<Competitor> athletes) {
        int start = athletes.indexOf(competitor) + 1;
        int end = athletes.lastIndexOf(competitor) + 1;
        
        if (start == end)
            return String.valueOf(start);
        else
            return start + "-" + end;
    }
}