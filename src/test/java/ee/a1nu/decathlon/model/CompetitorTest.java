package ee.a1nu.decathlon.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ee.a1nu.decathlon.service.CompetitorService;

public class CompetitorTest {
    
    CompetitorService competitorService = new CompetitorService();
    List<String[]> competitorsRawData = Arrays.asList(
            new String[] {"John Smith", "12.61", "5.00", "9.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"},
            new String[] {"Annie Smith", "12.61", "5.00", "9.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"},
            new String[] {"Jane Doe", "13.04", "4.53", "7.79", "1.55", "64.72", "18.74", "24.20", "2.40", "28.20", "6:50.76"}
    );
    
    List<Competitor> competitors = competitorService.getCompetitors(competitorsRawData);
    
    @Test
    public void competitorsAreComparedByTotalScore() {
        competitors.get(0).setTotalScore(1000);
        competitors.get(1).setTotalScore(1200);
        competitors.get(2).setTotalScore(1000);
        
        assertEquals(0, competitors.get(0).compareTo(competitors.get(2)));
        assertEquals(1, competitors.get(1).compareTo(competitors.get(2)));
        assertEquals(-1, competitors.get(0).compareTo(competitors.get(1)));
    }

}