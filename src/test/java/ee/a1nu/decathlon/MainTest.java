package ee.a1nu.decathlon;

import static ee.a1nu.decathlon.Main.calculatePlace;
import static ee.a1nu.decathlon.Main.calculateTotalScore;
import static ee.a1nu.decathlon.Main.validateNumberOfArguments;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ee.a1nu.decathlon.model.Competitor;
import ee.a1nu.decathlon.service.CompetitorService;

public class MainTest {
    
    CompetitorService competitorService = new CompetitorService();
    List<String[]> competitorsRawData = Arrays.asList(
            new String[] {"John Smith", "12.61", "5.00", "9.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"},
            new String[] {"Annie Smith", "12.61", "5.00", "9.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"},
            new String[] {"Jane Doe", "13.04", "4.53", "7.79", "1.55", "64.72", "18.74", "24.20", "2.40", "28.20", "6:50.76"}
    );
    
    List<Competitor> competitors = competitorService.getCompetitors(competitorsRawData);
    
    @Test
    public void checkValidAmountOfArguments() {
        validateNumberOfArguments(new String[] {"/some/path/file.csv", "output.xml"});
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void wrongArgumentsAmount() {
        validateNumberOfArguments(new String[] {"/some/path/file.csv"});
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void overflowArgumentsAmount() {
        validateNumberOfArguments(new String[] {"/some/path/file.csv", "/some/path/xml", "123"});
    }
    
    @Test
    public void calculateTotalScoreCheck() {
        assertThat(calculateTotalScore(competitors.get(0)), is(4200));
    }
    
    @Test
    public void placesCheck() {
        competitors.forEach(competitor -> competitor.setPlace(calculatePlace(competitor, competitors)));
        competitors.forEach(competitor -> competitor.setTotalScore(calculateTotalScore(competitor)));
        assertNotEquals("1-2", competitors.get(0).getPlace());
        assertNotEquals("2-1", competitors.get(1).getPlace());
        assertNotEquals("3", competitors.get(2).getPlace());
    }
}