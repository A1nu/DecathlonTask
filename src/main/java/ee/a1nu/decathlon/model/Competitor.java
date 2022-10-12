package ee.a1nu.decathlon.model;

import java.util.ArrayList;
import java.util.List;


import ee.a1nu.decathlon.util.CompetitorUtils;

public class Competitor implements Comparable<Competitor> {
    private final String name;
    private final List<Double> results;
    private int totalScore;
    private String place;
    
    private Competitor(CompetitorBuilder competitorBuilder) {
        this.name = competitorBuilder.name;
        this.results = competitorBuilder.results;
    }
    
    public String getName() {
        return name;
    }
    
    public Double getResults(int index) {
        return results.get(index);
    }
    
    public List<Double> getResults() {
        return results;
    }
    
    @Override
    public int compareTo(Competitor anotherCompetitor) {
        return Integer.compare(totalScore, anotherCompetitor.totalScore);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Competitor))
            return false;
        return totalScore == ((Competitor) o).totalScore;
    }
    
    public int getTotalScore() {
        return totalScore;
    }
    
    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
    
    public String getPlace() {
        return place;
    }
    
    public void setPlace(String place) {
        this.place = place;
    }
    
    public static class CompetitorBuilder {
        private String name;
        private List<Double> results;
    
        public CompetitorBuilder name(String name) {
            this.name = CompetitorUtils.validateName(name);
            return this;
        }
        
        public CompetitorBuilder results(String[] rawData) {
            List<Double> results = new ArrayList<>();
            
            for (String string : rawData) {
                results.add(CompetitorUtils.validateAndParseCompetitionResult(string));
            }
            
            this.results = results;
            return this;
        }
        
        public Competitor build() {
            return new Competitor(this);
        }
    }
}


