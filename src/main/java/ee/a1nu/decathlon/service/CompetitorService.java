package ee.a1nu.decathlon.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ee.a1nu.decathlon.model.Competitor;

public class CompetitorService {
    public List<Competitor> getCompetitors(List<String[]> competitorsRawData) {
        List<Competitor> competitors = new ArrayList<>();
    
        competitorsRawData.forEach(stringArray -> competitors.add(buildCompetitor(stringArray)));
        
        return competitors;
    }
    
    Competitor buildCompetitor(String[] values) {
        return new Competitor.CompetitorBuilder().name(values[0]).results(Arrays.copyOfRange(values, 1, values.length)).build();
    }
}
