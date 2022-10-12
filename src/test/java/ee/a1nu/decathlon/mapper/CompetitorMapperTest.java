package ee.a1nu.decathlon.mapper;

import static ee.a1nu.decathlon.mapper.CompetitorMapper.mapCompetitors;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ee.a1nu.decathlon.DTO.CompetitorDTO;
import ee.a1nu.decathlon.model.Competitor;
import ee.a1nu.decathlon.service.CompetitorService;

public class CompetitorMapperTest {
    CompetitorService competitorService = new CompetitorService();
    List<String[]> competitorsRawData = Arrays.asList(
            new String[] {"John Smith", "12.61", "5.00", "9.22", "1.50", "60.39", "16.43", "21.60", "2.60", "35.81", "5:25.72"},
            new String[] {"Jane Doe", "13.04", "4.53", "7.79", "1.55", "64.72", "18.74", "24.20", "2.40", "28.20", "6:50.76"}
    );
    
    @Test
    public void dtoBuilderTester() {
        List<Competitor> competitors = competitorService.getCompetitors(competitorsRawData);
        List<CompetitorDTO> competitorDTOS = mapCompetitors(competitors);
    
        assertThat(competitors.get(0).getName(), is(competitorDTOS.get(0).getName()));
    }
}