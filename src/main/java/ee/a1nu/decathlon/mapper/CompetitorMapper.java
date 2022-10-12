package ee.a1nu.decathlon.mapper;

import java.util.ArrayList;
import java.util.List;

import ee.a1nu.decathlon.DTO.CompetitorDTO;
import ee.a1nu.decathlon.DTO.ResultPOJO;
import ee.a1nu.decathlon.model.Discipline;
import ee.a1nu.decathlon.model.Competitor;
import ee.a1nu.decathlon.util.CompetitorUtils;

public class CompetitorMapper {
    private static CompetitorDTO toCompetitorDTO(Competitor competitor) {
        CompetitorDTO competitorDTO = new CompetitorDTO();
        
        competitorDTO.setName(competitor.getName());
        competitorDTO.setPlace(competitor.getPlace());
        competitorDTO.setTotalScore(competitor.getTotalScore());
        competitorDTO.setResults(mapResults(competitor.getResults()));
        
        return competitorDTO;
    }
    
    private static List<ResultPOJO> mapResults(List<Double> results) {
        List<ResultPOJO> resultPOJOS = new ArrayList<>();
    
        for (Discipline event : Discipline.values()) {
            if (event.getIndex() != Discipline.FIFTEEN_HUNDREDS_RUN.getIndex()) {
                resultPOJOS.add(new ResultPOJO(event.getName(), results.get(event.getIndex()).toString()));
            } else {
                resultPOJOS.add(new ResultPOJO(event.getName(), CompetitorUtils.stringifyFifteenHundredsResult(results.get(event.getIndex()))));
            }
        }
        return resultPOJOS;
    }
    
    public static List<CompetitorDTO> mapCompetitors(List<Competitor> competitors) {
        List<CompetitorDTO> competitorDTOS = new ArrayList<>();
        
        for (Competitor competitor : competitors) {
            competitorDTOS.add(toCompetitorDTO(competitor));
        }
        
        return competitorDTOS;
    }
}
