package ee.a1nu.decathlon.DTO;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class CompetitorDTO {
    int id;
    private String name;
    @XmlElement
    private int totalScore;
    @XmlElement
    private String place;
    @XmlElementWrapper(name = "results")
    @XmlElement(name = "discipline")
    private List<ResultPOJO> results;

    public CompetitorDTO() {}
    
    public CompetitorDTO(String name, int totalScore, String place, List<ResultPOJO> results) {
        this.name = name;
        this.totalScore = totalScore;
        this.place = place;
        this.results = results;
    }
    
    public String getName() { return this.name; }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    
    public void setResults(List<ResultPOJO> results) {
        this.results = results;
    }
}
